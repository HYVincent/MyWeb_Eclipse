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
 * @Description: 存放已连接的用户信息
 * @author:	chenpy
 * @date:	2016年11月1日
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
     * 移除
     * @param socketChannel
     */
    public static void remove(SocketChannel socketChannel){
        for (Map.Entry<String,SocketChannel> entry:map.entrySet()){
            if (entry.getValue()==socketChannel){
                String key = (String) entry.getKey();
                logger.debug("通道"+ key +"已被移除。");
                System.out.println("用户 "+key+" 退出登录..");
                map.remove(key);
                UserUtils.getInstance().setLoginOutTime(key, System.currentTimeMillis());
            }
        }
    }

}