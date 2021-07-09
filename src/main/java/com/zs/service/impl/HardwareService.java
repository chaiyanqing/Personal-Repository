package com.zs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zs.dao.HardwareDao;
import com.zs.service.IHardwareService;

@Service("hardwareService")
public class HardwareService extends BaseService implements IHardwareService{
    
	@Resource
	private HardwareDao hardwareDao;

	@Override
	public List<Map<String, Object>> getHardwareGroup(Map<String, String> map) {
		String name = map.get("groupName");
		return hardwareDao.getHardwareGroup(name);
	}
	public List<Map<String, Object>> getGroups(){
		return hardwareDao.getGroups();
	}
	@Override
	public Integer addHardwareGroup(Map<String, String> map){
		String groupName = map.get("groupName");
		String groupId = map.get("groupId");
		if("".equals(groupId)) {
			return hardwareDao.addHardwareGroup(groupName);
		} else {
			return hardwareDao.updateHardwareGroup(groupId, groupName);
		}
	}

	@Override
	public Integer deleteHardwareGroup(Map<String, String> map){
		String[] ids = map.get("groupIds").split(",");
		for (int i = 0; i < ids.length; i++) {
			hardwareDao.deleteHardwareGroup(ids[i]);			
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> getHardware(Map<String, String> map) {
		String memberName = map.get("memberName");
		String groupName = map.get("groupName");
		return hardwareDao.getHardware(memberName, groupName);
	}
	@Override
	public Map<String, Object> getById(Map<String, String> map) {
		String id = map.get("id");
		return hardwareDao.getById(id);
	}

	@Override
	public Integer addHardware(Map<String, String> map) {
		String memId = map.get("memId");
		if("".equals(memId)) {
			return hardwareDao.addHardware(map);
		}else {
			return hardwareDao.updateHardware(map);
		}
	}

	@Override
	public Integer deleteHardware(Map<String, String> map) {
		String[] ids = map.get("memberIds").split(",");
		for (int i = 0; i < ids.length; i++) {
			hardwareDao.deleteHardware(ids[i]);			
		}
		return 1;
	}
	
}
