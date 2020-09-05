package org.wyjs.server.command;

/**
 * 命令接受者接口
 * @author Administrator
 */
public interface Receiver {
    /**
     * 执行具体的命令
     * @return 执行后返回结果
     */
    StringBuilder execute();
}
