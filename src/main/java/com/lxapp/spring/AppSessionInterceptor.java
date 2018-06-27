package com.lxapp.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lxapp.AppContext;
import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.AppSessionReception;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.utils.AppUtils;
import com.lxapp.utils.CookieUtils;
import com.park.api.GatewayUtils;

public class AppSessionInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	AppSessionReception appSessionReception;
	
	
	String sessionKey = "sysSid";
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		appSessionReception.requestStart();
		String sid = request.getParameter(sessionKey);
		if(sid!=null&&sid.trim().equals(""))sid = null;
		appSessionReception.setSessionId(sid);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
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
