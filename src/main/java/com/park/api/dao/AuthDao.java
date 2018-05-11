package com.park.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.park.api.entity.User;

public interface AuthDao {

	
	List<String> findPrems(@Param("prems") String[] prems,@Param("uid")String uid);
	
	User getByAccount(@Param("account")String account);
	
	List<String> findViewPrems(@Param("uid")String uid,@Param("url") String url);
	
}
