/**
 * 
 */
package com.zs.controller;

import java.util.List;
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
import com.zs.service.ITrainService;

@Controller
@RequestMapping("/train")  
public class TrainController extends BaseController{
	@Resource
	private ITrainService trainService;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody ResponseResult groupSearch(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", trainService.search(map));
	}
	
	@RequestMapping(value="/trainMember", method=RequestMethod.GET)
	public @ResponseBody ResponseResult trainMember(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", trainService.trainMember(map));
	}
	
	@RequestMapping(value="/addTrain", method=RequestMethod.POST)
	public @ResponseBody ResponseResult addTrain(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", trainService.addTrain(map));
	}
	
	@RequestMapping(value="/startTrain", method=RequestMethod.POST)
	public @ResponseBody ResponseResult startTrain(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", trainService.startTrain(map));
	}
	
	@RequestMapping(value="/endTrain", method=RequestMethod.POST)
	public @ResponseBody ResponseResult endTrain(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		return new ResponseResult(true, "", trainService.endTrain(map));
	}
	
	@RequestMapping(value="/monitor", method=RequestMethod.GET)
	public ModelAndView monitor(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		String trainId = map.get("trainId");
		String trainName = map.get("trainName");
		model.addAttribute("trainId", trainId);
		model.addAttribute("trainName", trainName);
		List<Map<String, Object>> lst = trainService.monitor(trainId);
		model.addAttribute("data", lst);
		model.addAttribute("dataSize", lst != null ? lst.size() : 0);
		return new ModelAndView("monitorManagement");
	}
	

	@RequestMapping(value="/memHrList", method=RequestMethod.GET)
	public @ResponseBody ResponseResult memHrList(@RequestParam Map<String,String> map,HttpServletRequest request, HttpSession session, Model model){
		String trainId = map.get("trainId");
		String sn = map.get("sn");
		return new ResponseResult(true, "", trainService.memHrList(trainId, sn));
	}

}
