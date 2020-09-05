package org.wyjs.server.constant;

/**
 * 管理命令的常量类
 * @author Administrator
 */
public class Older {
    /**
     * 系统级命令
     */
    public final static class SystemOlder{
        public final static String CALC="calc";

    }
    /**
     * 自定义的命令
     */
    public final static class MyOlder{
        //查看在线的客户端
        public final static String SERVER_LIST="server_list";
        //离开一个用户
        public final static String OFFLINE="offline";
        //上线一个用户
        public final static String ONLINE="online";
    }
    /**
     * 选择命令
     */
    public final static class SelectOlder{
        public final static String C="c";
        public final static String M="m";
    }
}
