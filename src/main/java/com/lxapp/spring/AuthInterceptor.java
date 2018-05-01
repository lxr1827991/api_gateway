package com.lxapp.spring;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.lxapp.AppContext;
import com.lxapp.common.AppInterface;
import com.lxapp.common.JsonResult;
import com.lxapp.utils.AppUtils;

/**
 * 安全拦截
 * @author Administrator
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{

	
	String appidKey = "sysAppid";
	
	String versionKey = "sysVersion";
	
	String signKey = "sysSign";
	
	String reqKey = "reqKey";
	
	  @Override
	  public boolean preHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler) throws Exception {
		  response.setContentType("text/html;charset=UTF-8");
		String appid = request.getParameter(appidKey);
		String appKey = getAppkey(appid);
		if(appKey==null) {
	          response.getWriter().print(JSONObject.toJSONString(JsonResult.getResult(JsonResult.STATUS_ERR_INTERFLOW,"参数错误")));
	          return false;
		}
		
		String version = request.getParameter(versionKey);
		initAppInterface(appid, version);
		
		if(!checkSign(request.getParameter(signKey), request.getParameterMap(),appKey,signKey)){
	          response.getWriter().print(JSONObject.toJSONString(JsonResult.getResult(JsonResult.STATUS_ERR_INTERFLOW,"签名错误")));
	          return false;
		}
			
		
		return true;
		
	}
	  
	private void initAppInterface(String appid,String version) {
		AppInterface itf = new AppInterface();
		itf.setVersion(version);
		itf.setAppid(appid);
		AppUtils.setAppInterfaces(itf);

	}
	  
	
	private String getAppkey(String client) {
		String appkey = AppContext.appKeys.get(client);
		 if(appkey!=null)return appkey;
		
		return null;
	}
	
	
	public boolean checkSign(String sign,Map<String, String[]> params,String appkey,String eparam) {
		String csign =  DigestUtils.md5Hex(buildSignStr(params,eparam)+"&"+reqKey+"="+appkey);
		if(!csign.equals(sign))return false;
		return true;
	}
	
	 public String buildSignStr(Map<String, String[]> params,String eparam) {
	        StringBuilder sb = new StringBuilder();
	        // 将参数以参数名的字典升序排序
	        Map<String, String[]> sortParams = new TreeMap<String, String[]>(params);
	        // 遍历排序的字典,并拼接"key=value"格式
	        for (Map.Entry<String, String[]> entry : sortParams.entrySet()) {
	        	if(entry.getKey().equals(eparam)||"".equals(entry.getValue()[0]))continue;
	        	
	            if (sb.length()!=0) {
	                sb.append("&");
	            }
	            sb.append(entry.getKey()).append("=").append(entry.getValue()[0]);
	        }
	        System.out.println(sb.toString());
	        return sb.toString();
	    }
	
}
