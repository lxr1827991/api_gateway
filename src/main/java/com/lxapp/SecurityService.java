package com.lxapp;

import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.bean.AppClient;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.utils.AppUtils;

public abstract class SecurityService {
	
	
	public abstract AppClient checkUser(AppClient subject);
	
	public void login(AppClient client) {
		
		AppSession old = AppSessionUtils.getSessionByAlias(client.getAccount());
		if(old!=null) {
			AppSessionUtils.invalidatSession(old);
			System.out.println("登出"+client.getAccount());
		}
		AppSession session = AppSessionUtils.getSession();
		session.setAppClient(client);
		session.setAlias(client.getAccount());
		if(!AppContext.APP_WEB.equals(AppUtils.getAppInterface().getAppid()))
			session.setLife(-1);
		
	}
	
	
	public void logout(AppClient client){
		AppSessionUtils.getSession().setAppClient(null);
	}
	
	public boolean isLogin() {
		return AppSessionUtils.getSession().getAppClient()!=null;
	}
	
	public AppClient getAppClient() {
		return AppSessionUtils.getSession().getAppClient();

	}

}
