package com.ceking.web;

import com.ceking.entity.User;
import com.ceking.service.UserService;
import com.ceking.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ceking
 * @desc
 * @date 2020/12/20 10:17
 */
public class LoginServlet extends HttpServlet {
    private UserService userService =new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(new User(0, username, password, null));
        if (user!=null){
            System.out.println("登录成功！");
            request.getRequestDispatcher("/pages/user/login_success.html").forward(request,response);
        }else {
            System.out.println("登录失败!");
            request.getRequestDispatcher("/pages/user/login.html").forward(request,response);
            StringBuffer url = request.getRequestURL();
            System.out.println(url);
        }
    }
}
