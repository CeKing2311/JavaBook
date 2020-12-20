package com.ceking.service.impl;

import com.ceking.dao.UserDao;
import com.ceking.dao.impl.UserDaoImpl;
import com.ceking.entity.User;
import com.ceking.service.UserService;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 0:48
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        User userInfo = userDao.getUserByUserNameAndPassword(user.getUsername(), user.getPassword());
        if (userInfo != user) {
            return userInfo;
        }
        return null;
    }

    @Override
    public boolean existUsername(String username) {
        User user = userDao.queryUserByUserName(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
