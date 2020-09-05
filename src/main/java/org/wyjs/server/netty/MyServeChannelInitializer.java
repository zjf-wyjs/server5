package org.wyjs.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.wyjs.server.edcod.ProtoMsgDecoder;
import org.wyjs.server.edcod.ProtoMsgEncoder;
import org.wyjs.server.handler.NettyServerHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class MyServeChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("framedecoder",new LengthFieldBasedFrameDecoder(1024*1024*10,0,4,0,4));
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new ProtoMsgEncoder());
        pipeline.addLast(new ProtoMsgDecoder());
        //加入心跳检测机制
        pipeline.addLast(new IdleStateHandler(30,30,60, TimeUnit.SECONDS));
        pipeline.addLast(new NettyServerHandler());

    }
}
