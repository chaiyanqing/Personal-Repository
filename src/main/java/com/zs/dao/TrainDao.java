package com.zs.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface TrainDao {
	
	@Select("<script>"
			+ "select t.*,"
			+ "case when start_time is null or start_time &gt; now() then '未开始' when start_time &lt; now() and end_time is null then '进行中' when end_time &lt; now() then '已结束' end status1,"
			+ "TIMESTAMPDIFF(MINUTE, start_time, end_time) timeDiff, (select count(1) from member m where m.group = t.team) memCount from traintask t"
			+ "</script>")
    List<Map<String, Object>> search(@Param("params")Map<String, String> params);
	
	@Select("<script>"
			+ "select m.id,m.name,m.sn,m.heartrate,t.name taskname,t.team from traintask t left join member m on t.team = m.group where t.id = #{trainId} and m.sn is not null"
			+ "</script>")
    List<Map<String, Object>> trainMember(@Param("trainId")String trainId);
	
	@Insert("insert into traintask(name, address, team, model, status, start_time, end_time) "
			+ "values (#{map.traName}, #{map.traAddress}, #{map.traTeam}, #{map.traModel}, 0, #{map.traStartTime}, #{map.traEndTime})")
	Integer addTrain(@Param("map")Map<String, String> map);
	
	@Update("update traintask set status = 1, start_time = now() where id = #{id}")
	Integer startTrain(@Param("id")String id);
	
	@Update("update traintask set status = 2, end_time = now() where id = #{id}")
	Integer endTrain(@Param("id")String id);
	
	/**
	 * select mid,memName,heartrate,height,weight,nsn,max(hrval),max(tempval),max(breatheval) from (select mid, memName,nsn,heartrate,height,weight,case when type='hr' then value else '' end hrval,case when type='temp' then value else '' end tempval,case when type='breathe' then value else '' end breatheval from (select t1.name trainName,m.id mid, m.name memName,m.sn nsn,m.heartrate,m.height,m.weight, t2.* from traintask t1 left join member m on t1.team = m.group left join traindata t2 on t2.sn = m.sn where t1.status = 1 and t1.start_time < t2.trainDate) t1_ group by t1_.nsn, t1_.type) t2_ group by t2_.mid;
	 * 
	 * (select t.id trainId,t.name trainName,m.id memId,m.sn,m.name memName,m.sex,m.height,m.weight,m.heartrate from traintask t left join member m on t.team = m.group where status = 1)"
	 * @param params
	 * @return
	 */
//	@Select("<script>"
//			+ "select mid,memName,heartrate,round(max(hrval)/heartrate*100, 0) hrpercent,height,weight,nsn,max(hrval) hrval,max(tempval) tempval,max(breatheval) breatheval from " + 
//			"	(select trainName, mid, memName,nsn,heartrate,height,weight,avg(case when type='hr' then value else '' end) hrval,avg(case when type='temp' then value else '' end) tempval,avg(case when type='breathe' then value else '' end) breatheval from (" + 
//			"		select t1.name trainName,m.id mid, m.name memName,m.sn nsn,m.heartrate,m.height,m.weight, t2.* from traintask t1 left join member m on t1.team = m.group left join traindata t2 on t2.sn = m.sn where (t1.start_time &lt; t2.trainDate or t2.trainDate is null) " + 
//			"		and (t1.end_time &gt; t2.trainDate or t1.end_time is null) and t1.id=#{trainId}" + 
//			"	) t1_ group by t1_.nsn, t1_.type ) " + 
//			"t2_ group by t2_.mid "
//			+ ""
//			+ "</script>")
//	@Select("<script>"
//			+ "select t1.id trainId,t1.name trainName,t1.start_time, t1.end_time,m.id memId, m.name memName,m.sn memSn,m.heartrate,m.height,m.weight,round(avg(v1.value),0) hrval,"
//			+ "max(v1.value) hrvalmax,round(avg(v2.value),0) tempval,round(avg(v3.value),0) breatheval,round(avg(ifnull(v1.value,0))/heartrate*100, 0) hrpercent, max(v4.value) caloryval " + 
//			  "from traintask t1 inner join member m on t1.team = m.group " + 
//			  "left join traindata_hr v1 on (v1.sn = m.sn and v1.trainDate &gt; t1.start_time and (v1.trainDate &lt; t1.end_time or t1.end_time is null)) " + 
//			  "left join traindata_temp v2 on (v2.sn = m.sn and v2.trainDate &gt; t1.start_time and (v2.trainDate &lt; t1.end_time or t1.end_time is null)) " + 
//			  "left join traindata_breathe v3 on (v3.sn = m.sn and v3.trainDate &gt; t1.start_time and (v3.trainDate &lt; t1.end_time or t1.end_time is null)) " + 
//			  "left join traindata_power v4 on (v4.sn = m.sn and v4.trainDate &gt; t1.start_time and (v4.trainDate &lt; t1.end_time or t1.end_time is null)) " + 
//			  "where t1.id=#{trainId} group by m.sn"
//			+ "</script>")
	@Select("<script>"
			+ "select t1.id trainId,t1.name trainName,t1.start_time, t1.end_time,m.id memId, m.name memName,m.sn memSn,m.heartrate,m.height,m.weight,"
			+ "(select round(avg(v1.value),0) from traindata_hr v1 where v1.sn = m.sn and v1.trainDate &gt; t1.start_time and (v1.trainDate &lt; t1.end_time or t1.end_time is null)) hrval,"
			+ "(select max(v1.value) from traindata_hr v1 where v1.sn = m.sn and v1.trainDate &gt; t1.start_time and (v1.trainDate &lt; t1.end_time or t1.end_time is null)) hrvalmax,"
			+ "(select round(avg(v2.value),0) from traindata_temp v2 where v2.sn = m.sn and v2.trainDate &gt; t1.start_time and (v2.trainDate &lt; t1.end_time or t1.end_time is null)) tempval,"
			+ "(select round(avg(v3.value),0) from traindata_breathe v3 where v3.sn = m.sn and v3.trainDate &gt; t1.start_time and (v3.trainDate &lt; t1.end_time or t1.end_time is null)) breatheval,"
			+ "(select round(avg(ifnull(v1.value,0))/heartrate*100, 0) from traindata_hr v1 where v1.sn = m.sn and v1.trainDate &gt; t1.start_time and (v1.trainDate &lt; t1.end_time or t1.end_time is null)) hrpercent,"
			+ "(select max(v4.value) from traindata_power v4 where v4.sn = m.sn and v4.trainDate > t1.start_time and (v4.trainDate &lt; t1.end_time or t1.end_time is null)) caloryval "
			+ "from traintask t1 "
			+ "inner join member m on t1.team = m.group "
			+ "where t1.id=#{trainId} "
			+ "group by m.sn"
			+ "</script>")
    List<Map<String, Object>> monitor(@Param("trainId")String trainId);
	
	@Select("<script>"
			+ "select * from (select value, date_format(traindate,'%H:%i') traindate "
			+ "from traindata_hr "
			+ "where sn = #{sn} "
			+ "and traindate &gt; (select start_time from traintask where id = #{trainId}) "
			+ "and traindate &lt; (select ifnull(end_time, now()) from traintask where id = #{trainId}) "
			+ "order by id desc limit 180) t_ order by t_.traindate asc"
			+ "</script>")
	List<Map<String, Object>> getMemberHrs(@Param("trainId")String trainId, @Param("sn")String sn);
	
	

	@Insert("insert into traindetail(memberid, start_time, end_time, label, calorie, `load`, injury, intensity) "
			+ "values(#{map.memberId},#{map.startTime},#{map.endTime},#{map.label},#{map.calorie},#{map.load},#{map.injury},#{map.intensity})")
	Integer addTrainData(@Param("map")Map<String, Object> map);
	
	
	@Insert("insert into trainrecord(sn, start_time, status) "
			+ "values(#{sn},#{time},#{status})")
	Integer saveTrainRecord(@Param("sn")String sn,@Param("status")String status,@Param("time")Date time);
	@Insert("update trainrecord set end_time=#{time}, status=#{status} where sn=#{sn} and end_time is null ")
	Integer updateTrainRecord(@Param("sn")String sn,@Param("status")String status,@Param("time")Date time);
	
	/**
	 * 训练数据保存，心电、心率、体温、呼吸、卡路里、步频单独存储，
	 * @param sn
	 * @param type
	 * @param value
	 * @param traindate
	 * @param len
	 * @return
	 */
	@Insert("insert into traindata(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainData(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	@Insert("insert into traindata_ecg(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainDataEcg(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	@Insert("insert into traindata_hr(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainDataHr(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	@Insert("insert into traindata_temp(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainDataTemp(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	@Insert("insert into traindata_breathe(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainDataBreathe(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	@Insert("insert into traindata_power(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainDataPower(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	@Insert("insert into traindata_stride(sn, type, value, traindate, len) "
			+ "values(#{sn},#{type},#{value},#{traindate},#{len})")
	Integer saveTrainDataStride(@Param("sn")String sn,@Param("type")String type,@Param("value")String value,@Param("traindate")Date traindate,@Param("len")Integer len);
	
	
}
