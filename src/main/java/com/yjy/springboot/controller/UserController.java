package com.yjy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yjy.springboot.entity.User;


@RequestMapping("user")
@RestController
public class UserController {
	
	
	@GetMapping("/")
	public String getUser() {
		System.out.println("1");
		int a=3;
		System.out.println(a);
		return "1235";
	}
	


}
