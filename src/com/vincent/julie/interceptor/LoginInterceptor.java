package com.vincent.julie.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * @Title: LoginInterceptor.java
 * @Package com.vincent.julie
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: Vinent QQ:1032006226
 * @date: 2018年3月17日 下午4:45:20
 * @version V1.0
 * @Copyright: 2018 注意：本内容仅限于是我写的
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getCookies());
		
		
		Object o = request.getSession().getAttribute("API_TOKEN");
		String headToken = request.getHeader("API_TOKEN");
		System.out.println("服务器的token->"+String.valueOf(o)+",客户端的-->"+headToken);
		
//		return String.valueOf(o).equals(headToken);
		return true;
	}
}
