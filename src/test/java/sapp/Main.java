package sapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class Main {

	static SimpleClientHttpRequestFactory factory;
	static String requestKey = "97a7577b766d";
	 
	

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
	factory = new SimpleClientHttpRequestFactory();
	factory.setConnectTimeout(20000);
		
		
	String root = "http://localhost:8080/lapp/user/test.do";
	
	Map<String, String> map = new HashMap<>();
	map.put("account", "admin");
	map.put("pwd", "0791jr2018");
	map.put("sysSid", "43019925-d272-40f5-8597-584043b6f47");
	System.out.println(post(root, map));
	}
	
	
	
	
	private static String post(String url,Map<String, String> param) throws UnsupportedEncodingException {
		param.put("timestamp", System.currentTimeMillis()+"");
		param.put("sysAppid", "ios");
		String sign =  DigestUtils.md5Hex(buildSignStr(param,"")+"&reqKey="+requestKey);
		System.out.println(buildSignStr(param,"")+"&reqKey="+requestKey);
		param.put("sysSign", sign);
		
		
		System.out.println(param);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> mmap= map2MultiValueMap(param);
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(mmap, headers);
		
		
		
	
		
		ResponseEntity<String> str = restTemplate.postForEntity(url, request,String.class);
	
		
		
		
		return str.getBody();
		
		

	}
	
	private static MultiValueMap<String, String> map2MultiValueMap(Map<String, String> map) {
	
		 MultiValueMap<String, String> multiValueMap  = new LinkedMultiValueMap<String, String>();
		
		for (Entry<String, String> entry : map.entrySet()) {  
			  
			
			multiValueMap.add(entry.getKey(), entry.getValue());
		  
		}  

		return multiValueMap;
	}
	
	
	   public static String encrypByMd5(String context,String charset) throws UnsupportedEncodingException {  
	        try {  
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	            if(charset==null)charset = "utf-8";
	            md.update(context.getBytes(charset));//update处理  
	            byte [] encryContext = md.digest();//调用该方法完成计算  
	  
	            int i;  
	            StringBuffer buf = new StringBuffer("");  
	            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）  
	                i = encryContext[offset];  
	                if (i < 0) i += 256;  
	                if (i < 16) buf.append("0");  
	                buf.append(Integer.toHexString(i));  
	           }  
	            
	            return buf.toString();
	          // System.out.println("32result: " + );// 32位的加密  
	          // System.out.println("16result: " + buf.toString().substring(8, 24));// 16位的加密  
	        } catch (NoSuchAlgorithmException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }
			return null;  
	    }  
	   
		 public static String buildSignStr(Map<String, String> params,String eparam) {
		        StringBuilder sb = new StringBuilder();
		        // 将参数以参数名的字典升序排序
		        Map<String, Object> sortParams = new TreeMap<String, Object>(params);
		        // 遍历排序的字典,并拼接"key=value"格式
		        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
		        	if(entry.getKey().equals(eparam))continue;
		        	
		            if (sb.length()!=0) {
		                sb.append("&");
		            }
		            sb.append(entry.getKey()).append("=").append(entry.getValue());
		        }
		        return sb.toString();
		    }
	
}
