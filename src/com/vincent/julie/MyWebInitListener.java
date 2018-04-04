package com.vincent.julie;

import javax.servlet.ServletContextListener;

import com.vincent.julie.tcp.TcpService;

/**  
 * @Title:  MyWebInitListener.java   
 * @Package com.vincent.julie   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年3月9日 下午10:51:48   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */

public class MyWebInitListener implements ServletContextListener {

	private static MyWebInitListener instance;
	
	public MyWebInitListener() {
		//初始化方法写在这里
//		System.out.println("start socket service..");
		init();
	}
	
	public static MyWebInitListener getInstance() {
		if(instance == null) {
			instance = new MyWebInitListener();
		}
//		System.out.println("..............");
		return instance;
	}
	
	public void init() {
		//Myweb启动初始化
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				TcpService.geTcpService().startTcpService();
			}
		}).start();*/
	}
	
}
