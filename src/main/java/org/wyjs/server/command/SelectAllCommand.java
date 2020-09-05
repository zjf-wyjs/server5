package org.wyjs.server.command;

/**
 * 查询所有在线用户命令着
 * @author Administrator
 */
public class SelectAllCommand implements Command{
    private SelectAllReceiver selectAllReceiver;

    public SelectAllCommand(SelectAllReceiver selectAllReceiver) {
        this.selectAllReceiver = selectAllReceiver;
    }

    @Override
    public StringBuilder execute() {
        return selectAllReceiver.execute();
    }
}
