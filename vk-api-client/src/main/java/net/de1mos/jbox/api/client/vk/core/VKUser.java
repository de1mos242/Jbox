package net.de1mos.jbox.api.client.vk.core;

public class VKUser {
	
	private VKAuthToken token;
	
	public VKUser(VKAuthToken token, String fullName) {
		super();
		this.token = token;
		this.fullName = fullName;
	}

	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public VKAuthToken getToken() {
		return token;
	}
	
	public String getTokenString(){
		return token.getTokenString();
	}

	public void setToken(VKAuthToken token) {
		this.token = token;
	}

}
