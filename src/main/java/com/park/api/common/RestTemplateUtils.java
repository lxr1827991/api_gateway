package com.park.api.common;


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
	
	public static String post(String url,byte[] body,HttpServletRequest request){
	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", request.getContentType());
		headers.add("Content-Length", request.getContentLength()+"");
		headers.add("Accept", request.getHeader("Accept"));
		headers.add("Accept-Encoding", request.getHeader("Accept-Encoding"));
		headers.add("Accept-Language", request.getHeader("Accept-Language"));
		headers.add("Connection", request.getHeader("Connection"));
		headers.add("Host", request.getHeader("Host"));
		headers.add("Origin", request.getHeader("Origin"));
		headers.add("Referer", request.getHeader("Referer"));
		headers.add("User-Agent", request.getHeader("User-Agent"));
		headers.add("X-Requested-With", request.getHeader("X-Requested-With"));
		//headers.add("Accept-Encoding", request.);
		System.out.println(headers);

		HttpEntity<byte[]> httpEntity = new HttpEntity(body, headers);
		

		
		ResponseEntity<String> str = restTemplate.postForEntity(url, body,String.class);
	
		return str.getBody();
		
		

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
