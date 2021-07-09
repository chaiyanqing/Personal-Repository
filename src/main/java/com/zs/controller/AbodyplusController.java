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
import com.zs.service.AbodyplusService;

/**
 *  体能监测平台
 */

@Controller
@RequestMapping("/abody")  
public class AbodyplusController  extends BaseController{
	
	@Resource
	private AbodyplusService abodyplusService;
	
	// 我的工作台
	@RequestMapping(value="/desktop", method=RequestMethod.GET)
	public ModelAndView deskTop(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		model.addAttribute("groups", abodyplusService.getGroups());
		return new ModelAndView("desktop");
	}
	// 本周训练负荷
	@RequestMapping(value="/trainLoadSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult trainLoadSearch(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", abodyplusService.trainLoadSearch(map));
	}
	
	// 成员管理
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView user(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		model.addAttribute("groups", abodyplusService.getGroups());
		return new ModelAndView("userManagement");
	}

	// 训练管理
	@RequestMapping(value="/train", method=RequestMethod.GET)
	public ModelAndView train(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		model.addAttribute("groups", abodyplusService.getGroups());
		return new ModelAndView("trainManagement");
	}
	
	// 硬件管理
	@RequestMapping(value="/equipment", method=RequestMethod.GET)
	public ModelAndView equipment(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ModelAndView("equipmentManagement");
	}

	// 数据统计
	@RequestMapping(value="/dataview", method=RequestMethod.GET)
	public ModelAndView stat(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		model.addAttribute("labels", abodyplusService.getLabels());
		return new ModelAndView("dataviewPlatform");
	}
	
	
}
