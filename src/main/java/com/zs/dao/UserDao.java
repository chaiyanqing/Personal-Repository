/**
 * 
 */
package com.zs.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zs.entity.User;

/**
 * @ProjectName om
 * @File com.zs.dao.UserDao.java
 * @Author Yanqing
 * @Date 2018年4月16日 下午4:28:29
 * @Version V1.0
 */
public interface UserDao {
	
	@Select("select id, account, name, password, phone from user where account = #{account}")
	public User getUserByAccount(@Param("account")String account);
	
	@Delete("update user set password = #{password} where account = #{account}")
	public Integer updatePassword(@Param("password")String password, @Param("account")String account);
	
}
