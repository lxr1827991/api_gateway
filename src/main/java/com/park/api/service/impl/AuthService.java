package com.park.api.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.api.common.exception.AuthorizedException;
import com.park.api.dao.AuthDao;

@Service
public class AuthService {
	
	@Autowired
	AuthDao authDao;
	

	public List<String> isPermitted(String uid,String[] perms) {
		if(perms==null||perms.length<1)return null;
		return authDao.findPrems(perms, uid);
	}
	
	
	public boolean isPermitted(String uid,String perms) {
		List list = isPermitted(uid, new String[] {perms});
		if(list==null||list.size()<1)return false;
		return true;
		
	}
	
	
	private boolean checkPrem(String[] perms,String prem) {
		
		for (int i = 0; i < perms.length; i++) {
			if(perms[i].equals(prem))return true;
		}
		
		return false;

	}
	
	
	public List<String> getPermView(String uid,String url) {
		
		List<String> perms = authDao.findViewPrems(uid, url);
		if(perms==null||perms.size()<1)throw new AuthorizedException();
		
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < perms.size(); i++) {
			int index = perms.get(i).indexOf("$sysView=");
			if(index<0)continue;
			list.add(perms.get(i).substring(index+9));
		}
		
		return list;
		
	}
	
	
	public static void main(String[] args) {
		System.out.println("1234555".indexOf("2"));
	}
	
}
