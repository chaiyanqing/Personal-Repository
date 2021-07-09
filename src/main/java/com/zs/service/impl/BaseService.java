package com.zs.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaseService {

	public Logger logger = Logger.getLogger(Logger.class);
	
	public JSONObject params = new JSONObject();
	
	public Integer start = 0;
	public Integer size = 10;
	
	public JSONObject convertParams(JSONArray arr) {
		 JSONObject j = new JSONObject();
	     for (int i = 0; i < arr.size(); i++) {
	    	 JSONObject obj = arr.getJSONObject(i);
	         String name = obj.getString("name");
	         Object value = obj.get("value");
	         j.put(name, value);

	     }
	     return j;
	}
	
	public void initContextParams(Map<String, String> map) {
		params = new JSONObject();
		System.out.println(">>map："+map);
		String aoData = map.containsKey("aoData") ? map.get("aoData") : "";
		System.out.println(">>aoData："+aoData);
		try {
			aoData = URLDecoder.decode(aoData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONArray objects = JSONArray.parseArray(aoData);
		System.out.println(">>objects："+objects);
	     for (int i = 0; i < objects.size(); i++) {
	    	 JSONObject obj = objects.getJSONObject(i);
	         String name = obj.getString("name");
	         Object value = obj.get("value");
	         params.put(name, value);
	     }
	     System.out.println(">>params："+params);
	     start = params.getInteger("iDisplayStart");
	 	 size = params.getInteger("iDisplayLength");
	}
}
