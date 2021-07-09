package com.zs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zs.entity.User;

public class BaseController {
	
	public Logger logger = Logger.getLogger(Logger.class);

	
	public User getCurrentUser(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		if(u == null) {
			logger.info("当前session用户为空。");
		}
		return u;
	}
}
