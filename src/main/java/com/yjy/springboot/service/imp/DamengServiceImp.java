package com.yjy.springboot.service.imp;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.springboot.config.DMDBUtil;
import com.yjy.springboot.model.CLinfoModel;
import com.yjy.springboot.model.DMDriverInfo;
import com.yjy.springboot.service.DamengService;


@Service
public class DamengServiceImp implements DamengService{
	
	@Autowired
	private DMDBUtil dMDBUtil;
	@Override
	public List<CLinfoModel> getCarInfoByNo(String hphm, String hplx) {
		String sql="select * from zhjczyk.ql_jjzd_jsz  t where t.hphm= ? and t.hplx= ?";
		String[] arr= {hphm,hplx};
		List<CLinfoModel> resultList = dMDBUtil.excutePrepareQurey(sql,arr,CLinfoModel.class);	
		return resultList;
	}
	


	@Override
	public List<DMDriverInfo> getDriverInfoById(String sfzhm) {
		String sql="select * from zhjczyk.ql_jjzd_jsz t where t.SFZMHM = ?";
		String[] arr= {sfzhm};
		List<DMDriverInfo> resultList = dMDBUtil.excutePrepareQurey(sql,arr, DMDriverInfo.class);	
		return resultList;
	}

	
}
