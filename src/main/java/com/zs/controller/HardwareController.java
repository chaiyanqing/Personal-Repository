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

import com.zs.entity.ResponseResult;
import com.zs.service.IHardwareService;

@Controller
@RequestMapping("/hardware")  
public class HardwareController extends BaseController{
	@Resource
	private IHardwareService hardwareService;
	
	@RequestMapping(value="/groupSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult groupSearch(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.getHardwareGroup(map));
	}
	@RequestMapping(value="/addOrUpdateGroup", method=RequestMethod.POST)
	public @ResponseBody ResponseResult addOrUpdateGroup(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.addHardwareGroup(map));
	}
	@RequestMapping(value="/deleteGroup", method=RequestMethod.POST)
	public @ResponseBody ResponseResult deleteGroup(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.deleteHardwareGroup(map));
	}
	
	
	@RequestMapping(value="/hardwareSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult hardwareSearch(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.getHardware(map));
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public @ResponseBody ResponseResult getById(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.getById(map));
	}
	@RequestMapping(value="/addOrUpdateHardware", method=RequestMethod.POST)
	public @ResponseBody ResponseResult addOrUpdateHardware(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.addHardware(map));
	}
	@RequestMapping(value="/deleteHardware", method=RequestMethod.POST)
	public @ResponseBody ResponseResult deleteHardware(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", hardwareService.deleteHardware(map));
	}
	
}
