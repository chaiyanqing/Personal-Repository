package com.zs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface HardwareDao {
	@Select("<script>"
			+ "select g.*, (select count(1) from hardware m where m.group = g.name) counts from hgroup g "
			+ "<where>"
			+ "<if test=\"groupName != null and groupName != ''\">"
			+ " and g.name = #{groupName} "
			+ "</if>"
			+ "</where>"
			+ "</script>")
    List<Map<String, Object>> getHardwareGroup(@Param("groupName")String groupName);
	@Select("select * from `hgroup`")
	public List<Map<String, Object>> getGroups();
	@Insert("insert into hgroup(name,create_time) values(#{groupName},now())")
	Integer addHardwareGroup(@Param("groupName")String groupName);
	@Insert("update hgroup set name = #{groupName}, create_time = now() where id = #{groupId}")
	Integer updateHardwareGroup(@Param("groupId")String groupId, @Param("groupName")String groupName);
	@Delete("delete from hgroup where id in (#{groupIds})")
	Integer deleteHardwareGroup(@Param("groupIds")String groupIds);

	@Select("<script>"
			+ "select * from hardware m "
			+ "<where>"
			+ "<if test=\"name != null and name != ''\">"
			+ " and m.name = #{name} "
			+ "</if>"
			+ "<if test=\"group != null and group != ''\">"
			+ " and m.group = #{group} "
			+ "</if>"
			+ "</where>"
			+ "</script>")
    List<Map<String, Object>> getHardware(@Param("name")String name, @Param("group")String group);
	@Select("select * from hardware m where id = #{id}")
    Map<String, Object> getById(@Param("id")String id);
	@Insert("insert into hardware(sn, `group`) values (#{map.memSn}, #{map.memGroup})")
	Integer addHardware(@Param("map")Map<String, String> map);
	@Insert("update hardware set sn = #{map.memSn} where id = #{map.memId}")
	Integer updateHardware(@Param("map")Map<String, String> map);
	@Delete("delete from hardware where id in (#{ids})")
	Integer deleteHardware(@Param("ids")String ids);

}
