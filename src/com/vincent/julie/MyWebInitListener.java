package com.vincent.julie;

import javax.servlet.ServletContextListener;

import com.vincent.julie.tcp.TcpService;

/**  
 * @Title:  MyWebInitListener.java   
 * @Package com.vincent.julie   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��3��9�� ����10:51:48   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class MyWebInitListener implements ServletContextListener {

	private static MyWebInitListener instance;
	
	public MyWebInitListener() {
		//��ʼ������д������
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
		//Myweb������ʼ��
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				TcpService.geTcpService().startTcpService();
			}
		}).start();*/
	}
	
}
