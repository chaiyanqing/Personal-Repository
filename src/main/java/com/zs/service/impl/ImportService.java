package com.zs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zs.dao.HardwareDao;
import com.zs.dao.MemberDao;
import com.zs.service.IImportService;

@Service("importService")
public class ImportService extends BaseService implements IImportService{
	@Resource
	private MemberDao memberDao;
	
	@Resource
	private HardwareDao hardwareDao;
	
	public List<String> importMemberMuiltiSimple(List<ArrayList<String>> list, String groupName, Integer fromIndex) {
		List<String> noImportedData = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
        	try {
	        	List<String> dataItem = list.get(i);
	        	String memberId = dataItem.get(1);
	        	String name = dataItem.get(2);
	        	// 1.验证ID
	        	if(StringUtils.isEmpty(memberId)){
	        		noImportedData.add("第"+(fromIndex+i+2)+"行：成员ID为空，请核对数据。");
	        		continue;
	        	}
	        	if(StringUtils.isEmpty(name)){
	        		noImportedData.add("第"+(fromIndex+i+2)+"行：成员姓名为空，请核对数据。");
	        		continue;
	        	}
	        	try {
	        		Map<String, String> bean = new HashMap<>();
	        		bean.put("memId", dataItem.get(1));
    				bean.put("memName", dataItem.get(2));
    				bean.put("memSex", dataItem.get(3));
    				bean.put("memBirthYear", dataItem.get(4));
    				bean.put("memBirthMonth", dataItem.get(5));
    				bean.put("memBirthDay", dataItem.get(6));
    				bean.put("memHeight", dataItem.get(7));
    				bean.put("memWeight", dataItem.get(8));
    				bean.put("memHart", dataItem.get(9));
    				bean.put("memSn", dataItem.get(10));
    				bean.put("memGroup", groupName);
	    			memberDao.addMember(bean);
	        	}catch(Exception e) {
	        		System.out.println(e.getMessage());
	        		noImportedData.add("第"+(fromIndex+i+2)+"行：成员数据导入失败，" + e.getMessage());
	        		continue;
	        	}
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
        		noImportedData.add("第"+(fromIndex+i+2)+"行：数据验证异常，" + e.getMessage());
        		continue;
        	}
        }
        return noImportedData;
	}
	
	public List<String> importHardwareMuiltiSimple(List<ArrayList<String>> list, String groupName, Integer fromIndex) {
		List<String> noImportedData = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
        	try {
	        	List<String> dataItem = list.get(i);
	        	String sn = dataItem.get(1);
	        	if(StringUtils.isEmpty(sn)){
	        		noImportedData.add("第"+(fromIndex+i+2)+"行：硬件序列号码为空，请核对数据。");
	        		continue;
	        	}
	        	try {
	        		Map<String, String> bean = new HashMap<>();
    				bean.put("memSn", dataItem.get(1));
    				bean.put("memGroup", groupName);
    				hardwareDao.addHardware(bean);
	        	}catch(Exception e) {
	        		System.out.println(e.getMessage());
	        		noImportedData.add("第"+(fromIndex+i+2)+"行：硬件设备数据导入失败，" + e.getMessage());
	        		continue;
	        	}
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
        		noImportedData.add("第"+(fromIndex+i+2)+"行：数据验证异常，" + e.getMessage());
        		continue;
        	}
        }
        return noImportedData;
	}
	
}
