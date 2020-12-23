package com.ceking.web;

import com.ceking.entity.*;
import com.ceking.service.OrderService;
import com.ceking.service.impl.OrderServiceImpl;
import com.ceking.utils.JDBCUtils;
import com.ceking.utils.WebUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 21:12
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService =new OrderServiceImpl();


    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cartInfo");
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser!=null){
            String orderId =orderService.creatOrder(cart, loginUser.getId());
            req.getSession().setAttribute("orderId",orderId);
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }else {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 获取用户订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser!=null){
            List<Order> orderList = orderService.getOrder(loginUser.getId());
            req.setAttribute("orderList",orderList);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 获取所有订单数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllOrderPageList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        int pageIndex = WebUtils.parseInt(req.getParameter("pageIndex"), 0);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        if (loginUser!=null){
            Page<Order> page = orderService.getAllOrder(pageIndex,pageSize);
            page.setUrl("orderServlet?action=getAllOrderPageList");
            req.setAttribute("page",page);
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 订单发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId= req.getParameter("orderId");
        int count = orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
