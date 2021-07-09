package com.zs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zs.dao.MemberDao;
import com.zs.service.IMemberService;

@Service("memberService")
public class MemberService extends BaseService implements IMemberService{
    
	@Resource
	private MemberDao memberDao;

	@Override
	public List<Map<String, Object>> getMemberGroup(Map<String, String> map) {
		String name = map.get("groupName");
		return memberDao.getMemberGroup(name);
	}
	public List<Map<String, Object>> getGroups(){
		return memberDao.getGroups();
	}
	@Override
	public Integer addMemberGroup(Map<String, String> map){
		String groupName = map.get("groupName");
		String groupId = map.get("groupId");
		if("".equals(groupId)) {
			return memberDao.addMemberGroup(groupName);
		} else {
			return memberDao.updateMemberGroup(groupId, groupName);
		}
	}

	@Override
	public Integer deleteMemberGroup(Map<String, String> map){
		String[] ids = map.get("groupIds").split(",");
		for (int i = 0; i < ids.length; i++) {
			memberDao.deleteMemberGroup(ids[i]);			
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> getMember(Map<String, String> map) {
		String memberName = map.get("memberName");
		String groupName = map.get("groupName");
		return memberDao.getMember(memberName, groupName);
	}
	@Override
	public Map<String, Object> getById(Map<String, String> map) {
		String id = map.get("id");
		return memberDao.getById(id);
	}

	@Override
	public Integer addMember(Map<String, String> map) {
		String memId = map.get("memberId");
		if("".equals(memId)) {
			return memberDao.addMember(map);
		}else {
			return memberDao.updateMember(map);
		}
	}

	@Override
	public Integer deleteMember(Map<String, String> map) {
		String[] ids = map.get("memberIds").split(",");
		for (int i = 0; i < ids.length; i++) {
			memberDao.deleteMember(ids[i]);			
		}
		return 1;
	}
	
}
