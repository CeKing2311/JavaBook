package com.ceking.dao;

import com.ceking.entity.User;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 0:19
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User queryUserByUserName(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User getUserByUserNameAndPassword(String username,String password);
}
