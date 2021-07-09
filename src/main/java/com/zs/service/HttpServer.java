package com.zs.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.zs.util.ToolsUtils;


public class HttpServer{

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private static HttpServer instance = getInstance();
	
	public HttpServer(){}
	
	public static HttpServer getInstance(){
		if(instance == null)
			instance = new HttpServer();
		return instance;
	}
	
	private final String DEFAULT_CHARSET_UTF8 = "UTF-8";
	private String url = "";
	private String method = "";
	private String params = "";
	
	private int timeout = 10; // 秒
	private int retry = 1 ;   // 超时尝试次数
	private int interval = 1; // 秒
	
	/**
	 * 发送Http请求
	 * @param url 目标服务URL
	 * @param method 请求方式（POST|GET）
	 * @param timeout 超时时间（单位：秒）
	 * @param retry 失败尝试次数
	 * @param interval 失败等待再次请求延迟（单位：秒）
	 * @param body 请求报文
	 * @return
	 */
	public static HttpResponse sendHttp(String url, String method, int timeout, int retry, int interval, String body) {
		HttpResponse rsp = null;
		if(!instance._isBlank(url)){
			instance.url = url;
		}
		if(!instance._isBlank(method)){
			instance.method = method;
		}
		if(!instance._isBlank(body)){
			instance.params = body;
		}
		if(!instance._isBlank(timeout)){
			instance.timeout = timeout;
		}
		if(!instance._isBlank(interval) && interval > 0){
			instance.interval = interval;
		}
		if(!instance._isBlank(retry) && retry > 0){
			instance.retry = retry;
		}
		try {
			HttpResponse rstBody = instance.trySomeTimes(instance.url, instance.method, 1, instance.params);
			int rstcode = rstBody.getCode();
			String rstbody = rstBody.getBody();
			rsp = new HttpResponse(rstcode, rstbody);
		} catch (Exception e) {
			instance.logger.error("接口服务异常。");
			e.printStackTrace();
		}
		return rsp;
	}
	
	private HttpResponse trySomeTimes(String url, String method, int tryTimes, String body){
		logger.info("接口调用：" + tryTimes);
		String response = null;
		try{
			if(method != null && "GET".equals(method.toUpperCase())){
				response = instance.doGet(url);
			}else if(method != null && "POST".equals(method.toUpperCase())){
				response = instance.doPost(url);
			}else{
				
			}
		}catch(HttpException e){
			if(instance.retry > tryTimes){
				if(interval > 0){
					try {
						System.out.println("等待"+interval+"秒再次尝试。");
						Thread.sleep(interval * 1000);
						trySomeTimes(url, method, tryTimes + 1, body);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return new HttpResponse(1000, response);
	}
	
	
	/**
	 * @param url
	 * @return response
	 */
	public String doPost(String url) throws HttpException{
		logger.info("http get url is [" + url + "]");
		HttpPost post = new HttpPost(url);
		return instance.doPost(post);
	}
	
	/**
	 * @param url
	 * @param params key1=value1&key2=value2
	 * @return response
	 */
	public String doPost(String url, String params) throws HttpException{
		logger.info("http get url is [" + url + "], params is [" + params + "]");
		HttpPost post = new HttpPost(url);
		try {
			StringEntity entity = new StringEntity(params, instance.DEFAULT_CHARSET_UTF8);
			entity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance.doPost(post);
	}
	
	/**
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPost(String url, Map<String, String> params, String charset) throws HttpException{
		HttpPost post = new HttpPost(url);
		try {
			// 创建参数队列    
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
	        Iterator<String> keys = params.keySet().iterator();
	        while (keys.hasNext()) {
	        	String key = keys.next();
	            nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));  
	        }
	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, ToolsUtils.isBlank(charset) ? instance.DEFAULT_CHARSET_UTF8 : charset));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance.doPost(post);
	}
	
	private String doPost(HttpPost post) throws HttpException{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String rspContent = null;
		try {
			httpClient = HttpClients.createDefault();
			post.setConfig(instance.getRequestConfig(instance.timeout * 1000));
			//StringEntity strintEntity = new StringEntity("请求的报文内容。", DEFAULT_CHARSET_UTF8);
			//post.setEntity(strintEntity);
			response = httpClient.execute(post);
			entity = response.getEntity();
			rspContent = EntityUtils.toString(entity, DEFAULT_CHARSET_UTF8);
			System.out.println(rspContent);
		} catch (SocketTimeoutException se) {
			// se.printStackTrace();
			instance.logger.error(se.getMessage());
			throw new HttpException();
		} catch(IOException ie){
			// ie.printStackTrace();
			instance.logger.error(ie.getMessage());
			throw new HttpException();
		}finally {
			try {
				if(response != null)
					response.close();
				if(httpClient != null)
					httpClient.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return rspContent;
	}
	
	public String doGet(String url) throws HttpException{
		logger.info("http get url is [" + url + "]");
		HttpGet get = new HttpGet(url);
		return doGet(get);
	}
	
	public String doGet(String url, String params) throws HttpException{
		logger.info("http get url is [" + url + "], params is [" + params + "]");
		HttpGet get = null;
		try {
			get = new HttpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("23");
		}
		return instance.doGet(get);
	}
	
	private String doGet(HttpGet get) throws HttpException{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String rspContent = null;
		try {
			httpClient = HttpClients.createDefault();
			get.setConfig(instance.getRequestConfig(instance.timeout * 1000));
			response = httpClient.execute(get);
			entity = response.getEntity();
			rspContent = EntityUtils.toString(entity, DEFAULT_CHARSET_UTF8);
			System.out.println(rspContent);
		} catch (SocketTimeoutException se) {
			// se.printStackTrace();
			logger.error(se.getMessage());
			throw new HttpException();
		} catch(IOException ie){
			// ie.printStackTrace();
			logger.error(ie.getMessage());
			throw new HttpException();
		}finally {
			try {
				if(response != null)
					response.close();
				if(httpClient != null)
					httpClient.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return rspContent;
	}
	
	private RequestConfig getRequestConfig(int timeOut){
		Builder builder = RequestConfig.custom();
		builder.setSocketTimeout(timeOut);
		builder.setConnectTimeout(timeOut);
		builder.setConnectionRequestTimeout(timeOut);
		return builder.build();
	}
	
	private boolean _isBlank(Object obj){
		return (obj == null || obj.toString().length() == 0);
	}
	
}

class HttpException extends Exception{
	private static final long serialVersionUID = 1L;
}

class HttpResponse{
	private int code;
	private String body;
	public HttpResponse(int code, String body){
		this.code = code;
		this.body = body;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
