package com.zs.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zs.dao.AbodyplusDao;
import com.zs.dao.MemberDao;
import com.zs.dao.TrainDao;
import com.zs.service.AbodyplusService;
import com.zs.util.ToolsUtils;

/**
 * @author ant
 */
@Service("abodyplusService")
public class AbodyplusServiceImpl implements AbodyplusService {
	
	private Logger logger = Logger.getLogger(AbodyplusServiceImpl.class);
	
	@Resource
	private  AbodyplusDao  abodyplusDao;
	@Resource
	private  MemberDao  memberDao;
	@Resource
	private  TrainDao  trainDao;
	
	public List<Map<String, Object>> getGroups(){
		return abodyplusDao.getGroups();
	}
	
	public List<Map<String, Object>> getLabels(){
		return abodyplusDao.getLabels();
	}
	// 本周训练负荷
	public Collection<String> trainLoadSearch(Map<String, String> map){
		String groupName = map.get("groupName");
		List<Map<String, Object>> dataList = abodyplusDao.groupTrainLoadWeek(groupName);
		// ["一","二","三","四","五","六","日"]   1,2,3,4,5,6,7
		Map<String, String> rst = new HashMap<String, String>();
		for (int i = 1; i <= 7; i++) {
			String index = String.valueOf(i);
			rst.put(index, "0");
			dataList.forEach(item -> { // 3,5,7
				String dayOfWeek = item.get("dayOfWeek").toString();
				String trainLoads = item.get("trainLoads").toString();
				if(index.equals(dayOfWeek)) {
					rst.put(index, trainLoads);
				}
				return;
			});
		}
		return rst.values();
	}
	
	public JSONObject groupStatView(Map<String, String> map){
		JSONObject result = new JSONObject();
		// 组数据汇总
		Map<String, Object> totalData = abodyplusDao.groupTotalStat(map);
		if(totalData == null) {
			totalData = new HashMap<String, Object>();
			totalData.put("tot_member", "");
			totalData.put("avg_count", "");
			totalData.put("avg_time", "");
			totalData.put("avg_load", "");
		}
		result.put("totalData", totalData);
		// 组成员明细
		List<Map<String, Object>> memberData = abodyplusDao.groupMemberStat(map);
		result.put("memberData", memberData);
		return result;
	}
	
	public JSONObject memberView(Map<String, String> map){
		String cycle = map.get("cycle");
		JSONObject result = new JSONObject();
		// 基本汇总信息
		Map<String, Object> totalData = abodyplusDao.memberViewStat(map);
		// 训练负荷分布
		List<String> totalDataDistribe = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			totalDataDistribe.add(String.valueOf(new Random().nextInt(5)));
		}
		// 周训练数据
		List<Map<String, Object>> rstList1 = abodyplusDao.getMemberTrainDatas(map);
		String[] xAxisData = new String[]{};
		List<String> rst1 = new ArrayList<String>();
		List<String> rst2 = new ArrayList<String>();
		List<String> rst3 = new ArrayList<String>();
		if("W".equals(cycle)) {
			xAxisData = new String[]{"一", "二", "三", "四", "五", "六", "日"}; 
			for (int i = 0; i < xAxisData.length; i++) {
				int dataInex = i;
				String day = String.valueOf(i + 1);
				rst1.add("0");
				rst2.add("0");
				rst3.add("0");
				rstList1.forEach(item -> { // 3,5,7
					String dayOfWeek = item.get("dayOfWeek").toString();
					String trainLoads = item.get("trainLoads").toString();
					String trainCalories = item.get("trainCalories").toString();
					String trainIsitys = item.get("trainIntensitys").toString();
					if(day.equals(dayOfWeek)) {
						rst1.set(dataInex, trainLoads);
						rst2.set(dataInex, trainCalories);
						rst3.set(dataInex, trainIsitys);
						return;
					}
				});
			}
		}
		// 月训练数据
		else if("M".equals(cycle)) {
			String cycleDate = map.get("cycleDate").replace("年", "-").replace("月", "");
			int year = Integer.parseInt(cycleDate.split("-")[0]);
			int month = Integer.parseInt(cycleDate.split("-")[1]);
			int maxDate = ToolsUtils.getMonthDays(year, month);
			xAxisData = new String[maxDate];
			for (int i = 0; i < maxDate; i++) {
				int dataInex = i;
				xAxisData[i] = String.valueOf(i + 1);
				String date = String.valueOf(i);
				rst1.add("0");
				rst2.add("0");
				rst3.add("0");
				rstList1.forEach(item -> { // 8 10 11
					String dayOfMonth = item.get("dayOfMonth").toString();
					String trainLoads = item.get("trainLoads").toString();
					String trainCalories = item.get("trainCalories").toString();
					String trainIsitys = item.get("trainIntensitys").toString();
					if(date.equals(dayOfMonth)) {
						rst1.set(dataInex, trainLoads);
						rst2.set(dataInex, trainCalories);
						rst3.set(dataInex, trainIsitys);
						return;
					}
				});
			}
			System.out.println(rst1);
			System.out.println(rst2);
			System.out.println(rst3);
		}
		// 年训练数据
		else if("Y".equals(cycle)) {
			xAxisData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}; 
			for (int i = 0; i < xAxisData.length; i++) {
				int dataInex = i;
				String month = String.valueOf(i + 1);
				rst1.add("0");
				rst2.add("0");
				rst3.add("0");
				rstList1.forEach(item -> { // 3,5,7
					String monthOfYear = item.get("monthOfYear").toString();
					String trainLoads = item.get("trainLoads").toString();
					String trainCalories = item.get("trainCalories").toString();
					String trainIsitys = item.get("trainIntensitys").toString();
					if(month.equals(monthOfYear)) {
						rst1.set(dataInex, trainLoads);
						rst2.set(dataInex, trainCalories);
						rst3.set(dataInex, trainIsitys);
						return;
					}
				});
			}
		}
		if(totalData == null) {
			totalData = new HashMap<String, Object>();
			totalData.put("counts", "");
			totalData.put("times", "");
			totalData.put("calories", "");
			totalData.put("loads", "");
		}
		result.put("totalData", totalData);	
		result.put("loadData", totalDataDistribe);		// 负荷分布
		result.put("loadData", rst1);		// 负荷
		result.put("calorieData", rst2);	// 卡路里
		result.put("intensityData", rst3); // 训练强度
		result.put("xAxis", xAxisData); 		// 橫坐标
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public Integer createTrainData() {
		// 查询成员列表
		List<Map<String, Object>> members = memberDao.getMember(null, null);
		// 随机获取一半的成员进行模拟训练（产生训练数据）
		int maxIndex = members.size() / 2;
		Random random = new Random();
		for (int i = 0; i < maxIndex; i++) {
			Map<String, Object> member = members.get(i);
			Map<String, Object> bean = new HashMap<String, Object>();
			bean.put("memberId", member.get("id"));
			Date dateTime = new Date(); 
			bean.put("startTime", ToolsUtils.formatDate(dateTime, ToolsUtils.formatePattern));
			dateTime.setMinutes(dateTime.getMinutes() + random.nextInt(60));
			bean.put("endTime", ToolsUtils.formatDate(dateTime, ToolsUtils.formatePattern));
			bean.put("label", "");
			bean.put("calorie", (int)(random.nextFloat() * 5000));
			bean.put("load", random.nextInt(5));
			bean.put("injury", random.nextInt(5));
			bean.put("intensity", random.nextInt(5));
			trainDao.addTrainData(bean);
		}
		return 1;
	}
	
	public void doSomething(String message) {
		try {
			System.out.println("message is " + message);
			JSONObject object = JSONObject.parseObject(message);
			String type = object.getString("type");
			if(object.containsKey("type")) {
				String sn = object.getString("sn");
				String status = object.getString("status");
				Long time = object.containsKey("time") ? object.getLong("time") : Calendar.getInstance().getTime().getTime();
//				String stime = ToolsUtils.formatDate(new Date(time), ToolsUtils.formatePattern);
				if("device".equals(type)) { // 1. 设备
					/*
					 {
						    "type": "device"
						    "sn": "123456", //设备编号
						    "status": "start | stop", //设备开始或者结束
						    "time":1603259269855 //时间戳
					  }
					 */
					if("start".equals(status)) {
						trainDao.saveTrainRecord(sn, status, new Date(time));
					}else if("stop".equals(status)) {
						trainDao.updateTrainRecord(sn, status, new Date(time));
					}else {
						logger.info("设备状态异常：" + status);
					}
				}else if("ecg".equals(type)) { // 2. 心电
					/*
					 {
					    "type": "ecg"
					    "sn": "123456", //设备编号
						"value":[0,0,0,0……0],  //心电数据
						"len": 32, 心电数组长度， 可变
					    "time"：1603259269855 //时间戳
					 }
					*/
					JSONArray value = object.getJSONArray("value");
					Integer len = object.getInteger("len");
					trainDao.saveTrainDataEcg(sn, type, value.toString(), new Date(time), len);					
				}else if("hr".equals(type)) { // 3. 心率
					/*
					 {
					    "type": "hr"
					    "sn": "123456", //设备编号
						"value":88,  //心率
					    "time"：1603259269855 //时间戳
					 }
					 */
					Integer value = object.getInteger("value");
					trainDao.saveTrainDataHr(sn, type, value+"", new Date(time), null);
				}else if("temp".equals(type)) { // 4. 体温
					/*
					 {
					    "type": "temp"
					    "sn": "123456", //设备编号
						"value":36.5,  //当前体温
					    "time"：1603259269855 //时间戳
					 }
					 */
					String value = object.getString("value");
					trainDao.saveTrainDataTemp(sn, type, value, new Date(time), null);
				}else if("breathe".equals(type)) { // 5. 呼吸
					/*
					 {
					    "type": "breathe"
					    "sn": "123456", //设备编号
						"value":18, //呼吸率，次/每分钟
					    "time"：1603259269855 //时间戳
					 }
					 */
					String value = object.getString("value");
					trainDao.saveTrainDataBreathe(sn, type, value, new Date(time), null);
				}else if("Power".equals(type)) { // 卡路里
					// {"sn":"100086","time":1624535049500,"type":"Power","value":10.0}
					Double value = object.getDouble("value");
					trainDao.saveTrainDataPower(sn, type, value.toString(), new Date(time), null);
				}else if("StrideFrequency".equals(type)) { // 步频
					// {"sn":"100086","time":1624535049496,"type":"StrideFrequency","value":54.0}
					Integer value = object.getInteger("value");
					trainDao.saveTrainDataStride(sn, type, value.toString(), new Date(time), null);
				}else if("bat".equals(type)) { // 
					// {"deviceType":"repeater","sn":"100086","time":1624535069198,"type":"bat","value":10}
					
				}else {
					logger.info("未知的数据种类。");
				}
			}else {
				logger.info("数据结构异常。");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
