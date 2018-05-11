package com.park.api.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.lxapp.common.AppInterface;
import com.lxapp.utils.AppUtils;
import com.park.api.bean.AuthUtils;

public class RestTemplateUtils {

	static SimpleClientHttpRequestFactory factory;
	static {
		factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(20000);
		
	}
	
	
	
	public static String post(String url,Map<String, String[]> param){
	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> mmap= map2MultiValueMap(param);
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(mmap, headers);
		
		ResponseEntity<String> str = restTemplate.postForEntity(url, request,String.class);
	
		return str.getBody();
		
		

	}
	
	public static String post(String url,byte[] body,HttpServletRequest request,String sysUid){
	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", request.getContentType());
		headers.add("Content-Length", request.getContentLength()+"");
		
		
		headers.add("X-Requested-With", request.getHeader("X-Requested-With"));
		
		AppInterface appInterface = AppUtils.getAppInterface();
		
		headers.add("sysUid" , sysUid);
		headers.set("sysAppid", appInterface.getAppid());
		headers.add("sysVersion",appInterface.getVersion());
        if(AuthUtils.getPerms()!=null&&AuthUtils.getPerms().size()>0)
        	headers.add("sysViews",joinList(AuthUtils.getPerms()));
        //headers.add(HttpHeaders.COOKIE,JSONObject.toJSONString(interfaceMap));
        

		HttpEntity<byte[]> httpEntity = new HttpEntity(body, headers);
		

		
		ResponseEntity<String> str = restTemplate.postForEntity(url, httpEntity,String.class);
	
		return str.getBody();
		
		

	}
	
	
	private static String joinList(List<String> list) {
		
		String ret = "";
		for (int i = 0; i < list.size(); i++) {
			ret+=(list.get(i)+",");
		}

		return ret.substring(0,ret.length()-1);
		
		
	}
	
	
public static String get(String url,byte[] body){
	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<byte[]> httpEntity = new HttpEntity(body, headers);
		

		
		ResponseEntity<String> str = restTemplate.postForEntity(url, body,String.class);
	
		return str.getBody();
		
		

	}
	
	private static MultiValueMap<String, String> map2MultiValueMap(Map<String, String[]> map) {
		
		 MultiValueMap<String, String> multiValueMap  = new LinkedMultiValueMap<String, String>();
		
		for (Entry<String, String[]> entry : map.entrySet()) {  
			 if(entry.getValue()!=null&&entry.getValue().length>0)
			for (int i = 0; i < entry.getValue().length; i++) {
				multiValueMap.add(entry.getKey(), entry.getValue()[i]);
			}
			
		  
		}  

		return multiValueMap;
	}
	
}
