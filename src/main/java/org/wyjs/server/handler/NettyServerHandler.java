package org.wyjs.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.wyjs.server.bean.MessageBean;
import org.wyjs.server.bean.MessageTypeEnum;
import org.wyjs.server.constant.NettyConstant;
import org.wyjs.server.constant.Older;
import org.wyjs.server.manager.ClientManager;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端是否是第一次给服务器发消息
     */
    private boolean is = true;

    /**
     * 接收数据时调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        MessageBean bean = (MessageBean) msg;
        bean.setId(channel.id());
        //添加入管理器
        add(channel, bean);


        //处理这个消息
        message(bean);
        System.out.println(bean);
    }

    /**
     * 断开连接从管理器中移除目标对象
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        ClientManager.clientGroup.remove(id);
        Iterator<String> iterator = ClientManager.clientId.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (ClientManager.clientId.get(next).equals(id)) {
                iterator.remove();
                System.out.println(next + "断开了连接");
                //将掉线这个用户发送给服务端
                MessageBean messageBean = new MessageBean();
                messageBean.setObjective(Older.MyOlder.OFFLINE);
                messageBean.setName(Older.MyOlder.SERVER_LIST);
                messageBean.setContext(next);
                ClientManager.adminGroup.writeAndFlush(messageBean);
                return;
            }
        }
        //System.out.println(ctx.channel().remoteAddress() + "断开了连接");
    }

    /**
     * 转发消息
     * 如果是客户端发送的消息，就转发给管理端，如果是管理端的消息，就发送给目标客户端
     *
     * @param bean
     */
    private void message(MessageBean bean) {
        //如果是管理端
        if (NettyConstant.ADMIN_NAME.equals(bean.getName())) {
            //判断是什么命令
            if (bean.getType() == MessageTypeEnum.FORWARD) {
                //如果是转发消息，就处理这个转发消息
                MessageHandler.forward(bean);
            } else if (bean.getType() == MessageTypeEnum.SERVER) {
                //如果是对服务器操作的命令
                MessageHandler.server(bean);
            }

        } else {
//            if(!"未知命令".equals(bean.getContext())) {
            //发消息的是客户端，就转发给管理端
            System.out.println("转发" + ClientManager.adminGroup.size());
            System.out.println("数据大小为：" + bean.toString().getBytes(CharsetUtil.UTF_8).length);
            MessageBean mb = new MessageBean(bean);
            ClientManager.adminGroup.writeAndFlush(mb);
//            }
        }
    }

    /**
     * 将发消息过来的客户端添加进管理器
     *
     * @param channel
     * @param bean
     */
    private void add(Channel channel, MessageBean bean) {
        if (is) {
            //如果是管理员
            if (NettyConstant.ADMIN_NAME.equals(bean.getName())) {
                ClientManager.adminGroup.add(channel);
                System.out.println("新增管理端：" + ClientManager.adminGroup.size());
            } else {
                //普通用户
                //判断用户名是否已经存在
                Map<String, ChannelId> clientId = ClientManager.clientId;
                Map<ChannelId, Channel> clientGroup = ClientManager.clientGroup;

                //如果客户端名称已存在
                if (clientId.containsKey(bean.getName())) {
                    bean.setName(bean.getName() + "(" + bean.getId() + ")");
                }
                //将客户端对象存储
                clientId.put(bean.getName(), bean.getId());
                clientGroup.put(bean.getId(), channel);
                //将新增的这个客户端通知给管理端
                MessageBean messageBean = new MessageBean();
                messageBean.setObjective(Older.MyOlder.ONLINE);
                messageBean.setContext(bean.getName());
                messageBean.setName(Older.MyOlder.SERVER_LIST);
                ClientManager.adminGroup.writeAndFlush(messageBean);

                System.out.println("新增普通客户端：" + bean.getName());
            }
            is = false;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        ctx.close();
    }

    /**
     * 心跳检测
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()){
                case ALL_IDLE:
                    ChannelId id = ctx.channel().id();
                    System.out.println("发送心跳包：" + id.asShortText());
                    MessageBean mb = new MessageBean();
                    mb.setContext("browser idle");
                    ctx.writeAndFlush(mb);
                    break;
                default:
            }

        }
    }
}
