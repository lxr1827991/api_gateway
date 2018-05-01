package com.lxapp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	   public static void setCookie(HttpServletResponse response,String name,String value,int expiry,String path){  
           Cookie cookie = new Cookie(name.trim(), value.trim());  
           cookie.setMaxAge(expiry);// 设置为30min
           cookie.setPath(path);
           response.addCookie(cookie);  
       } 
	   
	   public static void setCookie(HttpServletResponse response,String name,String value){  
           setCookie(response, name, value, 30*60, "/");
       }
	   
	   
        public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){  
            Cookie[] cookies = request.getCookies();  
            if(null==cookies) return;
            
                for(Cookie cookie : cookies){  
                    if(!cookie.getName().equals(name))continue;
                        cookie.setValue(null);  
                        cookie.setMaxAge(0);// 立即销毁cookie  
                        cookie.setPath("/"); 
                        response.addCookie(cookie);
                      
                
            }  
        }  
  
	
}
