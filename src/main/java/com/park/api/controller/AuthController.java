package com.park.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lxapp.appsession.bean.AppClient;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.common.JsonResult;
import com.park.api.ServiceManage;
import com.park.api.common.BaseController;
import com.park.api.service.impl.AuthService;

@RequestMapping("authService")
@Controller
public class AuthController extends BaseController{
	
	
	@Autowired
	AuthService permService;
	
	@RequestMapping("isPermitteds")
	@ResponseBody
	public JsonResult isPermitted(HttpServletRequest request,String perms) {
		
		String[] permArr =  perms.split(",");
		
		AppClient appClient = AppSessionUtils.getSession(false).getAppClient();
		String uid = appClient.getId();
		return JsonResult.getSuccessResult(permService.isPermitted(uid, permArr));
		

	}
	
	
	@RequestMapping("login")
	@ResponseBody
	public Object login(String account,String pwd) {
		
		AppClient client = new AppClient();
		
		client.setAccount(account);
		client.setPwd(pwd);
		
		
		client = ServiceManage.securityService.checkUser(client);
		
		ServiceManage.securityService.login(client);
		
		return JsonResult.getSuccessResult();

	}
	
	
	@RequestMapping("logout")
	@ResponseBody
	public Object logout() {
		
		ServiceManage.securityService.logout(AppSessionUtils.getSession().getAppClient());
		
		return JsonResult.getSuccessResult();

	}
	

}
