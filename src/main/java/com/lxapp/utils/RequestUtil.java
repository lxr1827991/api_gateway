package com.lxapp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {
	
	
	public static HttpServletRequest getRequest() {
		
		  return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	}
	
	
	public static HttpServletResponse getResponse() {
		 return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	
	public static void setCookie(String key,String val) {
		getResponse().addCookie(new Cookie(key, val));
	}
	
	
	
	public static HttpSession getSession() {
		return  getRequest().getSession();
	}
	
	
	public static boolean isPost(HttpServletRequest request) {
		return true;

	}
	
	
	
}
