package com.vincent.julie.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.impl.StdScheduler;
import org.springframework.web.context.WebApplicationContext;



/**
 * @Title: QuartzContextListener.java
 * @Package com.vincent.julie.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Vincent
 * @date 2017年3月1日 上午12:54:06
 * @version V1.0
 */
public class QuartzContextListener implements ServletContextListener {

	/*
	 * 测试代码写得随便
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
