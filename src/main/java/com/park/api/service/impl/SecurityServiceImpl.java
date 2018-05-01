package com.park.api.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxapp.appsession.bean.AppClient;
import com.lxr.commons.exception.ApplicationException;
import com.park.api.dao.UserDao;
import com.park.api.entity.User;
import com.lxapp.SecurityService;

@Service
public class SecurityServiceImpl extends SecurityService{

	@Autowired
	UserDao userDao;

	@Override
	public AppClient checkUser(AppClient client) {
		
		User user = userDao.getByAccount(client.getAccount());
		
		if(user==null)throw new ApplicationException("账号不存在");
		
		if(!user.getPwd().equals(DigestUtils.md5Hex(client.getPwd())))
			throw new ApplicationException("密码错误");
		
		return client;
	}
	
	
	
	
}
