package com.yjy.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yjy.springboot.entity.User;
@Mapper
public interface UserDao {
	@Select("select * from user t  ")
	public List<User> find();
	
}
