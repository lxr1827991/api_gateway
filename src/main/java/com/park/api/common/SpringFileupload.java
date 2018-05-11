package com.park.api.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.lxr.commons.exception.ApplicationException;

public class SpringFileupload {
	
	
	public static final String FILE_TYPE = "img";
	
	
	
	public static String upload(MultipartFile  file) throws IllegalStateException, IOException {
		 if (file.isEmpty()) return null;
	           
	            String fileName=file.getOriginalFilename();// 文件原名称
	            
	            // 判断文件类型
	            fileName = java.net.URLEncoder.encode(fileName,"utf-8").replace("%", "");
	             
	            String webroot = "upload/"+DateFormatUtils.format(new Date(), "yyyy-MM-dd");
	            
	                    String realPath= new File(getTomcatHost(),"webapps/"+webroot).getPath();
	                    
	                    String trueFileName= genFileName(fileName);
	                   
	                    if(!new File(realPath).exists())
	                    	new File(realPath).mkdirs();
	                   
	                    String path=realPath+"/"+trueFileName;
	                   
	                    // 转存文件到指定的路径
	                    file.transferTo(new File(path));
	                  
	                    System.out.println("上传的文件 :"+fileName); 
	       
		return "/"+webroot+"/"+trueFileName;

	}
	
	
	public static String genFileName(String fileName){
		String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
		
		return trueFileName;
	}
	
	
	public static String upload(MultipartHttpServletRequest  request,String name) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multipartRequest =  request;         
		// 获得文件：
		MultipartFile mfile = (MultipartFile) multipartRequest.getFile(name); 
	    
		return upload(mfile);

	}
	
	public static String[] uploadFiles(MultipartHttpServletRequest  request,String name) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multipartRequest =  request;         
		// 获得文件：
		List<MultipartFile> mfile =  multipartRequest.getFiles(name); 
	    String[] files = new String[mfile.size()];
	    for (int i = 0; i < files.length; i++) {
	    	files[i] = upload(mfile.get(i));
		}
	    return files;
	}
	
	public static String[] uploadFiles(HttpServletRequest  request,String name) {
		 CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	       
		  String path = null;
	        // 判断是否有文件上传  
	        if (!commonsMultipartResolver.isMultipart(request))throw new MultipartException("Could not parse multipart servlet request");
	
		
		
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;         
		
	try {
			return uploadFiles(multipartRequest, name);
		}  catch (Exception e1) {
			throw new ApplicationException(e1);
		}
			

	}
	
	public static String upload(HttpServletRequest  request,String name) {
		 CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	       
		  String path = null;
	        // 判断是否有文件上传  
	        if (!commonsMultipartResolver.isMultipart(request))throw new MultipartException("Could not parse multipart servlet request");
	
		
		
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;         
		
	try {
			return upload(multipartRequest, name);
		}  catch (Exception e1) {
			throw new ApplicationException(e1);
		}
			

	}
	
	public static boolean isFile(HttpServletRequest  request,String name) {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	       
		  String path = null;
	        // 判断是否有文件上传  
	        if (!commonsMultipartResolver.isMultipart(request))return false;
	
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;         
			// 获得文件：
			MultipartFile mfile = (MultipartFile) multipartRequest.getFile(name);
		
			if(mfile==null)return false;
			
			return true;

	}
	
	
	public static String getTomcatHost() {
		return System.getProperty("catalina.home");

	}
	
	

}
