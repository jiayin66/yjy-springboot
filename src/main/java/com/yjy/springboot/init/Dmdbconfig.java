package com.yjy.springboot.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ConfigurationProperties(prefix="dameng")
@Component
public class Dmdbconfig {
	private String driverName;
	private String url;
	private String userName;
	private String password;
	 

}
