package net.de1mos.jbox.api.client.vk;

import net.de1mos.jbox.api.client.vk.core.HttpClientWrapper;
import net.de1mos.jbox.api.client.vk.core.OAuthToken;
import net.de1mos.jbox.api.client.vk.core.VKApplicationCredential;
import net.de1mos.jbox.api.client.vk.core.VKHttpClient;

public class VkApiClient {

	private final String appId;
	private final String appKey;
	private final String responseUri;

	private HttpClientWrapper clientWrapper;

	public VkApiClient(VKApplicationCredential credential,HttpClientWrapper wrapper) {
		super();
		this.appId = credential.getAppId();
		this.appKey = credential.getAppKey();
		this.responseUri = credential.getResponseUri();
		
		this.clientWrapper = wrapper;
	}

	public OAuthToken getOAuthToken() {
		VKHttpClient client = new VKHttpClient(clientWrapper);

		return null;
	}
}
