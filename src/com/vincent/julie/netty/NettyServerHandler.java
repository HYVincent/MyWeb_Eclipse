package com.vincent.julie.netty;

import com.vincent.julie.netty.msg.BaseMsg;
import com.vincent.julie.netty.msg.LoginMsg;
import com.vincent.julie.netty.msg.MsgType;
import com.vincent.julie.netty.msg.PingMsg;
import com.vincent.julie.netty.msg.PushMsg;
import com.vincent.julie.utils.DateUtils;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * @Project: schoolmallapi
 * @ClassName: NettyServerHandler
 * @Description: 客户端消息处理
 * @author:	chenpy
 * @date:	2016年11月1日
 * @version 1.0.0
 */
@Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {
	

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// channel失效，从Map中移除
		System.out.println("移除失效的channel");
		NettyContextChannelMap.remove((SocketChannel) ctx.channel());
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause);
		System.out.println("服务器异常了");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
		BaseMsg baseMsg = (BaseMsg) arg1;
		System.out.println("baseMsg-->"+baseMsg.getPhoneNum()+" type = "+baseMsg.getType());
		channelRead0(arg0, baseMsg);
		super.channelRead(arg0, arg1);
	}
	
	/**
	 * 这个方法统一处理客户端发送过来的消息
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
		switch (baseMsg.getType()) {
			case PING:
				PingMsg pingMsg = (PingMsg) baseMsg;
				PingMsg replyPing = new PingMsg();
				replyPing.setPhoneNum(pingMsg.getPhoneNum());
				NettyPush.push(replyPing);
				System.out.println("收到来自"+pingMsg.getPhoneNum()+"的消息-->type:"+pingMsg.getType()+" "+
						DateUtils.getDateString(DateUtils.DATE_FORMAT_ALL, System.currentTimeMillis()));
				break;
			case LOGIN:
				LoginMsg loginMsg = (LoginMsg) baseMsg;
				if (NettyContextChannelMap.get(loginMsg.getPhoneNum()) == null) {
					// 登录成功,把channel存到服务端的map中
					NettyContextChannelMap.add(loginMsg.getPhoneNum(), (SocketChannel) channelHandlerContext.channel());
					System.out.println("用户："+loginMsg.getPhoneNum()+" 成功上线了....");
					//TODO 登录成功之后应该把用户不在线的时候的消息推送给用户
					PushMsg pushMsg = new PushMsg();
					pushMsg.setPhoneNum(loginMsg.getPhoneNum());
					pushMsg.setType(MsgType.PUSH);
					pushMsg.setData("成功连接到Netty服务器");
					NettyPush.push(pushMsg);
				}
				break;
			case PUSH:
				PushMsg pushMsg = (PushMsg) baseMsg;
				System.out.println("收到客户端--> "+pushMsg.getPhoneNum() + "发送的消息: content = " + String.valueOf(pushMsg.getData()));
				break;
			default:
				break;
		}
		ReferenceCountUtil.release(baseMsg);
	}
}