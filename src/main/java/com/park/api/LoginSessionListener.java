package com.park.api;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.park.api.service.impl.SecurityServiceImpl;


/**
 * session监听
 * @author Administrator
 *
 */
public class LoginSessionListener implements HttpSessionListener {  
	
    public void sessionCreated(HttpSessionEvent event) {  
    	
    	System.out.println("session生成："+event.getSession().getId());
    }  
    
    
   public void sessionDestroyed(HttpSessionEvent event) {   
        HttpSession session = event.getSession();  
     
        
      //  Subject s = ServiceManage.securityService.outoLogout(session);;
       
        System.out.println(session.getId()+":销毁");
        
      
    }   

   
}    