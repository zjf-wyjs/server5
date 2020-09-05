package org.wyjs.server.dao;

import org.wyjs.server.bean.Users;

public interface UserDao {
    /**
     * 根据uid查询一个用户信息
     * @param uid
     * @return
     */
    Users queryUserByUid(String uid);
}
