package com.vincent.julie.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.impl.StdScheduler;
import org.springframework.web.context.WebApplicationContext;



/**
 * @Title: QuartzContextListener.java
 * @Package com.vincent.julie.utils
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author Vincent
 * @date 2017��3��1�� ����12:54:06
 * @version V1.0
 */
public class QuartzContextListener implements ServletContextListener {

	/*
	 * ���Դ���д�����
	 * 
	 * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		WebApplicationContext webApplicationContext = (WebApplicationContext) arg0.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if(webApplicationContext == null){
			return;
		}
		StdScheduler startQuertz = (StdScheduler) webApplicationContext  
	                .getBean("startQuertz");  
		if (startQuertz!= null) {
			startQuertz.shutdown();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
}
