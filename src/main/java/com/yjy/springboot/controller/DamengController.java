package com.yjy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.springboot.model.CLinfoModel;
import com.yjy.springboot.model.DMDriverInfo;
import com.yjy.springboot.service.DamengService;

@RestController
@RequestMapping("/dameng")
public class DamengController {
	@Autowired
	private DamengService damengService;
 
	@GetMapping("/info/car")
	public List<CLinfoModel> getCarInfoByNo(@RequestParam("hphm") String hphm,@RequestParam("hpzl") String hpzl) {
		return damengService.getCarInfoByNo(hphm,hpzl);
	}
	
	@GetMapping("/info/driver/{sfzhm}")
	public List<DMDriverInfo> getDriverInfoById(@PathVariable("sfzhm") String sfzhm) {
		return damengService.getDriverInfoById(sfzhm);	
	}
}
