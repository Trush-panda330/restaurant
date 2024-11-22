package com.example;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.domain.customer.mapper")  
public class MyBatisConfig {
	@Bean
	public DataSource datasource() {
		return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/restaurant_db")
				.username("root")
				.password("pass")
				.build();
	}
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sesseionfactoryBean = new SqlSessionFactoryBean();
		
		sesseionfactoryBean.setDataSource(datasource());
		
		sesseionfactoryBean.setConfigLocation(new ClassPathResource("/myBatis-config.xml"));
		return sesseionfactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}
	
}

