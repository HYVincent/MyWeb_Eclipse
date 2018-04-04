package com.vincent.julie.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import com.vincent.julie.utils.StringUtils;



/**  
 * @Title:  TcpService.java   
 * @Package com.vincent.julie.tcp   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��3��9�� ����10:25:03   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class TcpService {

	public static TcpService instance;
	private static boolean endFlag = false;
	//socketService�����Ķ˿�
	private static int  SOCKETSERVICE_PORT = 8081;
	
	/**
	 * ���������������ӵ�ServiceSocket�Ŀͻ���Socket
	 */
	private HashMap<String, Socket> socketList = new HashMap<String,Socket>();
	
	public static TcpService geTcpService() {
		if(instance == null) {
			instance = new TcpService();
		}
		return instance;
	}
	
	/**
	 * ����socket���ȴ��ͻ���������
	 */
	public void startTcpService() {
		try {
			ServerSocket serverSocket = new ServerSocket(SOCKETSERVICE_PORT);
			
			while(!endFlag) {
				//�ȴ��ͻ�������
				Socket client = serverSocket.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				//ע��ڶ�������Ϊtrueʱ���Զ�flush,������Ҫ�ֶ�����outputStream.flush()
				PrintWriter output = new PrintWriter(client.getOutputStream(),true);
				String message = input.readLine();
				socketList.put(message, client);
				System.out.println("from "+InetAddress.getLocalHost()+" message:"+message);
				if(StringUtils.equest(message, "fff")) {
					output.println("fkjalfjkajf");
					output.flush();
				}else {
					output.println("ffffffffffffffffffffffffffffffffffffffffff");
					output.flush();
				}
				if("shutDown".equals(message)) {
					endFlag = true;
					System.out.println("socket service is shutDown.");
					client.close();
				}
			}
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("socket service start fail.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		geTcpService().startTcpService();
	}
	
}
