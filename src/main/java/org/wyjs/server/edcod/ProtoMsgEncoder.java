package org.wyjs.server.edcod;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.wyjs.server.bean.MessageBean;

/**
 * @author Administrator
 */
public class ProtoMsgEncoder extends MessageToByteEncoder<MessageBean> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageBean messageBean, ByteBuf byteBuf) throws Exception {
        Schema<MessageBean> schema = RuntimeSchema.getSchema(MessageBean.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(10024);
        byte[] array = ProtobufIOUtil.toByteArray(messageBean, schema, buffer);
        byteBuf.writeBytes(array);
    }
}
