package com.zs.service;

import java.util.List;
import java.util.Map;

public interface ITrainService {
	
	List<Map<String, Object>> search(Map<String, String> map);
	List<Map<String, Object>> trainMember(Map<String, String> map);
	Integer addTrain(Map<String, String> map);
	Integer startTrain(Map<String, String> map);
	Integer endTrain(Map<String, String> map);
	List<Map<String, Object>> monitor(String trainId);
	Map<String, Object> memHrList(String trainId, String sn);
}
