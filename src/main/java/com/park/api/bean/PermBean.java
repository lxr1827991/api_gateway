package com.park.api.bean;

public class PermBean {

	
	String url;
	
	
	String[] perms;
	
	
	public static PermBean  newInstance(String url,String perms) {
	
		
		PermBean bean = new PermBean();
		bean.setUrl(url);
		bean.setPerms(perms.split(","));
		return bean;

	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String[] getPerms() {
		return perms;
	}


	public void setPerms(String[] perms) {
		this.perms = perms;
	}
	
}
