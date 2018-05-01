package com.park.api;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class GatewayUtils {

	private static ThreadLocal<byte[]> requestBody = new ThreadLocal<>();
	
	public static byte[] getBody() {
		
		return requestBody.get();
	}
	
	
	public static void setBody(HttpServletRequest request) throws IOException {
		
	 requestBody.set(binaryReader(request));
	}
	
	
	
	
	public static byte[] binaryReader(HttpServletRequest request) throws IOException {
		int len = request.getContentLength();
		ServletInputStream iii = request.getInputStream();
		byte[] buffer = new byte[len];
		iii.read(buffer, 0, len);
		return buffer;
	}
}
