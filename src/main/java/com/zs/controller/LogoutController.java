/**
 * 
 */
package com.zs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName om
 * @File com.zs.controller.LogoutController.java
 * @Author Yanqing
 * @Date 2018年4月16日 下午4:31:36
 * @Version V1.0
 */

@Controller
@RequestMapping("/logout")  
public class LogoutController {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request, HttpSession session){
		session.invalidate();
		return new ModelAndView("login");
	}
}
