package com.pokerface.util;

public class StringUtil {

	public static boolean isEmpty(String str){
		return str == null || str.isEmpty();
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	public static String nonNull(String str){
		return str == null?"":str;
	}
	
	public static int parseInt(String str){
		int result;
		try{
			result = Integer.parseInt(str);
		} catch(Exception e) {
			result = 0;
		}
		return result;
	}
	
	public static String versionIncrease(String version){
		return versionIncrease(version, 3);
	}
	
	/**
	 * 
	 * @param version 1.0.1
	 * @param part 1=大版本 2=中版本 3=小版本
	 * @return
	 */
	public static String versionIncrease(String version, int part){
		String[] parts = nonNull(version).split("\\.");
		if(parts.length != 3 || part < 1 || part > 3){
			return version;
		}
		part = part - 1;
		int increasePart = parseInt(parts[part]);
		increasePart++;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<parts.length;i++){
			if(i==part){
				sb.append(increasePart);
			} else {
				sb.append(parts[i]);
			}
			sb.append(".");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
