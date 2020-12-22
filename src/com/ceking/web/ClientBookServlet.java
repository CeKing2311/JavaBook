package com.ceking.web;

import com.ceking.entity.Book;
import com.ceking.entity.Page;
import com.ceking.service.BookService;
import com.ceking.service.impl.BookServiceImpl;
import com.ceking.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.IOException;

/*
 *@author ceking
 *@description
 *@date 2020-12-22 9:26
 */
public class ClientBookServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    /**
     * 分页获取数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryPageList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex = WebUtils.parseInt(req.getParameter("pageIndex"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = service.queryPageList(pageIndex, pageSize);
        page.setUrl("client/bookServlet?action=queryPageList");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void queryPageListByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex = WebUtils.parseInt(req.getParameter("pageIndex"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Double minPrice = WebUtils.parseDouble(req.getParameter("min"), 0);
        Double maxPrice = WebUtils.parseDouble(req.getParameter("max"), Double.MAX_VALUE);
        Page<Book> page = service.queryPageList(pageIndex, pageSize, minPrice, maxPrice);
        StringBuilder str = new StringBuilder("client/bookServlet?action=queryPageListByPrice");
        if (req.getParameter("min") != null) {
            str.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            str.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(str.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
