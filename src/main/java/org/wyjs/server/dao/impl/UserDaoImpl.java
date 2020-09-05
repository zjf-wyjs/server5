package org.wyjs.server.dao.impl;

import org.wyjs.server.bean.Users;
import org.wyjs.server.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public Users queryUserByUid(String uid) {
        String sql="SELECT *FROM users u,account a WHERE u.aid=a.aid AND u.uid=?";
        return queryForOne(Users.class,sql,uid);
    }
}
