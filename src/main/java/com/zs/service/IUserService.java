/**
 * 
 */
package com.zs.service;

import java.util.Map;

import com.zs.entity.ResponseResult;

/**
 * @ProjectName om
 * @File com.zs.service.IUserService.java
 * @Author Yanqing
 * @Date 2018年4月16日 下午4:21:14
 * @Version V1.0
 */
public interface IUserService {

	public ResponseResult login(String userName, String password);
	public String updatePassword(Map<String, String> map);
	public String updatePassword(String account, String newPassword,String confirmPassword);
	
}
