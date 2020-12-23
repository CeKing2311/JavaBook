package com.ceking.utils;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ceking
 * @desc
 * @date 2020/12/19 23:12
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回去数据库连接池中的连接
     *
     * @return
     */
    public static Connection getConnection() {
//        Connection conn =null;
//        try {
//            conn = dataSource.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return conn;
        Connection conn = conns.get();
        if (conn == null) {
            try {
                //从数据库连接池中获取
                conn = dataSource.getConnection();
                //保存到ThreadLocal 中,供后面的JDBC操作使用
                conns.set(conn);
                //设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并且关闭连接
     */
    public static  void  commitAndClose(){
        Connection conn = conns.get();
        if (conn!=null){
            try {
                conn.commit(); //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //执行remove操作，Tomcat服务器底层使用了线程池技术
        conns.remove();
    }

    /**
     * 回滚事务并关闭连接
     */
    public static  void  rollBackAndClose(){
        Connection conn = conns.get();
        if (conn!=null){
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //执行remove操作，Tomcat服务器底层使用了线程池技术
        conns.remove();

    }

    /**
     * 关闭连接
     *
     * @param conn
     */
//    public static void closeConnection(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }



}
