package com.vincent.julie.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vincent.julie.utils.UserUtils;

/**
 * @Project: MyWebProject
 * @ClassName: NettyContextChannelMap
 * @Description: ��������ӵ��û���Ϣ
 * @author:	chenpy
 * @date:	2016��11��1��
 * @version 1.0.0
 */
public class NettyContextChannelMap {
	
	private static Logger logger = LogManager.getLogger(NettyContextChannelMap.class);
	
    private static Map<String,SocketChannel> map=new ConcurrentHashMap<>();

    public static void add(String clientId,SocketChannel socketChannel){
        map.put(clientId,socketChannel);
    }

    public static Channel get(String clientId){
        return map.get(clientId);
    }

    /**
     * �Ƴ�
     * @param socketChannel
     */
    public static void remove(SocketChannel socketChannel){
        for (Map.Entry<String,SocketChannel> entry:map.entrySet()){
            if (entry.getValue()==socketChannel){
                String key = (String) entry.getKey();
                logger.debug("ͨ��"+ key +"�ѱ��Ƴ���");
                System.out.println("�û� "+key+" �˳���¼..");
                map.remove(key);
                UserUtils.getInstance().setLoginOutTime(key, System.currentTimeMillis());
            }
        }
    }

}