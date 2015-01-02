package net.de1mos.jbox.api.client.vk.core;

public enum VKApiMethods {
	GET_USER("users.get");
	
	private final String value;

	public String getValue() {
		return value;
	}

	private VKApiMethods(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
