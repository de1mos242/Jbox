package net.de1mos.jbox.api.client.vk.core;

public enum VKApiParams {

	CLIENT_ID("client_id"), 
	SCOPE("scope"), 
	REDIRECT_URI("redirect_uri"), 
	RESPONSE_TYPE("response_type"),
	CLIENT_SECRET("client_secret"), 
	CODE("code"),
	ACCESS_TOKEN("access_token"),
	USERIDS("uids"),
	FIELDS("fields"),
	QUERY("q");
	

	private final String value;

	public String getValue() {
		return value;
	}

	private VKApiParams(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
	
}
