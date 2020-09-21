/**<p>项目名：</p>
 * <p>包名：	com.zttx.im.utils</p>
 * <p>文件名：HttpClientUtils.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015年3月11日-下午2:15:05</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package com.qbt.framework.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.qbt.framework.constant.Constant;

/**<p>名称：HttpClientUtils.java</p>
 * <p>描述：</p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年3月11日 下午2:15:05
 * @version 1.0.0
 */
public class HttpClientUtils {

	protected static Logger logger = LogManager.getLogger(HttpClientUtils.class);
	
	public final static String HTTPS = "https";
	public final static String UTF_8 = "UTF-8";
	
	private static HttpClientUtils instance = new HttpClientUtils();
	
	public static HttpClientUtils getIntance(){
		return instance;
	} 
	
	private HttpClientUtils(){}
	
	//是否是Https请求
	private boolean isHttps(String url){
		return url.contains("https://");
	}
	
	
	/**
	 * 描述：
	 * <pre>
	 * 	发送HTTP请求方法
	 * </pre>
	 * @param builder
	 * @param requestData
	 * @param method
	 * @return
	 * @throws Exception 
	 */
	public HttpResponse sendHttp(String url, Object obj,  RequestMethod method) throws Exception{
		return sendHttp(url, obj, method, null);
	}
	
	/**
	 * 发送HTTP请求方法
	 * <pre></pre>
	 * @param url
	 * @param obj
	 * @param method
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public HttpResponse sendHttp(String url, Object obj,  RequestMethod method, Header[] headers) throws Exception{
		HttpClient client = getClient( isHttps(url) );
		return send(client, url, obj, method, headers);
	}
	
	
	//请求发送
	private  HttpResponse send(HttpClient client, String url, Object obj,  RequestMethod method, Header[] headers) throws Exception {
		HttpResponse response = null;
		switch (method) {
			case POST:
				response = sendPost(client, url, obj, headers);
				break;
			case DELETE:
				response = sendDelete(client, url, obj, headers);
				break;
			case GET:
				response = sendGet(client, url, obj, headers);
				break;
			case PUT:
				response = sendPut(client, url, obj, headers);
				break;
			default:
				throw new RuntimeException("请正确的选择网络的请求类型");
		}
		return response;
	}
	
	//发送POST
	private  HttpResponse sendPost(HttpClient client, String url, Object obj, Header[] headers) throws ClientProtocolException, IOException{
		HttpPost post = new HttpPost(url);
		if(headers != null){
			post.setHeaders(headers);
		}
		if( obj != null ){
			post.setEntity(new StringEntity( JSON.toJSONString(obj), Constant.UTF8 ) );
		}
		return client.execute(post);
	}
	
	//发送GET
	private  HttpResponse sendGet(HttpClient client, String url, Object obj, Header[] headers) throws ClientProtocolException, IOException{
		HttpGet get = new HttpGet(url);
		if(headers != null){
			get.setHeaders(headers);
		}
		return client.execute(get);
	}
	
	//发送PUT
	private  HttpResponse sendPut( HttpClient client, String url, Object obj, Header[] headers ) throws ClientProtocolException, IOException{
		HttpPut put = new HttpPut(url);
		if(headers != null){
			put.setHeaders(headers);
		}
		if( obj != null){
			put.setEntity(new StringEntity(JSON.toJSONString(obj), Constant.UTF8) );
		}
		return client.execute(put);
	}
	
	//发送DELETE
	private  HttpResponse sendDelete( HttpClient client, String url, Object obj, Header[] headers ) throws ClientProtocolException, IOException{
		HttpDelete delete = new HttpDelete(url);
		delete.setHeaders(headers);
		return client.execute(delete);
	}
	
	//获取HTTP实体对象
	private  HttpClient getClient(boolean isSSL){
		CloseableHttpClient client = HttpClients.createDefault();
		if( isSSL ){
			TrustStrategy trust = new TrustStrategy(){
				@Override
				public boolean isTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					return true;
				}
			};
			try {
				SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(null, trust).build();
				LayeredConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);    
				Registry<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create().register(HTTPS, factory).build(); 
				PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registryBuilder);
				client = HttpClients.custom().setConnectionManager(cm).build();
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("获取HTTPS请求失败" + e.getMessage());
			}
		}
		return client;
	}
	
	
	
}
