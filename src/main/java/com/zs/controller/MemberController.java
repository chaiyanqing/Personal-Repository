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
import com.zs.entity.User;
import com.zs.service.AbodyplusService;
import com.zs.service.IMemberService;

@Controller
@RequestMapping("/member")  
public class MemberController extends BaseController{
	@Resource
	private IMemberService memberService;
	@Resource
	private AbodyplusService abodyplusService;
	
	@RequestMapping(value="/groupSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult groupSearch(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", memberService.getMemberGroup(map));
	}
	@RequestMapping(value="/addOrUpdateGroup", method=RequestMethod.POST)
	public @ResponseBody ResponseResult addOrUpdateGroup(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		User user = getCurrentUser();
		map.put("userId", user.getId());
		return new ResponseResult(true, "", memberService.addMemberGroup(map));
	}
	@RequestMapping(value="/deleteGroup", method=RequestMethod.POST)
	public @ResponseBody ResponseResult deleteGroup(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", memberService.deleteMemberGroup(map));
	}
	
	
	@RequestMapping(value="/memberSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult memberSearch(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", memberService.getMember(map));
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public @ResponseBody ResponseResult getById(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", memberService.getById(map));
	}
	@RequestMapping(value="/addOrUpdateMember", method=RequestMethod.POST)
	public @ResponseBody ResponseResult addOrUpdateMember(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", memberService.addMember(map));
	}
	@RequestMapping(value="/deleteMember", method=RequestMethod.POST)
	public @ResponseBody ResponseResult deleteMember(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", memberService.deleteMember(map));
	}
	
	@RequestMapping(value="/groupStatSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult groupStat(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", abodyplusService.groupStatView(map));
	}
	
	@RequestMapping(value="/memberViewSearch", method=RequestMethod.GET)
	public @ResponseBody ResponseResult memberView(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", abodyplusService.memberView(map));
	}
}
