package com.park.api.bean;

import java.util.List;


public class AuthUtils {

	private static ThreadLocal<List<String>> perms = new ThreadLocal<>();
	
	
	public static void setPerms(List<String> ps) {
		AuthUtils.perms.set(ps);;
	}
	
	public static List<String> getPerms() {
		return AuthUtils.perms.get();
	}
}
