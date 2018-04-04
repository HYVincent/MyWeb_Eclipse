package com.vincent.julie.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.ognl.ParseException;

/**  
 * @Title:  SecurityUtil.java   
 * @Package com.vincent.julie.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年3月3日 上午7:50:52   
 * @version V1.0 安全类工具类
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */

public class SecurityUtil {
	/** 
     *  
     *  @Description    : 身份验证token值算法： 
     *                              算法是：将特定的某几个参数一map的数据结构传入， 
     *                              进行字典序排序以后进行md5加密,32位小写加密； 
     *  @Method_Name    : authentication 
     *  @param token        请求传过来的token 
     *  @param srcData   约定用来计算token的参数 
     *  @return  
     */  
    public static String authentication(Map<String , Object > srcData) throws ParseException{  
        //排序，根据keyde 字典序排序  
        if(null == srcData){  
            throw new ParseException("传入参数为空");  
        }  
        List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(srcData.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>(){  
            //升序排序  
            public int compare(Entry<String,Object> o1, Entry<String,Object> o2){  
                return o1.getKey().compareTo(o2.getKey());  
            }  
        });  
          
        StringBuffer srcSb = new StringBuffer();  
        for(Map.Entry<String , Object>srcAtom : list){  
            srcSb.append(String.valueOf(srcAtom.getValue()));  
        }  
        System.out.println("身份验证加密前字符串："+srcSb.toString());  
        //计算token  
        String token = MD5Util.md5(srcSb.toString());  
//      System.out.println(cToken);//for test  
        return token;  
    }  
    
    /**
     * 随机生成字母数字
     * @param length
     * @return
     */
    public static String gen(int length) {
    	 char[] ss = new char[length];
    	 int i=0;
    	while(i<length) {
    	    int f = (int) (Math.random()*3);
    	    if(f==0)  
    	     ss[i] = (char) ('A'+Math.random()*26);
    	    else if(f==1)  
    	     ss[i] = (char) ('a'+Math.random()*26);
    	    else 
    	     ss[i] = (char) ('0'+Math.random()*10);    
    	    i++;
    	 }
    	String is=new String(ss);
    	 return is;
    	}
    
    /**
     * 测试..
     * @param args
     */
    public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("username", "vincent");
		map.put("password", "555555");
		map.put("phoneNum", "18723768729");
		map.put("random", gen(10));
		try {
			String token = authentication(map);
			System.out.println("token = "+token);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
      
      
}  