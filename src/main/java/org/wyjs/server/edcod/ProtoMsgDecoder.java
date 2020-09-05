package org.wyjs.server.edcod;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.wyjs.server.bean.MessageBean;

import java.util.List;

/**
 * 解码，将bean转换为class对象
 * @author Administrator
 */
public class ProtoMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Schema<MessageBean> schema = RuntimeSchema.getSchema(MessageBean.class);
        MessageBean messageBean = schema.newMessage();
        byte[] array = new byte[byteBuf.readableBytes()];
        System.out.println("字节长度："+array.length);
        byteBuf.readBytes(array);
        ProtobufIOUtil.mergeFrom(array, messageBean, schema);
        list.add(messageBean);
    }
}
