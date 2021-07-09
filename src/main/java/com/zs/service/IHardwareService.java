package com.zs.service;

import java.util.List;
import java.util.Map;

public interface IHardwareService {
	
	List<Map<String, Object>> getHardwareGroup(Map<String, String> map);
	Integer addHardwareGroup(Map<String, String> map);
	Integer deleteHardwareGroup(Map<String, String> map);
	List<Map<String, Object>> getGroups();
	
	List<Map<String, Object>> getHardware(Map<String, String> map);
	Map<String, Object> getById(Map<String, String> map);
	Integer addHardware(Map<String, String> map);
	Integer deleteHardware(Map<String, String> map);
}
