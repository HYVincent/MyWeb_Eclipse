package com.vincent.julie.netty;

import com.vincent.julie.netty.msg.BaseMsg;
import io.netty.channel.socket.SocketChannel;

/**
 * @Project: schoolmallapi
 * @ClassName: NettyPush
 * @Description: 
 * @author:	chenpy
 * @date:	2016年11月1日
 * @version 1.0.0
 */

public class NettyPush {
    
	/**
	 * 向客户端发送消息
	 * @param pushMsg
	 */
    public static void push(BaseMsg baseMsg){
        SocketChannel channel = (SocketChannel) NettyContextChannelMap.get(baseMsg.getPhoneNum());
        if (channel != null) {
            channel.writeAndFlush(baseMsg);
        }
    }
}
