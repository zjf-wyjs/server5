package org.wyjs.server.service;

import org.wyjs.server.bean.Users;
import org.wyjs.server.dao.UserDao;
import org.wyjs.server.dao.impl.UserDaoImpl;

public class UserService {
    private UserDao userDao;
    private UserService(){
        this.userDao=new UserDaoImpl();
    }
    public static UserService getInstance(){
        return EE.INSTANCE.userService;
    }
    public Users queryUserByUid(String uid) {

        return userDao.queryUserByUid(uid);
    }

    enum EE{
        /**
         * 实例
         */
        INSTANCE;
        UserService userService;
        EE(){
            userService=new UserService();
        }
    }
}
