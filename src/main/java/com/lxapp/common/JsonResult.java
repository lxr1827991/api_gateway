package com.lxapp.common;


public class JsonResult {
		//业务处理错误
		public static final int STATUS_FAIL = 0;
		//业务处理成功
		public static final int STATUS_SUCCESS = 1;
		//接口错误
		public static final int STATUS_ERR_INTERFLOW = 3;
		//接口调用流程错误
		public static final int STATUS_CALL_ERROR = 4;
		//未认证
		public static final int STATUS_UN_LOGIN = 5;
		//无权限
		public static final int STATUS_UN_AUTH = 6;
		//服务未找到
		public static final int STATUS_UN_SERVICE = 7;
		//参数不合法
		public static final int STATUS_INVALID_PARAMETER= 100;
		//通讯异常
		public static final int STATUS_TRANSMITTAL= 110;
		
		int code = 0;
		
		
		String sid;
		
		Object token;
		
		String msg;
		
		Object data;
		
		Long time;
		
		public JsonResult() {
			
		}
		
		public JsonResult(int s) {
			
			code = s;
		}

		
		public Object getToken() {
			return token;
		}

		public void setToken(Object token) {
			this.token = token;
		}
		
		
		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
		
		
		public static JsonResult getFailResult(String msg) {
			return getResult(STATUS_FAIL,msg);

		}
		
		
		public static JsonResult getFailResult(Object obj) {
			return getResult(STATUS_FAIL,null,obj);

		}
		public static JsonResult getFailResult(String msg,Object obj) {
			return getResult(STATUS_FAIL,msg,obj);

		}
		
		public static JsonResult getSuccessResult(Object obj) {
			return getResult(STATUS_SUCCESS,null,obj);

		}
		
		public static JsonResult getSuccessResult(String m) {
			return getResult(STATUS_SUCCESS,m,null);

		}
		
		public static JsonResult getSuccessResult() {
			return getResult(STATUS_SUCCESS,null,null);

		}
		
		
		
		public static JsonResult getResult(int s) {
			
			return getResult(s,null,null);

		}
		
		
		public static JsonResult getResult(int s,String m) {
			
			return getResult(s,m,null);

		}
		
		public static JsonResult getResult(int s,String m,Object d) {
			JsonResult result = new JsonResult(s);
			result.setMsg(m);
			result.setData(d);
			return result;

		}
		
}
