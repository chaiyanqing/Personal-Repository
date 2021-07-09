package com.zs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zs.dao.TrainDao;
import com.zs.service.ITrainService;

@Service("trainService")
public class TrainService extends BaseService implements ITrainService{
    
	@Resource
	private TrainDao trainDao;

	@Override
	public List<Map<String, Object>> search(Map<String, String> map) {
		return trainDao.search(map);
	}
	
	@Override
	public List<Map<String, Object>> trainMember(Map<String, String> map) {
		return trainDao.trainMember(map.get("trainId"));
	}
	
	@Override
	public Integer addTrain(Map<String, String> map) {
		return trainDao.addTrain(map);
	}
	
	@Override
	public Integer startTrain(Map<String, String> map) {
		return trainDao.startTrain(map.get("trainId"));
	}
	
	@Override
	public Integer endTrain(Map<String, String> map) {
		return trainDao.endTrain(map.get("trainId"));
	}
	
	@Override
	public List<Map<String, Object>> monitor(String trainId) {
		return trainDao.monitor(trainId);
	}
	
	@Override
	public Map<String, Object> memHrList(String trainId, String sn) {
		Map<String, Object> rest = new HashMap<String, Object>();
		List<Map<String, Object>> lst = trainDao.getMemberHrs(trainId, sn);
		
		List<Object> xAxisData = new ArrayList<Object>();
		List<Object> yAxisData = new ArrayList<Object>();
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> bean = lst.get(i);
			xAxisData.add(bean.get("traindate"));
			yAxisData.add(bean.get("value"));
		}
		if(lst.size() == 0) {
			xAxisData.add("");
			yAxisData.add("");
		}
		rest.put("xdata", xAxisData);
		rest.put("ydata", yAxisData);
		return rest;
	}
}
