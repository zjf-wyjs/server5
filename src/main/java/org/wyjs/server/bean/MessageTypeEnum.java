package org.wyjs.server.bean;

/**
 * 消息类型
 * @author Administrator
 */

public enum MessageTypeEnum {
    /**
     * 服务器命令，对服务器操作
     */
    SERVER,
    /**
     * 转发命令，也就是发送给指定客户端的命令
     */
    FORWARD;


}
