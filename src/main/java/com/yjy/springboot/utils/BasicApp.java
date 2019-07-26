package com.yjy.springboot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yjy.springboot.init.Dmdbconfig;

@Component
public class BasicApp {
	@Autowired
	private Dmdbconfig dmdbconfig;

	static // 定义sql语句
	// String sqlString ="create table yujin3(a int,b int,c int);";
	String sqlString1 = "insert into yujin3  values(123,14,1234);";
	// 定义连接对象
	static Connection conn = null;
	
	public Connection getConnection() {
		if(conn!=null) {
			return conn;
		}
		try {
			Class.forName(dmdbconfig.getDriverName());
			conn = DriverManager.getConnection(dmdbconfig.getUrl(), dmdbconfig.getUserName(),
					dmdbconfig.getPassword());
		} catch (Exception e) {
			System.out.println("创建jdbc连接失败");
		}
		return conn;
	}
	

	/*
	 * 加载 JDBC 驱动程序
	 * 
	 * @throws SQLException 异常
	 */
	public void loadJdbcDriver() throws SQLException {
		try {
			System.out.println("Loading JDBC Driver...");
			// 加载 JDBC 驱动程序
			// DriverManager.registerDriver(new dm.jdbc.driver.DmDriver());
			Class.forName(dmdbconfig.getDriverName());
		} catch (ClassNotFoundException e) {
			throw new SQLException("Load JDBC Driver Error1: " + e.getMessage());
		} catch (Exception ex) {
			throw new SQLException("Load JDBC Driver Error : " + ex.getMessage());
		}
	}

	public void connect() throws SQLException {
		try {
			System.out.println("Connecting to DM Server...");
			// 连接 DM 数据库
			conn = DriverManager.getConnection(dmdbconfig.getUrl(), dmdbconfig.getUserName(), dmdbconfig.getPassword());
		} catch (SQLException e) {
			throw new SQLException("Connect to DM Server Error : " + e.getMessage());
		}
	}

	/*
	 * 关闭连接
	 * 
	 * @throws SQLException 异常
	 */
	public void disConnect() throws SQLException {
		try {
			// 关闭连接
			conn.close();
			System.out.println("close");
		} catch (SQLException e) {
			throw new SQLException("close connection error : " + e.getMessage());
		}
	}

	public static void main(String args[]) {
		try {
			BasicApp basicApp = new BasicApp();
			// 加载驱动程序
			basicApp.loadJdbcDriver();
			basicApp.connect();

			PreparedStatement pstmt1 = conn.prepareStatement(sqlString1);
			// pstmt1.setInt(1,11);
			// pstmt1.setInt(2, 12);
			// pstmt1.setInt(3, 123);

			pstmt1.execute();
			// 关闭语句
			pstmt1.close();


			System.out.println("OK!");
			basicApp.disConnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

