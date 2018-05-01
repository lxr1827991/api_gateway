package com.lxapp.common;

import java.util.Random;

import com.lxapp.common.bean.Vcode;


public class VcodeFactory {

	 static long l = 1000*60*10;
	
	
	
	public static Vcode newVcode(String code) {
		Vcode vcode = new Vcode();
		vcode.setCode(code);
		vcode.setExpiration(System.currentTimeMillis()+l);
		
		return vcode;

	}
	
	public static Vcode newVcode() {
		Vcode vcode = new Vcode();
		vcode.setCode(getFixLenthString(6));
		vcode.setExpiration(System.currentTimeMillis()+l);
		
		return vcode;

	}

	
	/* 
	 * 返回长度为【strLength】的随机数，在前面补0 
	 */  
	private static String getFixLenthString(int strLength) {  
	      
	    Random rm = new Random();  
	      
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	}  
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(getFixLenthString(6)+"");
		}
	}
	
}
