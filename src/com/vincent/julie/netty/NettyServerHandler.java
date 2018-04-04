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
 * @Description: �ͻ�����Ϣ����
 * @author:	chenpy
 * @date:	2016��11��1��
 * @version 1.0.0
 */
@Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {
	

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// channelʧЧ����Map���Ƴ�
		System.out.println("�Ƴ�ʧЧ��channel");
		NettyContextChannelMap.remove((SocketChannel) ctx.channel());
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause);
		System.out.println("�������쳣��");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
		BaseMsg baseMsg = (BaseMsg) arg1;
		System.out.println("baseMsg-->"+baseMsg.getPhoneNum()+" type = "+baseMsg.getType());
		channelRead0(arg0, baseMsg);
		super.channelRead(arg0, arg1);
	}
	
	/**
	 * �������ͳһ����ͻ��˷��͹�������Ϣ
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
		switch (baseMsg.getType()) {
			case PING:
				PingMsg pingMsg = (PingMsg) baseMsg;
				PingMsg replyPing = new PingMsg();
				replyPing.setPhoneNum(pingMsg.getPhoneNum());
				NettyPush.push(replyPing);
				System.out.println("�յ�����"+pingMsg.getPhoneNum()+"����Ϣ-->type:"+pingMsg.getType()+" "+
						DateUtils.getDateString(DateUtils.DATE_FORMAT_ALL, System.currentTimeMillis()));
				break;
			case LOGIN:
				LoginMsg loginMsg = (LoginMsg) baseMsg;
				if (NettyContextChannelMap.get(loginMsg.getPhoneNum()) == null) {
					// ��¼�ɹ�,��channel�浽����˵�map��
					NettyContextChannelMap.add(loginMsg.getPhoneNum(), (SocketChannel) channelHandlerContext.channel());
					System.out.println("�û���"+loginMsg.getPhoneNum()+" �ɹ�������....");
					//TODO ��¼�ɹ�֮��Ӧ�ð��û������ߵ�ʱ�����Ϣ���͸��û�
					PushMsg pushMsg = new PushMsg();
					pushMsg.setPhoneNum(loginMsg.getPhoneNum());
					pushMsg.setType(MsgType.PUSH);
					pushMsg.setData("�ɹ����ӵ�Netty������");
					NettyPush.push(pushMsg);
				}
				break;
			case PUSH:
				PushMsg pushMsg = (PushMsg) baseMsg;
				System.out.println("�յ��ͻ���--> "+pushMsg.getPhoneNum() + "���͵���Ϣ: content = " + String.valueOf(pushMsg.getData()));
				break;
			default:
				break;
		}
		ReferenceCountUtil.release(baseMsg);
	}
}