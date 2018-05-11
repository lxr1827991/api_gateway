package com.lxapp.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.AppSessionReception;
import com.lxapp.appsession.utils.AppSessionUtils;

public class AppSessionInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	AppSessionReception appSessionReception;
	
	
	String sessionKey = "sysSid";
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		appSessionReception.requestStart();
		appSessionReception.setSessionId(request.getParameter(sessionKey));
		AppSession appSession = AppSessionUtils.getSession(false);
		if(appSession!=null)AppSessionUtils.refSession(appSession);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		/*if(AppUtils.getAppInterface().getAppid().equals(AppContext.APP_WEB)) {
			AppSession appSession = AppSessionUtils.getSession(false);
			if(appSession!=null)
			CookieUtils.setCookie(response, sessionKey,appSession.getId(),10*60,"/");
			else CookieUtils.delCookie(request, response, sessionKey);
			
		}*/
		
		appSessionReception.requestFinish();
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
}
