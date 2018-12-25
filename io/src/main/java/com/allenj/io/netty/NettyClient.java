package com.allenj.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public void connect(String ip, int port) {
		final MyClientChannelHandler clientHandler = new MyClientChannelHandler();
		new Thread() {
			public void run() {
				clientHandler.start();
			}
		}.start();
		NioEventLoopGroup eg = new NioEventLoopGroup();
		Bootstrap boot = new Bootstrap();
		boot.group(eg)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(clientHandler);
				}
				
			});
		try {
			ChannelFuture f = boot.connect(ip, port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			eg.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new NettyClient().connect("127.0.0.1", 8888);
	}
}
