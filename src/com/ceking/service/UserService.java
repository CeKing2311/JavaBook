package com.ceking.service;

import com.ceking.entity.User;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 0:45
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public  void  registerUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public  User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    public  boolean existUsername(String username);
}
