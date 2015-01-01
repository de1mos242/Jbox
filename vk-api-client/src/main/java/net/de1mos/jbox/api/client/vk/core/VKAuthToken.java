package net.de1mos.jbox.api.client.vk.core;

import java.util.Date;

public class VKAuthToken implements OAuthToken {
	
	private String tokenString;
	
	private Date expiare;
	
	public Date getExpiare() {
		return expiare;
	}

	public VKAuthToken(String tokenString, Date expiare, Integer user_id) {
		super();
		this.tokenString = tokenString;
		this.expiare = expiare;
		this.user_id = user_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	private Integer user_id;

	@Override
	public String getTokenString() {
		return tokenString;
	}
}
