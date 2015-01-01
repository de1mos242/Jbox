package net.de1mos.jbox.api.client.vk.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class VKHttpClient {

	protected org.apache.http.client.HttpClient httpCleint;
	
	public VKHttpClient(HttpClient client) {
		this.httpCleint = client;
	}
	
	public VKHttpClient(HttpClientWrapper wrapper) {
		this.httpCleint = wrapper.getDefaultHttpClient();
	}

	public String executeRequest(URL executedURl) throws URISyntaxException {
		return executeGet(new HttpGet(executedURl.toURI()));
	}
	
	public String executeGet(HttpUriRequest request)
	{
		String resp = null;
		try {
			HttpResponse hresponse = httpCleint.execute(request);
			
			HttpEntity entity = hresponse.getEntity();
			
			return IOUtils.toString(entity.getContent(),"UTF-8");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}

}
