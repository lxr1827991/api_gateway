package com.park.api.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.AppSessionDao;
import com.park.api.dao.SessionDao;

@Component
public class DbAppSessionDao implements AppSessionDao{

	
	@Autowired
	SessionDao sessionDao;
	
	@Override
	public AppSession getSession(String sessionid) {
		String json = sessionDao.getSession(sessionid);
		if(json==null||"".equals(json))
		return null;
		return JSONObject.parseObject(json,AppSession.class);
	}

	@Override
	public void save(AppSession appSession) {
		sessionDao.save(appSession.getId()
				,appSession.getAlias()
				,appSession.getLife()
				,JSONObject.toJSONString(appSession)
				,System.currentTimeMillis());
	}

	@Override
	public void update(String sessionid, AppSession appSession) {
		
		sessionDao.update(sessionid,appSession.getAlias(), JSONObject.toJSONString(appSession));
	}

	@Override
	public void delete(AppSession appSession) {
		sessionDao.delete(appSession.getId());
		
	}
	
	@Override
	public void setLife(AppSession appSession, int life) {
		sessionDao.setLife(appSession.getId(), life);
	} 
	
	
	@Override
	public void refLife(AppSession session) {
		sessionDao.refLife(session.getId(), System.currentTimeMillis());
	}

	@Override
	public AppSession getByAlias(String alias) {
		String json = sessionDao.getByAlias(alias);
		if(json==null||"".equals(json))
		return null;
		return JSONObject.parseObject(json,AppSession.class);
		
	}

	@Override
	public void gc() {
		
		sessionDao.deleteOverdue(System.currentTimeMillis());
		
	}
	


	

}
