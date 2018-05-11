package com.park.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxapp.common.JsonResult;
import com.park.api.common.BaseController;
import com.park.api.common.SpringFileupload;

@RequestMapping("fileService")
public class FilerController extends BaseController{

	@RequestMapping("upload")
	@ResponseBody
	public JsonResult fileUpload(HttpServletRequest request,String url,String field) {
		
		if(SpringFileupload.isFile(request, "file")) {
			String[] files = SpringFileupload.uploadFiles(request, "file");
			return JsonResult.getSuccessResult(files);
		}
		
		return JsonResult.getFailResult("无文件上传");
		

	}
	
}
