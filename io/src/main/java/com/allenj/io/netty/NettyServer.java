package com.allenj.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

//EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
//EventLoopGroup workerGroup = new NioEventLoopGroup();
//try {
//    ServerBootstrap b = new ServerBootstrap(); // (2)
//    b.group(bossGroup, workerGroup)
//     .channel(NioServerSocketChannel.class) // (3)
//     .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
//         @Override
//         public void initChannel(SocketChannel ch) throws Exception {
//             ch.pipeline().addLast(new DiscardServerHandler());
//         }
//     })
//     .option(ChannelOption.SO_BACKLOG, 128)          // (5)
//     .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
//
//    // Bind and start to accept incoming connections.
//    ChannelFuture f = b.bind(port).sync(); // (7)
//
//    // Wait until the server socket is closed.
//    // In this example, this does not happen, but you can do that to gracefully
//    // shut down your server.
//    f.channel().closeFuture().sync();
//} finally {
//    workerGroup.shutdownGracefully();
//    bossGroup.shutdownGracefully();
//}

public class NettyServer {
	private int port;

	public NettyServer(int port) {
		this.port = port;
	}
	
	public void start() {
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap boot = new ServerBootstrap();
		boot.group(boss, worker)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>(){
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new MySocketHandler());
				}
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
		// Bind and start to accept incoming connections.
        ChannelFuture f;
		try {
			System.out.println("server started");
			f = boot.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
        }

        // Wait until the server socket is closed.
        // In this example, this does not happen, but you can do that to gracefully
        // shut down your server.
	}
	
	public static void main(String[] args) {
		new NettyServer(8888).start();
	}

}
