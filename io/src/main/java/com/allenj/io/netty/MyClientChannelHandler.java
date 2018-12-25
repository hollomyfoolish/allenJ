package com.allenj.io.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

public class MyClientChannelHandler extends ChannelInboundHandlerAdapter {
	private static final String code = "UTF-8";
    private volatile ChannelHandlerContext ctx;
    private final Object lock = new Object();

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	synchronized (lock) {
    		this.ctx = ctx;
    		lock.notify();
		}
    }
    
    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	ByteBuf bf = (ByteBuf)msg;
    	int len = bf.readableBytes();
    	byte[] content = new byte[len];
    	bf.readBytes(content, 0, len);
    	System.out.println("server msg received: " + new String(content, code));
    }
    
    public void start() {
    	synchronized (lock) {
    		while(ctx == null) {
    			try {
    				System.out.println("connecting ...");
					lock.wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		System.out.println("connected ...");
		}
    	
    	try {
    		String line = null;
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "GBK"));
			do {
				System.out.println("you say: ");
				line = br.readLine();
				send(line);
			}while(line != null && line.length() > 0);
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ctx.channel().close();
		}
    }

	private void send(String line) throws UnsupportedEncodingException {
		byte[] msg = line.getBytes(code);
		ByteBuf buffer = Unpooled.buffer(msg.length);
		buffer.writeBytes(msg);
		ctx.write(buffer);
		ctx.flush();
	}
}
