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

public class VkApiClient {

	private final String appId;
	private final String appKey;
	private final String responseUri;

	private HttpClientWrapper clientWrapper;
	
	private VKApiURL urlGenerator;

	public VkApiClient(VKApplicationCredential credential,HttpClientWrapper wrapper) {
		super();
		this.appId = credential.getAppId();
		this.appKey = credential.getAppKey();
		this.responseUri = credential.getResponseUri();
		
		this.clientWrapper = wrapper;
		
		this.urlGenerator =  new VKApiURL(credential);
	}
	
	public URL getAuthURL(String[] scopes)
	{
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
			
			token = new VKAuthToken(tokenString,new Date(expiare), user_id);
			
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
	public ArrayList<String> searchMusic(String searchRequest,VKUser user)
	{
		return null;
		
	}
	
	public VKHttpClient getHttpClient()
	{
		return new VKHttpClient(clientWrapper);
	}
}
