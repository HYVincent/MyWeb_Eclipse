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
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年3月9日 下午10:25:03   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */

public class TcpService {

	public static TcpService instance;
	private static boolean endFlag = false;
	//socketService开启的端口
	private static int  SOCKETSERVICE_PORT = 8081;
	
	/**
	 * 用来保存所有连接到ServiceSocket的客户端Socket
	 */
	private HashMap<String, Socket> socketList = new HashMap<String,Socket>();
	
	public static TcpService geTcpService() {
		if(instance == null) {
			instance = new TcpService();
		}
		return instance;
	}
	
	/**
	 * 开启socket，等待客户端来连接
	 */
	public void startTcpService() {
		try {
			ServerSocket serverSocket = new ServerSocket(SOCKETSERVICE_PORT);
			
			while(!endFlag) {
				//等待客户端连接
				Socket client = serverSocket.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				//注意第二个参数为true时会自动flush,否则需要手动操作outputStream.flush()
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
