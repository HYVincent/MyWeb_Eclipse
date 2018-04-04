package com.vincent.julie.utils;

import java.security.MessageDigest;

/**  
 * @Title:  MD5Utils.java   
 * @Package com.vincent.julie.utils   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��3��3�� ����7:52:16   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class MD5Util {

	 private static final String encryModel="MD5";  
	    /** 
	     * 32��md5. 
	     * 32λСдmd5���� 
	     * @param str 
	     * @return 
	     */  
	    public  static String md5(String str) {  
	        return encrypt(encryModel, str);  
	    }  
	    
	    
	    public static String encrypt(String algorithm, String str) {  
	        try {  
	            MessageDigest md = MessageDigest.getInstance(algorithm);  
	            md.update(str.getBytes());  
	            StringBuffer sb = new StringBuffer();  
	            byte[] bytes = md.digest();  
	            for (int i = 0; i < bytes.length; i++) {  
	                int b = bytes[i] & 0xFF;  
	                if (b < 0x10) {  
	                    sb.append('0');  
	                }  
	                sb.append(Integer.toHexString(b));  
	            }  
	            return sb.toString();  
	        } catch (Exception e) {  
	            return "";  
	        }  
	    }  
	
}
