package com.lxapp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.inject.New;

import org.dom4j.DocumentException;

import com.lxr.commons.utils.XmlUtils;

public class AppContext {
	
	public static boolean isDebug = false;
	
	public static String debugKey = "45c7b7f0-f662-499e-9545-7151eda7d2f3";

	public static Map<String, String> appConfig = null;
	
	
	public static String APP_ANDROID = "android";
	
	public static String APP_IOS = "ios";
	
	public static String APP_WEB = "web";
	
	public static Map<String, String> appKeys = new HashMap<>();
	
	
	static{
		
		
			try {
				
				appConfig = XmlUtils.xml2map(AppContext.class.getResourceAsStream("/appConfig.xml"));
				appKeys.put(APP_ANDROID, appConfig.get("app_key_"+APP_ANDROID));
				appKeys.put(APP_IOS, appConfig.get("app_key_"+APP_IOS));
				appKeys.put(APP_WEB, appConfig.get("app_key_"+APP_WEB));
			} catch (DocumentException e) {
				throw new RuntimeException(e);
			}
		
		
	}
	
	public static String getFileHost() {
		
		return appConfig.get("fileHost");

	}
	
	public static String getProperties(String key) {
		return appConfig.get(key);

	}
	
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
	
	
}
