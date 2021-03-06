package com.park.api.controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import com.alibaba.fastjson.JSONObject;
import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.common.JsonResult;
import com.park.api.common.RestTemplateUtils;

@RequestMapping
@Controller
public class GatewayController {


	
	
	@RequestMapping("**")
	@ResponseBody
	public JsonResult gateway(HttpServletRequest request,@RequestBody byte[] body) throws IOException {
		
		String localPath = getServiceUrl(request.getContextPath(),request.getRequestURI());
		if(request.getQueryString()!=null)
			localPath+=request.getQueryString();
		
		try {
			
			AppSession appSession = AppSessionUtils.getSession(false);
			String uid = appSession.getAppClient().getId();
			
			String result = RestTemplateUtils.post(localPath,body,request,uid);
			
			return parseJson(result);
			
		} catch (HttpClientErrorException e) {
			if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
				return JsonResult.getResult(JsonResult.STATUS_UN_SERVICE, "服务未找到");
			
			return JsonResult.getResult(JsonResult.STATUS_UN_SERVICE, "服务错误");
		}
		
		

	}
	
	
	public JsonResult parseJson(String json) {
		JsonResult jsonResult = JsonResult.getSuccessResult();
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		jsonResult.setCode(jsonObject.getIntValue("code"));
		jsonResult.setData(jsonObject.get("data"));
		jsonResult.setMsg(jsonObject.getString("msg"));
		
		return jsonResult;

	}
	
	
	
	private String getServiceUrl(String contextPath,String url) {
		
		String surl = url.substring(contextPath.length(), url.length());
		
		return "http://localhost:8080"+surl;

	}
	
	public static void main(String[] args) {
		   
	}
	
	
	
	
	
	
}