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
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��3��3�� ����7:50:52   
 * @version V1.0 ��ȫ�๤����
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class SecurityUtil {
	/** 
     *  
     *  @Description    : �����֤tokenֵ�㷨�� 
     *                              �㷨�ǣ����ض���ĳ��������һmap�����ݽṹ���룬 
     *                              �����ֵ��������Ժ����md5����,32λСд���ܣ� 
     *  @Method_Name    : authentication 
     *  @param token        ���󴫹�����token 
     *  @param srcData   Լ����������token�Ĳ��� 
     *  @return  
     */  
    public static String authentication(Map<String , Object > srcData) throws ParseException{  
        //���򣬸���keyde �ֵ�������  
        if(null == srcData){  
            throw new ParseException("�������Ϊ��");  
        }  
        List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(srcData.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>(){  
            //��������  
            public int compare(Entry<String,Object> o1, Entry<String,Object> o2){  
                return o1.getKey().compareTo(o2.getKey());  
            }  
        });  
          
        StringBuffer srcSb = new StringBuffer();  
        for(Map.Entry<String , Object>srcAtom : list){  
            srcSb.append(String.valueOf(srcAtom.getValue()));  
        }  
        System.out.println("�����֤����ǰ�ַ�����"+srcSb.toString());  
        //����token  
        String token = MD5Util.md5(srcSb.toString());  
//      System.out.println(cToken);//for test  
        return token;  
    }  
    
    /**
     * ���������ĸ����
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
     * ����..
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