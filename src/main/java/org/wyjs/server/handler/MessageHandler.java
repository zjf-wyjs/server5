package org.wyjs.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.wyjs.server.bean.MessageBean;
import org.wyjs.server.bean.Users;
import org.wyjs.server.manager.ClientManager;
import org.wyjs.server.service.UserService;

import java.util.Map;

/**
 * 消息处理者
 * @author Administrator
 */
public class MessageHandler {

    /**
     * 转发消息处理
     * @param bean
     */
    public static void forward(MessageBean bean) {

        //转发命令
        //获取目标对象
        String objective = bean.getObjective();
        if(objective==null){
            return;
        }
        //遍历获取用户名
        Map<String, ChannelId> clientId = ClientManager.clientId;
        ChannelId channelId = clientId.get(objective);
        if(channelId==null){
            System.out.println("目标主机不能存在");
            bean.setContext("目标主机不能存在");
            ClientManager.adminGroup.writeAndFlush(bean);
            return;
        }
        //如果目标主机存在
        Channel channel = ClientManager.clientGroup.get(channelId);

        //判断是否是登录
        if(bean.getContext().contains("login")){
            //从数据获取
            String[] s = bean.getContext().split(" ");
            Users users = UserService.getInstance().queryUserByUid(s[3]);
            if(users==null){
                bean.setContext("没有找到这个用户");
                ClientManager.adminGroup.writeAndFlush(bean);
                return;
            }
            //重组字符串
            StringBuilder sb = new StringBuilder();
            sb.append(s[0]).append(" ")
                    .append(s[1]).append(" ")
                    .append(s[2]).append(" ")
                    .append(users.getUsername()).append(" ")
                    .append(users.getPassword());
            bean.setContext(sb.toString());
        }

        //将消息发送给目标主机
        channel.writeAndFlush(bean);
        System.out.println("收到转发消息处理："+bean.getContext());

    }

    /**
     * 对服务处理
     * @param bean
     */
    public static void server(MessageBean bean) {
        CommandHandler instance = CommandHandler.getInstance();
        //解析这个命令
        instance.parse(bean.getContext());

    }
}
