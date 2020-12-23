package com.ceking.web;

import com.ceking.entity.User;
import com.ceking.service.UserService;
import com.ceking.service.impl.UserServiceImpl;
import com.ceking.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 11:50
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 登录业务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(new User(0, username, password, null));
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 注册业务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //删除Session中的验证码
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());
        if (token.equalsIgnoreCase(code)) {
            if (userService.existUsername(user.getUsername())) {
                req.setAttribute("msg", "用户名已存在，不可用！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registerUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 退出
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        //重定向到工程目录
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 判断用户名是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void existUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        boolean isExist = userService.existUsername(userName);
        Map<String,Object> result= new HashMap<>();
        result.put("isExist", isExist);
        Gson gson =new Gson();
        String json = gson.toJson(result);
        resp.getWriter().write(json);
    }
}
