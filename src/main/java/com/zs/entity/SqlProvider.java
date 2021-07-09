package com.zs.entity;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.zs.util.ToolsUtils;

public class SqlProvider {
	
	@SuppressWarnings("deprecation")
	public String groupTrainLoadWeekSql(Map<String, String> params) {
		String groupName = params.get("groupName");
		SQL sql = new SQL();
		sql.SELECT("date_format(t.start_time, '%Y-%m-%d') trainDate, case when dayofweek(t.start_time) = 1 then 7 else dayofweek(t.start_time) - 1 end as dayOfWeek, sum(ifnull(t.load, 0)) trainLoads ");
		sql.FROM("traindetail t ");
		sql.LEFT_OUTER_JOIN("member m on m.id = t.memberid ");
		if(!"".equals(groupName)) {
			sql.WHERE("m.group = '" + groupName + "' ");
		}
		// 获取当前周的周一到周日日期
		try {
			Date date = new Date();
			Date start = new Date();
			Date end = new Date();
			if(date.getDay() == 0) { // 周日为0单独计算
				start.setDate(date.getDate() - 6);
				end.setDate(date.getDate() + 0);
			}else {
				start.setDate(date.getDate() - (date.getDay() - 1));
				end.setDate(date.getDate() + (7 - date.getDay()));
			}
			String startDate = ToolsUtils.formatDate(start, ToolsUtils.formatePattern2);
			String endDate = ToolsUtils.formatDate(end, ToolsUtils.formatePattern2);
			sql.WHERE("t.start_time >= '" + startDate + "' and t.end_time <= '" + endDate + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.GROUP_BY("date_format(t.start_time, '%Y-%m-%d') ");
		sql.ORDER_BY("t.start_time");
		System.out.println(sql);
		return sql.toString();
	}

	public String groupTotalStatSql(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String label = param.get("label");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String groupName = param.get("groupName");
		SQL subsql = new SQL();
		subsql.SELECT("m.id, m.memberid, m.name, count(t.id) as counts, max(t.injury) as injury, ifnull(hour(timediff(t.start_time,t.end_time))+minute(timediff(t.start_time,t.end_time))/60, 0) as times, sum(ifnull(t.load,0)) as loads ");
		subsql.FROM("member m ");
		subsql.LEFT_OUTER_JOIN("traindetail t on m.id = t.memberid ");
		subsql.WHERE("m.group = '" + groupName + "' ");		
		if(!"".equals(label)) {
			subsql.WHERE("t.label = #{params.label} ");		
		}
		if(!"".equals(cycle)) {
			if("W".equals(cycle)) {
				String startDate = cycleDate.split("—")[0];
				String endDate = cycleDate.split("—")[1];
				// 2021年4月5日—2021年4月11日
				try {
					startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
					endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
					subsql.WHERE("t.start_time >= '" + startDate + "' and t.end_time <= '" + endDate + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("M".equals(cycle)) {
				// 2021年4月
				try {
					String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
					subsql.WHERE("DATE_FORMAT(t.start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(t.end_time, '%Y-%m') = '" + searchMonth + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("Y".equals(cycle)) {
				// 2021年
				try {
					String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
					subsql.WHERE("DATE_FORMAT(t.start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(t.end_time, '%Y') = '" + searchYear + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		subsql.GROUP_BY("m.id");
		
		String columns_sql = "count(d.memberid) tot_member, avg(d.counts) avg_count, avg(d.times) avg_time, avg(d.loads) avg_load";
		SQL sql = new SQL().SELECT(columns_sql).FROM("(" + subsql.toString() + ") d");
		System.out.println(sql);
		return sql.toString();
	}
	
	public String groupMemberStatSql(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String label = param.get("label");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String groupName = param.get("groupName");
		SQL sql = new SQL();
		sql.SELECT("m.id, m.memberid, m.name, count(t.id) as counts, max(t.injury) as injury, ifnull(hour(timediff(t.start_time,t.end_time))+minute(timediff(t.start_time,t.end_time))/60, 0) as times, sum(ifnull(t.load,0)) as loads ");
		sql.FROM("member m ");
		
		SQL subsql = new SQL();
		subsql.SELECT(" * ");
		subsql.FROM("traindetail ");
		if(!"".equals(label)) {
			subsql.WHERE("label = #{params.label} ");		
		}
		if(!"".equals(cycle)) {
			if("W".equals(cycle)) {
				String startDate = cycleDate.split("—")[0];
				String endDate = cycleDate.split("—")[1];
				// 2021年4月5日—2021年4月11日
				try {
					startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
					endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
					subsql.WHERE("start_time >= '" + startDate + "' and end_time <= '" + endDate + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("M".equals(cycle)) {
				// 2021年4月
				try {
					String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
					subsql.WHERE("DATE_FORMAT(start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(end_time, '%Y-%m') = '" + searchMonth + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("Y".equals(cycle)) {
				// 2021年
				try {
					String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
					subsql.WHERE("DATE_FORMAT(start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(end_time, '%Y') = '" + searchYear + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		sql.LEFT_OUTER_JOIN("(" + subsql.toString() + ") t on m.id = t.memberid ");
		sql.WHERE("m.group = '" + groupName + "' ");
		sql.GROUP_BY("m.id");
		System.out.println(sql);
		return sql.toString();
	}
	
	public String groupMemberStatSql_New(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String label = param.get("label");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String groupName = param.get("groupName");
		
		String condsqlv = "";
		String condsqlt = "";
		if(!"".equals(label)) {
			//subsql.WHERE("label = #{params.label} ");		
		}
		if(!"".equals(cycle)) {
			if("W".equals(cycle)) {
				String startDate = cycleDate.split("—")[0];
				String endDate = cycleDate.split("—")[1];
				// 2021年4月5日—2021年4月11日
				try {
					startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
					endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
					condsqlv = "and v.start_time >= '" + startDate + "' and v.end_time <= '" + endDate + "'";
					condsqlt = "and t.start_time >= '" + startDate + "' and t.end_time <= '" + endDate + "'";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("M".equals(cycle)) {
				// 2021年4月
				try {
					String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
					condsqlv = "and DATE_FORMAT(v.start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(v.end_time, '%Y-%m') = '" + searchMonth + "'";
					condsqlt = "and DATE_FORMAT(t.start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(t.end_time, '%Y-%m') = '" + searchMonth + "'";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("Y".equals(cycle)) {
				// 2021年
				try {
					String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
					condsqlv = "and DATE_FORMAT(v.start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(v.end_time, '%Y') = '" + searchYear + "'";
					condsqlt = "and DATE_FORMAT(t.start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(t.end_time, '%Y') = '" + searchYear + "'";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		StringBuffer subsql = new StringBuffer();
		subsql.append("select m.id, m.sn, m.memberid, m.name, null injury, null loads, ");
		subsql.append("(select count(1) from trainrecord v where v.sn = t.sn " + condsqlv+") counts,");
		subsql.append("(select sum(hour(timediff(v.start_time, v.end_time))+minute(timediff(v.start_time, v.end_time))/60) from trainrecord v where v.sn = m.sn "+condsqlv+") times ");
		subsql.append("from member m left join trainrecord t on t.sn = m.sn ");
		subsql.append("where m.group = '"+groupName+"'").append(" group by m.id");
		System.out.println(subsql.toString());
		return subsql.toString();
	}
	
	public String memberViewStatSql(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String memberId = param.get("memberId");
		SQL subsql = new SQL();
		subsql.SELECT("count(id) as counts, sum(calorie) as calories, sum(`load`) as loads, ifnull(hour(timediff(start_time,end_time))+minute(timediff(start_time,end_time))/60, 0) as times ");
		subsql.FROM("traindetail ");
		subsql.WHERE("memberId = '" + memberId + "' ");
		if(!"".equals(cycle)) {
			if("W".equals(cycle)) {
				String startDate = cycleDate.split("—")[0];
				String endDate = cycleDate.split("—")[1];
				// 2021年4月5日—2021年4月11日
				try {
					startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
					endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
					subsql.WHERE("start_time >= '" + startDate + "' and end_time <= '" + endDate + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("M".equals(cycle)) {
				// 2021年4月
				try {
					String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
					subsql.WHERE("DATE_FORMAT(start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(end_time, '%Y-%m') = '" + searchMonth + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("Y".equals(cycle)) {
				// 2021年
				try {
					String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
					subsql.WHERE("DATE_FORMAT(start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(end_time, '%Y') = '" + searchYear + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(subsql);
		return subsql.toString();
	}
	
	public String memberViewStatSql_New(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String memberId = param.get("memberId");
		String condsqlc = "";
		String condsqld = "";
		String condsqlt = "";
		if(!"".equals(cycle)) {
			if("W".equals(cycle)) {
				String startDate = cycleDate.split("—")[0];
				String endDate = cycleDate.split("—")[1];
				// 2021年4月5日—2021年4月11日
				try {
					startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
					endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
					condsqlc = "and v.start_time >= '" + startDate + "' and v.end_time <= '" + endDate + "'";
					condsqld = "and v.traindate >= '" + startDate + "' and v.traindate <= '" + endDate + "'";
					condsqlt = "and t.start_time >= '" + startDate + "' and t.start_time <= '" + endDate + "'";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("M".equals(cycle)) {
				// 2021年4月
				try {
					String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
					condsqlc = "and DATE_FORMAT(v.start_time, '%Y-%m') = '" + searchMonth + "' and (DATE_FORMAT(v.end_time, '%Y-%m') = '" + searchMonth + "' or v.end_time is null)";
					condsqld = "and DATE_FORMAT(v.traindate, '%Y-%m') = '" + searchMonth + "' ";
					condsqlt = "and DATE_FORMAT(t.start_time, '%Y-%m') = '" + searchMonth + "' ";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("Y".equals(cycle)) {
				// 2021年
				try {
					String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
					condsqlc = "and DATE_FORMAT(v.start_time, '%Y') = '" + searchYear + "' and (DATE_FORMAT(v.end_time, '%Y') = '" + searchYear + "' or v.end_time is null) ";
					condsqld = "and DATE_FORMAT(v.traindate, '%Y') = '" + searchYear + "' ";
					condsqlt = "and DATE_FORMAT(t.start_time, '%Y') = '" + searchYear + "' ";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		String vsql1 = "select count(1) from trainrecord v where v.sn = t.sn " + condsqlc;
		String vsql2 = "select max(value) from traindata_power v where v.sn = t.sn " + condsqld;
		String vsql3 = "select sum(hour(timediff(v.start_time, v.end_time))+minute(timediff(v.start_time, v.end_time))/60) from trainrecord v where v.sn = t.sn " + condsqlc;
		StringBuffer subsql = new StringBuffer();
		subsql.append("select m.id, m.sn, ");
		subsql.append("(").append(vsql1).append(") counts, ");
		subsql.append("(").append(vsql2).append(") calories, ");
		subsql.append("(").append(vsql3).append(") times ");
		subsql.append("from trainrecord t left join member m on t.sn = m.sn where m.id = '" + memberId + "' ");
		subsql.append(condsqlt).append(" limit 1");
		
		System.out.println(subsql);
		return subsql.toString();
	}
	
	public String getMemberTrainDatasSql(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String memberId = param.get("memberId");
		SQL sql = new SQL();
		if("W".equals(cycle)) {
			String startDate = cycleDate.split("—")[0];
			String endDate = cycleDate.split("—")[1];
			// 2021年4月5日—2021年4月11日
			try {
				sql.SELECT("date_format(t.start_time, '%Y-%m-%d') trainDate, case when dayofweek(t.start_time) = 1 then 7 else dayofweek(t.start_time) - 1 end as dayOfWeek, sum(ifnull(t.load, 0)) trainLoads, sum(ifnull(t.calorie, 0)) trainCalories, sum(ifnull(t.intensity, 0)) trainIntensitys ");
				startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
				endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
				sql.WHERE("start_time >= '" + startDate + "' and end_time <= '" + endDate + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("M".equals(cycle)) {
			// 2021年4月
			try {
				sql.SELECT("date_format(t.start_time, '%Y-%m-%d') trainDate, day(t.start_time) as dayOfMonth, sum(ifnull(t.load, 0)) trainLoads, sum(ifnull(t.calorie, 0)) trainCalories, sum(ifnull(t.intensity, 0)) trainIntensitys ");
				String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
				sql.WHERE("DATE_FORMAT(start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(end_time, '%Y-%m') = '" + searchMonth + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("Y".equals(cycle)) {
			// 2021年
			try {
				sql.SELECT("date_format(t.start_time, '%Y-%m') trainDate, month(t.start_time) as monthOfYear, sum(ifnull(t.load, 0)) trainLoads, sum(ifnull(t.calorie, 0)) trainCalories, sum(ifnull(t.intensity, 0)) trainIntensitys ");
				String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
				sql.WHERE("DATE_FORMAT(start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(end_time, '%Y') = '" + searchYear + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		sql.FROM("traindetail t ");
		sql.WHERE("t.memberId = '" + memberId + "' ");
		if("W".equals(cycle) || "M".equals(cycle)) {
			sql.GROUP_BY("date_format(t.start_time, '%Y-%m-%d') ");
		}else if("Y".equals(cycle)) {
			sql.GROUP_BY("date_format(t.start_time, '%Y-%m') ");
		}
		sql.ORDER_BY("t.start_time");
		System.out.println(sql);
		return sql.toString();
	}
	
	public String getMemberTrainDatasSql_New(Map<String, Map<String, String>> params){
		Map<String, String> param = (Map<String, String>) params.get("params");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		String memberId = param.get("memberId");
		SQL sql = new SQL();
		if("W".equals(cycle)) {
			String startDate = cycleDate.split("—")[0];
			String endDate = cycleDate.split("—")[1];
			// 2021年4月5日—2021年4月11日
			try {
				sql.SELECT("date_format(t.start_time, '%Y-%m-%d') trainDate, case when dayofweek(t.start_time) = 1 then 7 else dayofweek(t.start_time) - 1 end as dayOfWeek, sum(ifnull(t.load, 0)) trainLoads, sum(ifnull(t.calorie, 0)) trainCalories, sum(ifnull(t.intensity, 0)) trainIntensitys ");
				startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
				endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
				sql.WHERE("start_time >= '" + startDate + "' and end_time <= '" + endDate + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("M".equals(cycle)) {
			// 2021年4月
			try {
				sql.SELECT("date_format(t.start_time, '%Y-%m-%d') trainDate, day(t.start_time) as dayOfMonth, sum(ifnull(t.load, 0)) trainLoads, sum(ifnull(t.calorie, 0)) trainCalories, sum(ifnull(t.intensity, 0)) trainIntensitys ");
				String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
				sql.WHERE("DATE_FORMAT(start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(end_time, '%Y-%m') = '" + searchMonth + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("Y".equals(cycle)) {
			// 2021年
			try {
				sql.SELECT("date_format(t.start_time, '%Y-%m') trainDate, month(t.start_time) as monthOfYear, sum(ifnull(t.load, 0)) trainLoads, sum(ifnull(t.calorie, 0)) trainCalories, sum(ifnull(t.intensity, 0)) trainIntensitys ");
				String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
				sql.WHERE("DATE_FORMAT(start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(end_time, '%Y') = '" + searchYear + "'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		sql.FROM("traindetail t ");
		sql.WHERE("t.memberId = '" + memberId + "' ");
		if("W".equals(cycle) || "M".equals(cycle)) {
			sql.GROUP_BY("date_format(t.start_time, '%Y-%m-%d') ");
		}else if("Y".equals(cycle)) {
			sql.GROUP_BY("date_format(t.start_time, '%Y-%m') ");
		}
		sql.ORDER_BY("t.start_time");
		System.out.println(sql);
		return sql.toString();
	}
	
	public String trainDataSearch(Map<String, Map<String, String>> params) {
		Map<String, String> param = (Map<String, String>) params.get("params");
		String cycle = param.get("cycle");
		String cycleDate = param.get("cycleDate");
		SQL subsql = new SQL();
		subsql.SELECT("m.name, m.group, m.sex, t.* ");
		subsql.FROM("traindetail t ");
		subsql.LEFT_OUTER_JOIN("member m on t.memberid = m.id ");
		if(!"".equals(cycle)) {
			if("W".equals(cycle)) {
				String startDate = cycleDate.split("—")[0];
				String endDate = cycleDate.split("—")[1];
				// 2021年4月5日—2021年4月11日
				try {
					startDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(startDate), ToolsUtils.formatePattern2);
					endDate = ToolsUtils.formatDate(ToolsUtils.formateSimple1.parse(endDate), ToolsUtils.formatePattern2);
					subsql.WHERE("t.start_time >= '" + startDate + "' and t.end_time <= '" + endDate + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("M".equals(cycle)) {
				// 2021年4月
				try {
					String searchMonth = ToolsUtils.formatDate(ToolsUtils.formateSimple2.parse(cycleDate), ToolsUtils.formatePattern3);
					subsql.WHERE("DATE_FORMAT(t.start_time, '%Y-%m') = '" + searchMonth + "' and DATE_FORMAT(t.end_time, '%Y-%m') = '" + searchMonth + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if("Y".equals(cycle)) {
				// 2021年
				try {
					String searchYear = ToolsUtils.formatDate(ToolsUtils.formateSimple3.parse(cycleDate), ToolsUtils.formatePattern4);
					subsql.WHERE("DATE_FORMAT(t.start_time, '%Y') = '" + searchYear + "' and DATE_FORMAT(t.end_time, '%Y') = '" + searchYear + "'");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(subsql);
		return subsql.toString();
	}
}
