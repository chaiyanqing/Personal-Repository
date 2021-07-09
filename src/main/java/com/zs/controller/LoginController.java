/**
 * 
 */
package com.zs.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zs.entity.ResponseResult;
import com.zs.service.IUserService;

/**
 * @ProjectName om
 * @File com.zs.controller.LoginController.java
 * @Author Yanqing
 * @Date 2018年4月16日 下午4:31:36
 * @Version V1.0
 */

@Controller
@RequestMapping("/")  
public class LoginController {
	
	@Resource
	private IUserService userService;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult login(HttpServletRequest request){
		String userName = request.getParameter("i_username");
		String password = request.getParameter("i_password");
		ResponseResult result = userService.login(userName, password);
		if(result.getSuccess()) {
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("user", result.getData());
		}
//		String rst = JSONObject.toJSONString(result);
		return result;
	}
	
	
	@RequestMapping(value="loginOnline", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult loginOnline(HttpServletRequest request){
		String userName = request.getParameter("i_username");
		String password = request.getParameter("i_password");
		String captcha = request.getParameter("i_captcha");

		Object fUserName = request.getSession().getAttribute("fUserName");
		Object fCaptcha = request.getSession().getAttribute("fCaptcha");
		Long fTime = (Long) request.getSession().getAttribute("fTime");
		
		if(fUserName == null || !userName.equals(fUserName.toString()) || fCaptcha==null || fTime==null || !captcha.equals(fCaptcha)) {
			return new ResponseResult(false, "验证码错误。", null);
		}else if((System.currentTimeMillis() - fTime) > 120000) {
			return new ResponseResult(false, "验证码超时。", null);
		} 
		
		// 登录密码验证
		ResponseResult result = userService.login(userName, password);
		if(result.getSuccess()) {
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("user", result.getData());
		}
//		String rst = JSONObject.toJSONString(result);
		return result;
	}
	
	@RequestMapping(value="forgot", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult forgot(HttpServletRequest request){
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		String repasswd = request.getParameter("repasswd");
		String captcha = request.getParameter("captcha");
		
		Object fUserName = request.getSession().getAttribute("fUserName");
		Object fCaptcha = request.getSession().getAttribute("fCaptcha");
		Long fTime = (Long) request.getSession().getAttribute("fTime");
		
		if(fUserName == null || !account.equals(fUserName.toString()) || fCaptcha==null || fTime==null || !captcha.equals(fCaptcha)) {
			return new ResponseResult(false, "验证码错误。", null);
		}else if((System.currentTimeMillis() - fTime) > 120000) {
			return new ResponseResult(false, "验证码超时。", null);
		} else{
			String re = userService.updatePassword(account, passwd, repasswd);
			if(re==null) {
				request.getSession().removeAttribute("fUserName");
				request.getSession().removeAttribute("fCaptcha");
				request.getSession().removeAttribute("fTime");
			}
			return new ResponseResult(re==null, re==null ? "密码修改成功！" : re, null);
		}
	}
	
	
}

