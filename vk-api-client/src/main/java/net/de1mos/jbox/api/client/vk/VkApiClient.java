package net.de1mos.jbox.api.client.vk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import net.de1mos.jbox.api.client.vk.core.HttpClientWrapper;
import net.de1mos.jbox.api.client.vk.core.VKApplicationCredential;
import net.de1mos.jbox.api.client.vk.core.VKAuthToken;
import net.de1mos.jbox.api.client.vk.core.VKHttpClient;
import net.de1mos.jbox.api.client.vk.core.VKUser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class VkApiClient {

	private final String appId;
	private final String appKey;
	private final String responseUri;

	private HttpClientWrapper clientWrapper;

	private VKApiURL urlGenerator;

	public VkApiClient(VKApplicationCredential credential,
			HttpClientWrapper wrapper) {
		super();
		this.appId = credential.getAppId();
		this.appKey = credential.getAppKey();
		this.responseUri = credential.getResponseUri();

		this.clientWrapper = wrapper;

		this.urlGenerator = new VKApiURL(credential);
	}

	public URL getAuthURL(String[] scopes) {
		URL authUrl = null;
		try {
			authUrl = urlGenerator.getAuthURL(scopes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authUrl;
	}

	public VKAuthToken getOAuthToken(String code) {
		VKHttpClient httpClient = getHttpClient();

		VKAuthToken token = null;

		try {
			URL apiUrl = urlGenerator.getAuthTokenUrl(code);

			String jsonResponse = httpClient.executeRequest(apiUrl);

			ObjectMapper mapper = new ObjectMapper();

			JsonNode node = mapper.readTree(jsonResponse);

			Integer expiare = node.get("expires_in").asInt();
			Integer user_id = node.get("user_id").asInt();
			String tokenString = node.get("access_token").asText();

			token = new VKAuthToken(tokenString, new Date(expiare), user_id);

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

	public VKUser getCurrentVKUserInfo(VKAuthToken token) {
		String[] fields = { "Username" };

		VKUser vkuser = null;

		try {
			VKHttpClient httpClient = getHttpClient();

			URL userUrl = urlGenerator.getUserInfoUrl(token.getTokenString(),
					token.getUser_id().toString(), fields);

			String jsonResponse = httpClient.executeRequest(userUrl);

			ObjectMapper mapper = new ObjectMapper();

			JsonNode rootNode = mapper.readTree(jsonResponse);

			ObjectNode node = (ObjectNode) rootNode.get("response").get(0);

			String fname = node.get("first_name").asText();
			String lname = node.get("last_name").asText();

			vkuser = new VKUser(token, String.format("%s %s", fname, lname));

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vkuser;

	}

	public ArrayList<VKMusicTrack> searchMusic(String searchRequest, VKUser user) {
		VKHttpClient httpClient = getHttpClient();
		ArrayList<VKMusicTrack> musicList = null;
		try {

			musicList = new ArrayList<VKMusicTrack>();

			URL searchUrl = urlGenerator.searchAudio(user.getTokenString(),
					searchRequest);

			String jsonResponse = httpClient.executeRequest(searchUrl);

			ObjectMapper mapper = new ObjectMapper();

			JsonNode rootNode = mapper.readTree(jsonResponse);

			System.out.println(jsonResponse);

			ArrayNode arrayNode = (ArrayNode) rootNode.get("response");

			for (JsonNode jsonNode : arrayNode) {
				if ( jsonNode instanceof IntNode ) {
					continue;
				}

				musicList.add(new VKMusicTrack(jsonNode));
			}

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return musicList;
	}

	public VKHttpClient getHttpClient() {
		return new VKHttpClient(clientWrapper);
	}
}
