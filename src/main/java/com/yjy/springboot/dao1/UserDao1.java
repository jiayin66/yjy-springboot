package com.yjy.springboot.dao1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yjy.springboot.dao.UserDao;
import com.yjy.springboot.entity.User;
@Mapper
public interface UserDao1 {
	@Select("select * from user t  ")
	public List<User> find();

}
