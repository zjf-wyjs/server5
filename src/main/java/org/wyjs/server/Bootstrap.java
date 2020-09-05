package org.wyjs.server;

import org.wyjs.server.netty.NettyServer;

/**
 * @author Administrator
 */
public class Bootstrap {
    public static void main(String[] args) {
        NettyServer.start("192.168.0.33",1234);
    }
}
