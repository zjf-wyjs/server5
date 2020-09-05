package org.wyjs.server.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.wyjs.server.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Administrator
 */
public abstract class BaseDao {
    /**
     * 使用dbutil
     */
    private QueryRunner queryRunner=new QueryRunner();

    /**
     * 执行查询一条数据
     * @param type bean
     * @param sql 执行的sql语句
     * @param args 可变参数
     * @param <T> 类型
     * @return 啊
     */
    public<T> T queryForOne(Class<T> type,String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }

        return null;
    }
}
