package com.zs.interceptor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	private List<String> allowUrls = new ArrayList<String>(); 
	
	
	 
	public List<String> getAllowUrls() {  
        return allowUrls;  
    }  
	
    public void setAllowUrls(List<String> allowUrls) {  
        this.allowUrls = allowUrls;  
    }  
    
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("after");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("post");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		 //获取请求的URL  
        String reqUrl = request.getRequestURI();  
        request.setAttribute("reqUrl", reqUrl);
        System.out.println("reqUrl is " + reqUrl);
        
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
        for(String url : allowUrls) {  
            if(reqUrl.indexOf(url) >= 0) {  
                return true;  
            }  
        }  
        //获取Session  
        HttpSession session = request.getSession();  
        String username = (String)session.getAttribute("userName");  
        System.out.println("登录用户： " + username);
        if(username != null){  
            return true;  
        } else {
        	//ajax请求响应头包含x-requested-with
        	if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
        		response.setHeader("sessionstatus", "timeout"); //在响应头设置session状态  
        	}
        	//其他页面跳转请求直接到登录页面
        	else{
        		System.out.println("reqServerName is " + request.getServerName());
        		System.out.println("LOCAL_URL is " + InetAddress.getLocalHost().getHostAddress());
        		request.getRequestDispatcher("/login.jsp").forward(request, response);  
        	} 
        }
        return false;  
	}

}
