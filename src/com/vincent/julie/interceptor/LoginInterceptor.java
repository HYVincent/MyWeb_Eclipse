package com.vincent.julie.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * @Title: LoginInterceptor.java
 * @Package com.vincent.julie
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author: Vinent QQ:1032006226
 * @date: 2018��3��17�� ����4:45:20
 * @version V1.0
 * @Copyright: 2018 ע�⣺�����ݽ���������д��
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getCookies());
		
		
		Object o = request.getSession().getAttribute("API_TOKEN");
		String headToken = request.getHeader("API_TOKEN");
		System.out.println("��������token->"+String.valueOf(o)+",�ͻ��˵�-->"+headToken);
		
//		return String.valueOf(o).equals(headToken);
		return true;
	}
}
