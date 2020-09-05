package org.wyjs.server.bean;

import io.netty.channel.ChannelId;

import java.util.Objects;

/**
 * 发送的消息
 * @author Administrator
 */
public class MessageBean {
    private String objective;
    private String name;
    private String context;
    private MessageTypeEnum type;
    private ChannelId id;
    public MessageBean(){

    }
    public MessageBean(MessageBean messageBean){
        this.objective=messageBean.getObjective();
        this.name=messageBean.getName();
        this.context=messageBean.getContext();
        this.type=messageBean.getType();
        this.id=messageBean.getId();
    }
    public ChannelId getId() {
        return id;
    }

    public void setId(ChannelId id) {
        this.id = id;
    }

    public MessageTypeEnum getType() {
        return type;
    }

    public void setType(MessageTypeEnum type) {
        this.type = type;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "objective='" + objective + '\'' +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageBean)) return false;
        MessageBean that = (MessageBean) o;
        System.out.println("对比");
        return                 Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjective(), getName(), getContext(), getType(), getId());
    }
}
