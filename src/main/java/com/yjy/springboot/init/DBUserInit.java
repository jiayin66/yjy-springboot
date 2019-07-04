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
@MapperScan(basePackages="com.yjy.springboot.dao.UserDao",sqlSessionFactoryRef="sqlfactory")
public class DBUserInit {
	@Bean(name="source")
	@Primary
	@ConfigurationProperties(prefix="spring1.datasource")
	public DataSource getSource1() {
		return DataSourceBuilder.create().build();
	}	
	@Bean(name="sqlfactory")
	@Primary
	public SqlSessionFactory getSqlfactory1(@Qualifier("source") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean s=new SqlSessionFactoryBean();
		s.setDataSource(dataSource);
		return s.getObject();
	}
	
}
