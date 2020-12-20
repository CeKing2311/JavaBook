package com.ceking.dao.impl;

import com.ceking.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ceking
 * @desc
 * @date 2020/12/19 23:30
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库

    private QueryRunner queryRunner =new QueryRunner();

    /**
     *
     * @param sql
     * @param args
     * @return 返回-1.执行失败
     */
    public  int update(String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
           JDBCUtils.closeConnection(conn);
        }
        return  -1;
    }

    /**
     *
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return 返回一个对象
     */
    public <T> T queryForObject(Class<T> type,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
           return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return  null;
    }

    /**
     *
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return 返回一个对象集合
     */
    public <T> List<T>  queryForList(Class<T> type, String sql, Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
           return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return  null;
    }

    /**
     * 执行返回一列的sql语句
     * @param sql
     * @param args
     * @return
     */
    public Object queryForObjectValue(String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
           return queryRunner.query(conn, sql, new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return  null;
    }

}
