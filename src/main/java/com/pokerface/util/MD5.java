package com.pokerface.util;

import java.security.MessageDigest;

public class MD5 {
	
	public static String encode(String pwd){
		if(pwd == null){
			pwd = "";
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'a', 'b', 'c', 'd', 'e', 'f' };  
		String encryptPwd = pwd;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(pwd.getBytes());
			int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
			encryptPwd = new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptPwd;
	}
	
	public static void main(String[] args){
		System.out.println(encode("zhizun888"));
		System.out.println(encode("PW8814"));
	}

}
