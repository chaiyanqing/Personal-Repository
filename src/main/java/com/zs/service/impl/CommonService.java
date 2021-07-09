package com.zs.service.impl;

import javax.annotation.Resource;

import com.zs.service.AbodyplusService;
import com.zs.service.ICommonService;

public class CommonService implements ICommonService{

	@Resource
	private AbodyplusService abodyplusService;
	
	public void doSomething(String message) {
		System.out.println(message);
		System.out.println(abodyplusService);
	}
}
