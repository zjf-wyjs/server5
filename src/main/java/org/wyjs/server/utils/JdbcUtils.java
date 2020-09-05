package org.wyjs.server.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author Administrator
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;

    static {
        try{
            Properties properties = new Properties();
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection=null;

        try {
            connection=dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭连接
     * @param connection
     */
    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
