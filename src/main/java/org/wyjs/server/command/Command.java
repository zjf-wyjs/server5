package org.wyjs.server.command;

/**
 * 创建执行命令的接口
 * @author Administrator
 */
public interface Command {
    /**
     * 执行命令
     */
    StringBuilder execute();
}
