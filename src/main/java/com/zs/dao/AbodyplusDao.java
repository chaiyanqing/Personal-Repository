package com.zs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.zs.entity.SqlProvider;

public interface AbodyplusDao {
	
	@Select("select * from `mgroup`")
	public List<Map<String, Object>> getGroups();
	
	@Select("select * from `label`")
	public List<Map<String, Object>> getLabels();
	
	// 获取本周训练负荷
	@SelectProvider(type = SqlProvider.class, method = "groupTrainLoadWeekSql")
	public List<Map<String, Object>> groupTrainLoadWeek(@Param("groupName")String groupName);

	@SelectProvider(type = SqlProvider.class, method = "groupTotalStatSql")
	public Map<String, Object> groupTotalStat(@Param("params")Map<String, String> params);

	@SelectProvider(type = SqlProvider.class, method = "groupMemberStatSql_New")
	public List<Map<String, Object>> groupMemberStat(@Param("params")Map<String, String> params);

	@SelectProvider(type = SqlProvider.class, method = "memberViewStatSql_New")
	public Map<String, Object> memberViewStat(@Param("params")Map<String, String> params);
	
	@SelectProvider(type = SqlProvider.class, method = "getMemberTrainDatasSql_New")
	public List<Map<String, Object>> getMemberTrainDatas(@Param("params")Map<String, String> params);

}
