package com.ceking.web;

import com.ceking.entity.User;
import com.ceking.service.UserService;
import com.ceking.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 9:35
 */
public class RegisterServlet extends HttpServlet {
    private UserService userService =new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getCharacterEncoding());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if ("abcde".equalsIgnoreCase(code)){
           if ( userService.existUsername(username)){
               System.out.println("用户名已存在，不可用！");
               req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);

           }else {
               User user =new User(0,username,password,email);
                userService.registerUser(user);
               System.out.println("注册成功！");
               req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
           }

        }else {
            System.out.println("验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);

        }
    }
}