package com.zs.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface MemberDao {
	@Select("<script>"
			+ "select g.*, (select count(1) from member m where m.group = g.name) counts from mgroup g "
			+ "<where>"
			+ "<if test=\"groupName != null and groupName != ''\">"
			+ " and g.name = #{groupName} "
			+ "</if>"
			+ "</where>"
			+ "</script>")
    List<Map<String, Object>> getMemberGroup(@Param("groupName")String groupName);
	@Select("select * from `mgroup`")
	public List<Map<String, Object>> getGroups();
	@Insert("insert into mgroup(name,create_time) values(#{groupName},now())")
	Integer addMemberGroup(@Param("groupName")String groupName);
	@Insert("update mgroup set name = #{groupName}, create_time = now() where id = #{groupId}")
	Integer updateMemberGroup(@Param("groupId")String groupId, @Param("groupName")String groupName);
	@Delete("delete from mgroup where id in (#{groupIds})")
	Integer deleteMemberGroup(@Param("groupIds")String groupIds);

	@Select("<script>"
			+ "select * from member m "
			+ "<where>"
			+ "<if test=\"name != null and name != ''\">"
			+ " and m.name = #{name} "
			+ "</if>"
			+ "<if test=\"group != null and group != ''\">"
			+ " and m.group = #{group} "
			+ "</if>"
			+ "</where>"
			+ "</script>")
    List<Map<String, Object>> getMember(@Param("name")String name, @Param("group")String group);
	@Select("select * from member m where id = #{id}")
    Map<String, Object> getById(@Param("id")String id);
	@Insert("insert into member(memberid, name, sex, birthyear, birthmonth, birthday, height, weight, heartrate, sn, `group`, groupseq,create_time) "
			+ "values(#{map.memId},#{map.memName},#{map.memSex},#{map.memBirthYear},#{map.memBirthMonth},#{map.memBirthDay},#{map.memHeight},"
			+ "#{map.memWeight},#{map.memHart},#{map.memSn},#{map.memGroup},#{map.memSeq},now())")
	Integer addMember(@Param("map")Map<String, String> map);
	@Insert("update member set memberid=#{map.memId}, name=#{map.memName}, sex=#{map.memSex}, birthyear=#{map.memBirthYear}, birthmonth=#{map.memBirthMonth}, "
			+ "birthday=#{map.memBirthDay}, height=#{map.memHeight}, weight=#{map.memWeight}, heartrate=#{map.memHart}, sn=#{map.memSn}, `group`=#{map.memGroup}, "
			+ "groupseq=#{map.memSeq} where id = #{map.memberId}")
	Integer updateMember(@Param("map")Map<String, String> map);
	@Delete("delete from member where id in (#{ids})")
	Integer deleteMember(@Param("ids")String ids);
	
}
