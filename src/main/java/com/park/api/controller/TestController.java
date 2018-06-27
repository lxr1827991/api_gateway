package com.park.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.utils.AppSessionUtils;

@RequestMapping
@Controller
public class TestController {

	@RequestMapping("test")
	@ResponseBody
	public Object test() {
		redis.clients.jedis.JedisPubSub l;
		
		AppSession session = AppSessionUtils.getSession();
		
		session.putAttr("a", session.getAttr("a")+"r");
		
		return session.getAttr("a");

	}
	
}
