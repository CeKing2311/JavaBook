package com.ceking.web;

import com.ceking.entity.Cart;
import com.ceking.entity.User;
import com.ceking.service.OrderService;
import com.ceking.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            String orderId = orderService.creatOrder(cart, loginUser.getId());
            req.getSession().setAttribute("orderId",orderId);
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

        }else {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
}
