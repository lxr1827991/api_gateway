package com.park.api.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.park.api.entity.User;

public interface UserDao {

	 
	 User getByAccount(@Param("account")String account);
	 
	 
	 
}
