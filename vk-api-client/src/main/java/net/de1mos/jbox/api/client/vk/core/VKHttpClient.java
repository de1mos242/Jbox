package net.de1mos.jbox.api.client.vk.core;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

public class VKHttpClient {

	protected org.apache.http.client.HttpClient httpCleint;
	
	public VKHttpClient(HttpClient client) {
		this.httpCleint = client;
	}
	
	public VKHttpClient(HttpClientWrapper wrapper) {
		this.httpCleint = wrapper.getDefaultHttpClient();
	}

	public HttpResponse executeRequest() {
		return null;
	}

}
