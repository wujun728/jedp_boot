/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.httpClient;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月3日 上午10:42:11
 */
public class HttpClientUtil {

	/**
	 * post request
	 * @param url
	 * @param formparams
	 * @return
	 */
    public static String post(String url, List<NameValuePair> formparams) {  
    	
    	String response = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse httpResponse = null;
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
			httpPost.setEntity(uefEntity);
			httpResponse = httpClient.execute(httpPost);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					String responseContent = EntityUtils.toString(entity);
					response = responseContent;
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
    }  
  
    /**
     * get request  
     * @param url
     * @return
     */
    public static String get(String url) {  
    	
    	String response = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(get);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					String responseContent = EntityUtils.toString(entity);
					response = responseContent;
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
    }  
  
}
