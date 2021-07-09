package com.zs.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;


public interface AbodyplusService {

	List<Map<String, Object>> getGroups();
	
	Collection<String> trainLoadSearch(Map<String, String> map);
	
	List<Map<String, Object>> getLabels();
	
	JSONObject groupStatView(Map<String, String> map);
	
	JSONObject memberView(Map<String, String> map);
	
	Integer createTrainData();
	
	void doSomething(String message);

}
