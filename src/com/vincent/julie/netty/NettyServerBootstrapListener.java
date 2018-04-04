package com.vincent.julie.netty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.vincent.julie.Config;
import com.vincent.julie.tcp.TcpService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @Project: schoolmallapi
 * @ClassName: NettyServerBootstrap
 * @Description:netty服务端
 * @author: chenpy
 * @date: 2016年11月1日
 * @version 1.0.0
 */
public class NettyServerBootstrapListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent sce) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(boss, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.SO_BACKLOG, 128);
		// 通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		// 保持长连接状态
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

			protected void initChannel(SocketChannel socketChannel) throws Exception {
				ChannelPipeline p = socketChannel.pipeline();
				p.addLast(new ObjectEncoder());
				p.addLast(new ObjectDecoder(1024 * 1024,
						ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
				p.addLast(new NettyServerHandler());
			}
		});

		ChannelFuture f;
		try {
			f = bootstrap.bind(Config.NETTY_PUSH_PORT).sync();
			if (f.isSuccess()) {
				System.out.println("netty server start success.");
			}else {
				System.out.println("netty server start faile.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
