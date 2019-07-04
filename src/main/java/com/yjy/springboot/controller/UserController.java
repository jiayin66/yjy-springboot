package com.yjy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.springboot.dao1.UserDao1;
import com.yjy.springboot.entity.User;
import com.yjy.springboot.dao.UserDao;

@RequestMapping("user")
@RestController
public class UserController {
	@Autowired
	private UserDao UserDao;
	
	@Autowired
	private UserDao1 UserDao1;
	
	@GetMapping("/")
	public void getUser() {
		List<User> find = UserDao.find();
		List<User> find2 = UserDao1.find();
	}


}
