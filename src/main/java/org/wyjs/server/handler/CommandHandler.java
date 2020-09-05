package org.wyjs.server.handler;


import org.wyjs.server.bean.MessageBean;
import org.wyjs.server.command.OlderController;
import org.wyjs.server.command.SelectAllCommand;
import org.wyjs.server.command.SelectAllReceiver;
import org.wyjs.server.constant.Older;
import org.wyjs.server.manager.ClientManager;

/**
 * 命令处理器，单例
 * @author Administrator
 */
public class CommandHandler {
    private static CommandHandler instance=null;
    private OlderController olderController;

    public static CommandHandler getInstance(){
        if(instance==null){
            synchronized (CommandHandler.class){
                if(instance==null){
                    instance=new CommandHandler();
                }
            }
        }
        return instance;
    }
    private CommandHandler(){
        init();
    }

    /**
     * 初始化所有命令
     */
    public void init(){
        //创建一个命令处理
        olderController = new OlderController();

        //创建一个获取用户的命令
        SelectAllReceiver selectAllReceiver = new SelectAllReceiver();
        SelectAllCommand selectAllCommand = new SelectAllCommand(selectAllReceiver);
        //绑定查询用户列表命令
        olderController.setCommand(0,selectAllCommand);


    }

    /**
     * 命令解析器
     * @param str 把字符串解析成命令
     */
    public void parse(String str){
        if(str.contains(Older.MyOlder.SERVER_LIST)) {
            StringBuilder stringBuilder = olderController.onButton(0);
            MessageBean messageBean = new MessageBean();
            messageBean.setName(Older.MyOlder.SERVER_LIST);
            messageBean.setObjective(Older.MyOlder.SERVER_LIST);
            messageBean.setContext(stringBuilder.toString());
            ClientManager.adminGroup.writeAndFlush(messageBean);
        }else{
            System.out.println("没有执行任何命令");
        }
    }
}
