package com.ceking.test;

import com.ceking.dao.UserDao;
import com.ceking.dao.impl.UserDaoImpl;
import com.ceking.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 0:34
 */
public class UserDaoTest {
    private  UserDao userDao =new UserDaoImpl();
    @Test
    public void queryUserByUserName() {

        User user = userDao.queryUserByUserName("admin");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(0, "cjq", "123456", "cjq@qq.com")));
    }

    @Test
    public void getUserByUserNameAndPassword() {
        System.out.println(userDao.getUserByUserNameAndPassword("cjq", "123456"));
    }
}