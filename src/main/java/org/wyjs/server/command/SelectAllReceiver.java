package org.wyjs.server.command;

import org.wyjs.server.manager.ClientManager;

import java.util.Iterator;

/**
 * 查询所有在线用户，具体执行者
 * @author Administrator
 */
public class SelectAllReceiver implements Receiver{

    @Override
    public StringBuilder execute() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = ClientManager.clientId.keySet().iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("#");
        }
        return sb;

    }
}
