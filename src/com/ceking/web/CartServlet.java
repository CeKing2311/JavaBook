package com.ceking.web;

import com.ceking.entity.Book;
import com.ceking.entity.Cart;
import com.ceking.entity.CartItem;
import com.ceking.entity.WebResult;
import com.ceking.service.BookService;
import com.ceking.service.impl.BookServiceImpl;
import com.ceking.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 *@author ceking
 *@description
 *@date 2020-12-22 15:11
 */
public class CartServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cartInfo");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cartInfo", cart);
        }
        String id = req.getParameter("Id");
        Book book = service.queryBookById(WebUtils.parseInt(id, 0));
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), 1 * book.getPrice());
        cart.addItem(item);
        session.setAttribute("lastName",book.getName());
        //重定向回发送请求的页面
        WebResult result =new WebResult(true, "添加成功", 200, null);
        Map<String ,Object> resMap= new HashMap<>();
        resMap.put("success", true);
        resMap.put("msg", "添加成功");
        resMap.put("totalCount",cart.getTotalCount());
        resMap.put("lastName", book.getName());

        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(resMap));
        //req.getHeader("Referer") 请求头中请求页面的地址
//        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cartInfo");
        String id = req.getParameter("Id");
        if (cart != null) {
            cart.deleteItem(WebUtils.parseInt(id, 0));
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cartInfo");
        if(cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改购物车商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cartInfo");
        String id = req.getParameter("Id");
        String count= req.getParameter("Count");
        if (cart!=null){
            cart.updateCount(WebUtils.parseInt(id, 0),WebUtils.parseInt(count, 0));
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
