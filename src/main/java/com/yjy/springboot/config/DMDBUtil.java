package com.yjy.springboot.config;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DMDBUtil implements CommandLineRunner{
	/**
	 * 初始化连接池
	 */	
	@Override
	public void run(String... args) throws Exception {
		init()	;
	}

	@Autowired
	private PoolConfig config;// 连接池的配置对象 
	
	private int count;// 记录连接池的连接数

	private boolean isActive;// 连接池是否被激活

	// 空闲连接集合
	private Vector<Connection> freeConn = new Vector<Connection>();

	// 正在使用的连接集合
	private Vector<Connection> useConn = new Vector<Connection>();

	// 同一个线程无论请求多少次都使用同一个连接（使用ThreadLocal确保）
	// 每一个线程都私有一个连接
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	/*
	 * 
	 * 初始化连接池配置
	 * 
	 */
	public DMDBUtil(PoolConfig config) {
		this.config = config;
	}
	public DMDBUtil() {
		super();
	}

	/*
	 * 
	 * 数据库连接池初始化
	 * 
	 */
	public void init() {
		for (int i = 0; i < config.getInitConn(); i++) {// 建立初始连接
			// 获取连接对象
			Connection conn;
			try {
				conn = getNewConnection();
				freeConn.add(conn);
				count++;
			} catch (SQLException e) {
				log.error("初始化线程，创建线程失败");
				e.printStackTrace();
			}
			isActive = true;// 连接池激活
		}
	}
	/*
	 * 
	 * 获取新数据库连接
	 * 
	 */
	private synchronized Connection getNewConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(config.getUrl(), config.getUserName(),
				config.getPassword());
		return conn;
	}



	/*
	 * 
	 * 从连接池获取连接
	 * 
	 */
	public synchronized Connection getConnection(){
	  Connection conn = null;
	  //当前连接总数小于配置的最大连接数才去获取
	  if(count<config.getMaxActiveConn()){
	   //空闲集合中有连接数
		   if(freeConn.size()>0){
			  conn = freeConn.get(freeConn.size()-1);//从空闲集合中取出
			  freeConn.remove(freeConn.size()-1);//移除该连接
		   }else{
			   try {
				conn = getNewConnection();//拿到新连接
				} catch (Exception e) {
					log.error("没有空闲连接，且小于最大连接数，创建新连接失败！");
					e.printStackTrace();
				}
				count++;
			}
		   if(isEnable(conn)){
			   useConn.add(conn);//添加到已经使用的连接
		    }else{
		    	count--;
		    	conn = getConnection();//递归调用到可用的连接
		    }

	  }else{//当达到最大连接数，只能阻塞等待
		  try {
			  wait(config.getWaitTime());
		} catch (InterruptedException e) {
			log.error("连接已达最大，休眠指定时间报错");
			e.printStackTrace();
		} 
		conn = getConnection();//递归调用	 
	   }
	  //将获取的conn设置到本地变量ThreadLocal
	  threadLocal.set(conn);
	  return conn;
	}

	/*
	 * 
	 * 把用完的连接放回连接池集合Vector中
	 * 
	 */
	public synchronized void releaseConnection(Connection conn) {
		if (isEnable(conn)) {
			if (freeConn.size() < config.getMaxConn()) {// 空闲连接数没有达到最大
				freeConn.add(conn);// 放回集合
			} else {
				try {
					conn.close();
				} catch (SQLException e) {
					log.error("空余线程太多，关闭失败");
					e.printStackTrace();
				}
				count--;
			}
		}
		notifyAll();
		useConn.remove(conn);
		threadLocal.remove();
	}

	/*
	 * 获取当前线程的本地变量连接
	 */
	public Connection getCurrentConnection() {
		return threadLocal.get();
	}

	/*
	 * 判断该连接是否可用
	 */
	private boolean isEnable(Connection conn) {
		if (conn == null) {
			return false;
		}
		try {
			if (conn.isClosed()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	public <T> List<T> excuteQurey(String sql,Class<T> Tclass) {
		Connection connection=null;
		List<T> resultToObject=null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultToObject = resultToObject(resultSet, Tclass);
		} catch (Exception e) {
			log.error("执行sql出现异常,sql为：{}",sql);
		}
		 releaseConnection(connection);
		 return resultToObject;
	}
	
	public <T> List<T> excutePrepareQurey(String sql,String[] arr,Class<T> Tclass) {
		Connection connection=null;
		List<T> resultToObject=null;
		try {
			connection = getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			for(int i=1;i<=arr.length;i++) {
				prepareStatement.setString(i, arr[i-1]);
			}
			 System.out.println(prepareStatement.toString());
			ResultSet resultSet = prepareStatement.executeQuery();
			resultToObject = resultToObject(resultSet, Tclass);
		} catch (Exception e) {
			log.error("执行sql出现异常sql:{}参数为：{}，合并为：{}",sql,JSON.toJSONString(arr));
			e.printStackTrace();
		}
		releaseConnection(connection);
		return resultToObject;
	}


	private <T>  List<T>  resultToObject(ResultSet resultSet,Class<T> Tclass) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException {
		List<T> listResult=new ArrayList<T>();
		while(resultSet.next()) {
			T t = Tclass.newInstance();
			Field[] Fields = Tclass.getDeclaredFields();
			for(Field field:Fields) {
				String name = field.getName();	
				field.setAccessible(true);
				field.set(t, resultSet.getString(name));
			}
			listResult.add(t);
		}
		return listResult;
	}
}
