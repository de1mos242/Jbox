package net.de1mos.jbox.api.client.vk;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import net.de1mos.jbox.api.client.vk.core.VKApiMethods;
import net.de1mos.jbox.api.client.vk.core.VKApiParams;
import net.de1mos.jbox.api.client.vk.core.VKApplicationCredential;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class VKApiURL {
	
	private static final String METHOD_URI = "https://api.vk.com/method/";
	
	private static final String AUTH_URI = "http://oauth.vk.com/authorize?";
	private static final String ACCESS_TOKEN_URI = "https://oauth.vk.com/access_token?";

	
	private VKApplicationCredential credential;
	
	public VKApiURL(VKApplicationCredential credential) {
		super();
		this.credential = credential;
	}

	public URL getAuthURL(String[] scopes) throws UnsupportedEncodingException, MalformedURLException
	{
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(createParam(VKApiParams.CLIENT_ID,credential.getAppId()));
		params.add(createParam(VKApiParams.SCOPE,StringUtils.join(scopes,",")));
		params.add(createParam(VKApiParams.REDIRECT_URI, credential.getResponseUri()));
		params.add(createParam(VKApiParams.RESPONSE_TYPE,"code"));
		
		return new URL(AUTH_URI+getGETParamsString(params));
	}
	
	public URL getAuthTokenUrl(String code) throws MalformedURLException, UnsupportedEncodingException {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(createParam(VKApiParams.CLIENT_ID,credential.getAppId()));
		params.add(createParam(VKApiParams.CLIENT_SECRET,credential.getAppKey()));
		params.add(createParam(VKApiParams.REDIRECT_URI,credential.getResponseUri()));
		params.add(createParam(VKApiParams.CODE,code));
		
		return new URL(ACCESS_TOKEN_URI+getGETParamsString(params));
	}
	
	
	private BasicNameValuePair createParam(VKApiParams name,String value){
		return new BasicNameValuePair(name.toString(), value);
	}
	
	private String getGETParamsString(ArrayList<NameValuePair> params) throws UnsupportedEncodingException
	{
		ArrayList<String> paramsStringList = new ArrayList<String>();
		
		for (NameValuePair nameValuePair : params) {
			String value = URLEncoder.encode(nameValuePair.getValue(),"UTF-8");
			paramsStringList.add(String.format("%s=%s",nameValuePair.getName(),value));
		}
		
		return StringUtils.join(paramsStringList,"&");
	}

	public URL getUserInfoUrl(String token, String userId, String[] fields) throws MalformedURLException, UnsupportedEncodingException {
		
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(createParam(VKApiParams.ACCESS_TOKEN,token));
		params.add(createParam(VKApiParams.USERIDS,userId));
		params.add(createParam(VKApiParams.FIELDS,StringUtils.join(fields,",")));
		
		return new URL(METHOD_URI+VKApiMethods.GET_USER.toString()+"?"+getGETParamsString(params));
	}

}
