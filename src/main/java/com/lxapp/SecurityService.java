package com.lxapp;

import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.bean.AppClient;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.utils.AppUtils;



public abstract class SecurityService {
	
	
	
	public abstract AppClient checkUser(AppClient subject);
	
	public void login(AppClient client) {
		AppSession session = AppSessionUtils.getSession();
		session.setAppClient(client);
		session.setAlias(client.getAccount());
		if(AppContext.APP_WEB.equals(AppUtils.getAppInterface().getAppid()))
			session.setForever();
		
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
