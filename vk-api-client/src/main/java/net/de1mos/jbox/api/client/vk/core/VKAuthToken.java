package net.de1mos.jbox.api.client.vk.core;

public class VKAuthToken implements OAuthToken {
	
	private String tokenString;

	@Override
	public String getTokenString() {
		return tokenString;
	}
}
