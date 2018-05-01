package com.lxapp.common.bean;


public class Vcode {


	String phone;
	
	String code;
	
	long expiration;

	
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	
	
	public boolean valid() {
		
		return expiration>System.currentTimeMillis();

	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vcode))return false;
		
		Vcode tvcode = (Vcode)obj;
		
		if(phone!=null&&phone.equals(tvcode.getPhone())&&code!=null&&code.equals(tvcode.getCode()))
			return true;
		
		return super.equals(obj);
	}
	
}
