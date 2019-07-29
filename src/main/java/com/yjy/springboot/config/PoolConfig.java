package com.yjy.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="dameng")
@Component
@Getter
@Setter
public class PoolConfig {
	private String url;
	
	private String userName;
	
	private String password;
	
	private String driverName;

	private int maxConn = 5;//空闲集合最多的连接数
	
	private int initConn = 5;//初始连接数
	
	private int maxActiveConn = 10;//整个连接池（数据库）允许的最大连接数
	
	private int waitTime = 200;//单位毫秒，连接数不够时，线程等待的时间
	

}
