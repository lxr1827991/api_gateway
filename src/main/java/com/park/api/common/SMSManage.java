package com.park.api.common;

import com.lxr.commons.exception.ApplicationException;

public class SMSManage {

	
	public static  <T> T getService(Class<T> cls) {
		try {
			return  (T) cls.newInstance();
		}  catch (Exception e) {
			throw new ApplicationException(e);
		}

	}
}
