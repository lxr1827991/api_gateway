package com.park.api.bean;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.common.JsonResult;

@ControllerAdvice(basePackages = "com.park.api.controller")
public class JsonResoleResponseBodyAdvice implements ResponseBodyAdvice{

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		
			return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		
		if((body instanceof JsonResult)) {
			JsonResult result =((JsonResult)body);
			AppSession appSession = AppSessionUtils.getSession(false);
			if(appSession!=null)
			result.setSid(appSession.getId());
			result.setTime(System.currentTimeMillis());
			return result;
			
		}
		return body;
	}
	
	
	
}
