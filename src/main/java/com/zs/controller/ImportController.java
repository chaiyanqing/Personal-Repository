package com.zs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zs.entity.ResponseResult;
import com.zs.service.IImportService;
import com.zs.util.ExcelRead;
import com.zs.util.ExcelUtil;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController{
	
	@Resource
	private IImportService importService;
	
	//群组成员导入
	@RequestMapping(value="/excelImportMember", method=RequestMethod.POST)
	public @ResponseBody() ResponseResult excelImportMember(@RequestParam Map<String,String> map,HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="file") MultipartFile file) throws IOException{
		//判断文件是否为空
		try {
			String groupName = map.get("groupName");
			response.setContentType("text/html");
			if(file == null){
				return new ResponseResult(true, "msg", "failed");
			}
	        String name = file.getOriginalFilename();
			long size = file.getSize();
			if(name == null || ExcelUtil.EMPTY.equals(name) && size==0){
				return new ResponseResult(true, "msg", "failed");
			}
			//读取Excel数据到List中
			Date date1 = new Date();
			System.out.println("解析数据开始：" + date1);
			List<ArrayList<String>> list = new ExcelRead().readExcel(file);
			System.out.println(list);
			Date date2 = new Date();
			System.out.println("解析数据结束：" + date2);
			System.out.println("解析数据用时：" + (date2.getTime() - date1.getTime()));
			if(list.size()>0){
				Date date3 = new Date();
				System.out.println("数据导入开始：" + date3);
				// 获取第一行，总记录数值
				ArrayList<String> row1 = list.get(0);
				int totalCounts = Integer.parseInt(row1.get(1));
				// 获取其他行，待导入记录行
				List<ArrayList<String>> recordList = list.subList(2, list.size()); 
				System.out.println(list);
				System.out.println(recordList);
				if(totalCounts == recordList.size()) {
					// 文件记录验证成功
					if(recordList.get(0).size() != 11) {
						return new ResponseResult(true, "数据解析处理完成。", "详细信息：模板数据列数不对。");
					}else {
						List<String> rst = doImportSimple(recordList, groupName, "M");
						Date date4 = new Date();
						System.out.println("数据导入结束：" + date4);
						System.out.println("数据导入用时：" + (date4.getTime() - date3.getTime()));
						return new ResponseResult(true, "数据解析导入完成。", "详细信息："+rst);
					}
				}else {
					return new ResponseResult(true, "数据解析处理完成。", "导入记录数验证失败【" + totalCounts + "，" + recordList.size() + "】。");
				}
			}else{
				return new ResponseResult(true, "上传失败", "表格内容为空");
			}
		} catch (Exception e) {
			return new ResponseResult(true, "上传失败", e.getMessage());
		}
	} 
	
	//硬件SN导入
	@RequestMapping(value="/excelImportHardware", method=RequestMethod.POST)
	public @ResponseBody() ResponseResult excelImportHardware(@RequestParam Map<String,String> map,HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="file") MultipartFile file) throws IOException{
		//判断文件是否为空
		try {
			String groupName = map.get("groupName");
			response.setContentType("text/html");
			if(file == null){
				return new ResponseResult(true, "msg", "failed");
			}
	        String name = file.getOriginalFilename();
			long size = file.getSize();
			if(name == null || ExcelUtil.EMPTY.equals(name) && size==0){
				return new ResponseResult(true, "msg", "failed");
			}
			//读取Excel数据到List中
			Date date1 = new Date();
			System.out.println("解析数据开始：" + date1);
			List<ArrayList<String>> list = new ExcelRead().readExcel(file);
			Date date2 = new Date();
			System.out.println("解析数据结束：" + date2);
			System.out.println("解析数据用时：" + (date2.getTime() - date1.getTime()));
			
			if(list.size()>0){
				Date date3 = new Date();
				System.out.println("数据导入开始：" + date3);
				// 获取第一行，总记录数值
				ArrayList<String> row1 = list.get(0);
				int totalCounts = Integer.parseInt(row1.get(1));
				// 获取其他行，待导入记录行
				List<ArrayList<String>> recordList = list.subList(2, list.size()); 
				System.out.println(list);
				System.out.println(recordList);
				if(totalCounts == recordList.size()) {
					// 文件记录验证成功
					if(recordList.get(0).size() != 2) {
						return new ResponseResult(true, "数据解析处理完成。", "详细信息：模板数据列数不对。");
					}else {
						List<String> rst = doImportSimple(recordList, groupName, "E");
						Date date4 = new Date();
						System.out.println("数据导入结束：" + date4);
						System.out.println("数据导入用时：" + (date4.getTime() - date3.getTime()));
						return new ResponseResult(true, "数据解析导入完成。", "详细信息："+rst);
					}
				}else {
					return new ResponseResult(true, "数据解析处理完成。", "导入记录数验证失败【" + totalCounts + "，" + recordList.size() + "】。");
				}
			}else{
				return new ResponseResult(true, "上传失败", "表格内容为空");
			}
		} catch (Exception e) {
			return new ResponseResult(true, "上传失败", e.getMessage());
		}
	} 
	
	private List<String> doImportSimple(List<ArrayList<String>> list, String groupName, String model){
		List<String> result = new ArrayList<String>();
		int totalNum = list.size();
		int batchNum = 50; // 每页数据量
		int pageNum = totalNum % batchNum == 0 ? totalNum / batchNum : totalNum / batchNum + 1;
		// 线程数，每页一个线程
		ExecutorService executor = Executors.newFixedThreadPool(pageNum);
		CountDownLatch countDownLatch = new CountDownLatch(pageNum);
		try {
			for (int i = 0; i < pageNum; i++) {
				int fromIndex = batchNum * i;
				int toIndex = Math.min(totalNum, fromIndex + batchNum);
				List<ArrayList<String>> currThreadData = list.subList(fromIndex, toIndex);
				System.out.println("线程："+(i+1)+"数据开始索引："+fromIndex+"，结束索引："+toIndex+"，数据集：" + currThreadData);
//				DataimportSimpleTask task = new DataimportSimpleTask(result, countDownLatch, currThreadData, userId, fromIndex);
//				executor.execute(task);
				executor.execute(() -> {
					try {
						if("M".equals(model)) {
							System.out.println(Thread.currentThread().getName() + "，当前线程数据量：" + currThreadData.size());
							List<String> ls = importService.importMemberMuiltiSimple(currThreadData, groupName, fromIndex);
							System.out.println(Thread.currentThread().getName() + "，当前线程执行结果：" + ls);
							result.addAll(ls);
						}else {
							System.out.println(Thread.currentThread().getName() + "，当前线程数据量：" + currThreadData.size());
							List<String> ls = importService.importHardwareMuiltiSimple(currThreadData, groupName, fromIndex);
							System.out.println(Thread.currentThread().getName() + "，当前线程执行结果：" + ls);
							result.addAll(ls);
						}
					} catch (Exception e) {
						e.printStackTrace();
						result.add(Thread.currentThread().getName() + "，当前线程执行失败，" + e.getMessage());
					}
					// 发出线程任务完成的信号
					countDownLatch.countDown();
				});
			}
			countDownLatch.await();
			System.out.println("数据操作完成，可以在此开始其它业务");					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
		return result;
	}
	
	class DataimportSimpleTask implements Runnable{
		List<String> result;
		CountDownLatch countDownLatch;
		List<ArrayList<String>> currThreadData;
		String userId;
		Integer fromIndex;
		public DataimportSimpleTask(List<String> result, CountDownLatch countDownLatch, List<ArrayList<String>> currThreadData, String userId, Integer fromIndex) {
			this.result = result;
			this.countDownLatch = countDownLatch;
			this.currThreadData = currThreadData;
			this.userId = userId;
			this.fromIndex = fromIndex;
		}
		@Override public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + "，当前线程数据量：" + currThreadData.size());
				List<String> ls = importService.importMemberMuiltiSimple(currThreadData, userId, fromIndex);
				System.out.println(Thread.currentThread().getName() + "，当前线程执行结果：" + ls);
				result.addAll(ls);
			} catch (Exception e) {
				e.printStackTrace();
				result.add(Thread.currentThread().getName() + "，当前线程执行失败，" + e.getMessage());
			}
			// 发出线程任务完成的信号
			countDownLatch.countDown();
		}
	}
	
}
