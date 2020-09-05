package org.wyjs.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Administrator
 */
public class NettyServer {
    /**
     * 启动
     * @param host 主机地址
     * @param port  ip
     */
    public static void start(String host,int port){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try{
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //接收缓冲区
                    .option(ChannelOption.SO_RCVBUF,1024*16)
                    .option(ChannelOption.SO_SNDBUF,1024*16)
                    //设置线程队列连接等待个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new MyServeChannelInitializer());
            System.out.println("服务端开始服务");
            ChannelFuture sync = bootstrap.bind(host, port).sync();
            sync.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
