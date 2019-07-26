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

import com.yjy.springboot.model.CLinfoModel;
import com.yjy.springboot.model.DMDriverInfo;
import com.yjy.springboot.service.DamengService;
import com.yjy.springboot.utils.BasicApp;

@Service
public class DamengServiceImp implements DamengService{
	@Autowired
	private BasicApp basicApp ;
	@Override
	public List<CLinfoModel> getCarInfoByNo(String hphm, String hplx) {
		List<CLinfoModel> resultList =new ArrayList<CLinfoModel>();
		
		Connection conn = basicApp.getConnection();
		String sql="select * from zhjczyk.ql_jjzd_jdcdj t where t.hphm=? and t.hplx=?";
		ResultSet resultSet=executeQuery(sql,conn);
		try {
			resultList = ResultToObject(resultSet,CLinfoModel.class);
		} catch (Exception e) {
			
		}		
		return resultList;
	}
	
	private <T>  List<T>  ResultToObject(ResultSet resultSet,Class<T> Tclass) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException {
		List<T> listResult=new ArrayList<T>();
		while(resultSet.next()) {
			T t = Tclass.newInstance();
			Field[] Fields = Tclass.getDeclaredFields();
			for(Field field:Fields) {
				String name = field.getName();	
				Tclass.getDeclaredField(name).set(t, resultSet.getString(name));			
			}
			listResult.add(t);
		}
		return listResult;
	}
	
	public ResultSet executeQuery(String sql,Connection conn){
		Statement statement;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			//statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public List<DMDriverInfo> getDriverInfoById(String sfzhm) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
