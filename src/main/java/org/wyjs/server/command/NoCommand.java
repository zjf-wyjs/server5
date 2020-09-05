package org.wyjs.server.command;

/**
 * 空执行，用于初始化每个按钮，当调用空命令时，什么也不用做
 * 可以省掉对空判断
 * @author Administrator
 */
public class NoCommand implements Command{
    @Override
    public StringBuilder execute() {
        return null;
    }

}
