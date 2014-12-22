package net.de1mos.jbox.api.client.vk.core;

import org.apache.http.client.HttpClient;

public interface HttpClientWrapper {
	
	public HttpClient getDefaultHttpClient();
	
	public HttpClient wrapClient(HttpClient client);

}
