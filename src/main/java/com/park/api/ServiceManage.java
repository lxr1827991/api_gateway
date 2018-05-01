package com.park.api;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.park.api.service.impl.SecurityServiceImpl;

public class ServiceManage {
	
	
	
	public static SecurityServiceImpl securityService;
	
	public static JdbcTemplate jdbcTemplate;
	
	
	public static DataSource dataSource;
	

	
	 @Autowired
	 public void setSecurityService(SecurityServiceImpl s){
		 
		 securityService = s;
	 }
	 
	 @Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		 ServiceManage.jdbcTemplate = jdbcTemplate;
	}
	 
	 @Autowired
	 public void setDataSource(DataSource dataSource) {
		 ServiceManage.dataSource = dataSource;
	}
	 
	 
	
}
