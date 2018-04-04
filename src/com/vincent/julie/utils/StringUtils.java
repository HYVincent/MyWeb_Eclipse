package com.vincent.julie.utils;

/**  
 * @Title:  StringUtils.java   
 * @Package com.vincent.julie.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年1月31日 下午11:23:45   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */

public class StringUtils {
	
	/**
	 * 检查字符串是否为空或者长度为0
	 * @param str
	 * @return
	 */
	public static boolean isEntity(String str){
		if(str == null || str.length() == 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * 比较两个字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equest(String str1,String str2) {
		if(str1.equals(str2)) {
			return true;
		}else {
			return false;
		}
	}

}
