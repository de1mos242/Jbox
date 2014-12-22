package net.de1mos.jbox.api.client.vk.core;

public class VKApplicationCredential {

	private final String appId;
	public String getAppId() {
		return appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getResponseUri() {
		return responseUri;
	}

	private final String appKey;
	private final String responseUri;
	
	public VKApplicationCredential(String appId, String appKey,
			String responseUri) {
		super();
		this.appId = appId;
		this.appKey = appKey;
		this.responseUri = responseUri;
	}

}
