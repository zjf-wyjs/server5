package org.wyjs.server.manager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.wyjs.server.bean.MessageBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端管理
 *
 * @author Administrator
 */
public class ClientManager {
    /**
     * 存储管理员
     */
    public static ChannelGroup adminGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存储普通客户端
     * ChannelId 客户端id
     *
     */
    public static Map<ChannelId,Channel> clientGroup=new ConcurrentHashMap<ChannelId,Channel>();

    /**
     * 根据名字存储ChannelId
     */
    public static Map<String,ChannelId> clientId=new ConcurrentHashMap<>();
}
