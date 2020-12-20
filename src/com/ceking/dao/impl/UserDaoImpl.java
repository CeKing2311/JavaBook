package com.ceking.dao.impl;

import com.ceking.dao.UserDao;
import com.ceking.entity.User;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 0:23
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUserName(String username) {
        String  sql ="select id,username,password,email from t_user where username = ?";
        return queryForObject(User.class, sql, username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User getUserByUserNameAndPassword(String username, String password) {
        String  sql ="select id,username,password,email from t_user where username = ? and password = ?";
        return queryForObject(User.class,sql,username,password);
    }
}
