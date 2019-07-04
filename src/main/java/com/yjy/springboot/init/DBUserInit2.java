package com.yjy.springboot.init;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Configuration
@Component
@MapperScan(basePackages="com.yjy.springboot.dao1.UserDao1",sqlSessionFactoryRef="sqlfactory1")
public class DBUserInit2 {
	@Bean(name="source1")
	@ConfigurationProperties(prefix="spring2.datasource")
	public DataSource getSource1() {
		return DataSourceBuilder.create().build();
	}	
	@Bean(name="sqlfactory1")
	public SqlSessionFactory getSqlfactory1(@Qualifier("source1") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean s=new SqlSessionFactoryBean();
		s.setDataSource(dataSource);
		return s.getObject();
	}
	
}
