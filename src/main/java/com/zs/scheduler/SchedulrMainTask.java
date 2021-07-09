package com.zs.scheduler;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zs.service.AbodyplusService;
import com.zs.util.ToolsUtils;

/**
 * 定时任务启动类
 * @author Yanqing
 *
 */
@Component
public class SchedulrMainTask {
	
	private Logger logger = Logger.getLogger(SchedulrMainTask.class);
	

	@Resource
	private AbodyplusService abodyplusService;
	
	
	@Scheduled(cron = "00 00 10,15 * * ? ")
	public void productOvertime() {
		abodyplusService.createTrainData();
		logger.info(ToolsUtils.formatDate(new Date(), ToolsUtils.formatePattern)+" 查询超时未到货商品数量 "+ "，待后续通知处理。");
	}
	
}