package com.park.api.dao;

import org.apache.ibatis.annotations.Param;

public interface SessionDao {

	
	String getSession(@Param("id")String id);
	
	void save(@Param("id")String id,@Param("alias")String alias,@Param("life")Integer life,@Param("json")String json,@Param("time")Long time);
	
	void update(@Param("id")String id,@Param("alias")String alias,@Param("json")String json);
	
	void setLife(@Param("id")String id,@Param("life")Integer life);
	
	void delete(@Param("id")String id);
	
	String getByAlias(@Param("alias")String alias);
	
	void refLife(@Param("id")String id,@Param("time")Long time);
	
	void deleteOverdue(@Param("time")Long time);
	
}
