package com.zs.service;

import java.util.List;
import java.util.Map;

public interface IMemberService {
	
	List<Map<String, Object>> getMemberGroup(Map<String, String> map);
	Integer addMemberGroup(Map<String, String> map);
	Integer deleteMemberGroup(Map<String, String> map);
	List<Map<String, Object>> getGroups();
	
	List<Map<String, Object>> getMember(Map<String, String> map);
	Map<String, Object> getById(Map<String, String> map);
	Integer addMember(Map<String, String> map);
	Integer deleteMember(Map<String, String> map);
}
