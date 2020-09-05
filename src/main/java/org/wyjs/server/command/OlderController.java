package org.wyjs.server.command;

import jdk.nashorn.internal.ir.CallNode;

/**
 * 命令控制器
 *
 * @author Administrator
 */
public class OlderController {
    /**
     * 命令数组
     */
    Command[] commands;

    public OlderController() {
        //创建5个命令
        this.commands = new Command[5];
        //设置5个命令为空命令,避免空指针异常
        for (int i = 0; i < commands.length; i++) {
            commands[i] = new NoCommand();
        }
    }

    /**
     * @param num     第几个按钮设置命令
     * @param command 要设置的命令
     */
    public void setCommand(int num, Command command) {
        if (num < this.commands.length) {
            commands[num] = command;
        } else {
            System.out.println("设置命令失败：数组越界");
        }
    }

    /**
     * 按下执行命令
     * @param no 命令的位置
     */
    public StringBuilder onButton(int no){
        if (no > this.commands.length) {
            System.out.println("没找到这个命令");
        } else {
            return commands[no].execute();
        }
        return null;
    }
}
