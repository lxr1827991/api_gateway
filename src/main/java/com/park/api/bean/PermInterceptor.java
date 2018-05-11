package com.park.api.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.lxapp.common.JsonResult;
import com.park.api.ServiceManage;
import com.park.api.common.exception.AuthorizedException;
import com.park.api.service.impl.AuthService;
public class PermInterceptor extends HandlerInterceptorAdapter{   
    
	
	@Autowired
	AuthService authService;
	
	Map<String, PermBean> urlPerms = new HashMap<>();
	
  
	public void init() {
		
		urlPerms.put("/jrapi/service1/announcement/delete.do", PermBean.newInstance("admin/information/article/delete?sysModule=notice", "admin/information/article/delete?sysModule=notice"));
		urlPerms.put("/jrapi/api/getUser.do", PermBean.newInstance("admin/work/workReport?sysModule=daily", "admin/work/workReport?sysModule=daily&$sysView=all"));
	}
	
	
	
    
    /**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true   
     */    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {    
       String url = request.getRequestURI();
       PermBean bean = urlPerms.get(url);
       if(bean==null)return true;
       
       try {
    	   if(checkPerm( bean))
        	   return true;
		} catch (AuthorizedException e) {
			
			  response.setContentType("text/html;charset=UTF-8");
	          response.getWriter().print(JSONObject.toJSONString(JsonResult.getResult(JsonResult.STATUS_UN_AUTH,"无权限访问！")));
	        
			return false;
		}
   	
       
       
    return false;
    
    }  
    
    
    
    public boolean checkPerm(PermBean bean) {
    	String uid = ServiceManage.securityService.getAppClient().getId();
    	
    		List list = authService.getPermView(uid, bean.getUrl());	
    		
    		AuthUtils.setPerms(list);
    		
    		return true;
	}
    
    
    
    
    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
            
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
           
    }    
  
}    
