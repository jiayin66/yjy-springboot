package com.yjy.springboot.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yjy.springboot.service.TestDemoService;
@Service
public class TestDemoServiceImpl implements TestDemoService{

	@Override
	@Async
	public void findA() {
		System.out.println("进入异步函数");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("异步函数完事");
	}


}
