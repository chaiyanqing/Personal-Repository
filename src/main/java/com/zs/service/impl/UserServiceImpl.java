/**
 * 
 */
package com.zs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.dao.UserDao;
import com.zs.entity.ResponseResult;
import com.zs.entity.User;
import com.zs.service.IUserService;

/**
 * @ProjectName om
 * @File com.zs.service.impl.IUserService.java
 * @Author Yanqing
 * @Date 2018年4月16日 下午4:10:16
 * @Version V1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserDao userDao;
	
	public ResponseResult login(String account, String password){
		ResponseResult rst = new ResponseResult();
		User user = userDao.getUserByAccount(account);
		if(user != null){
			if(user.getPassword().equals(password)){
				// 登录成功
				rst = new ResponseResult(true, "登录成功.", user);
			}else{
				// 用户名密码不匹配
				rst = new ResponseResult(false, "用户名密码不匹配.", null);
			}
		}else{ 
			// 用户名不存在
			rst = new ResponseResult(false, "用户名不存在["+account+"].", null);
		}
		return rst;
	}

	public String updatePassword(Map<String, String> map) {
		String rst = null;
		String account = map.get("user_account");
		String oldPassword = map.get("user_oldPassword");
		String newPassword = map.get("user_newPassword");
		String confirmPassword = map.get("user_confirmPassword");
		if(!confirmPassword.equals(newPassword)) {
			rst = "两次输入密码不一致";
			return rst;
		}
		User user = userDao.getUserByAccount(account);
		if(user.getPassword().equals(oldPassword)) {
			userDao.updatePassword(newPassword, account);
		}else {
			rst = "原密码不正确";
		}
		return rst;
	}
	
	public String updatePassword(String account, String newPassword,String confirmPassword) {
		String rst = null;
		if(!confirmPassword.equals(newPassword)) {
			rst = "两次输入密码不一致";
			return rst;
		}
		// 修改用户密码
		userDao.updatePassword(newPassword, account);
		
		return rst;
	}
	
}
