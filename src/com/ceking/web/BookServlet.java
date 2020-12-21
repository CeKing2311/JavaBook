package com.ceking.web;

import com.ceking.entity.Book;
import com.ceking.service.BookService;
import com.ceking.service.impl.BookServiceImpl;
import com.ceking.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 15:45
 */
public class BookServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    /**
     * 修改获取图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void bookInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = service.queryBookById(WebUtils.parseInt(id, 0));
        req.setAttribute("bookInfo",book);
        req.setAttribute("method", "update");
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        int count = service.addBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=queryList");
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int count = service.deleteBookById(WebUtils.parseInt(id, 0));
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=queryList");
    }

    /**
     * 编辑图片保存
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        int count = service.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=queryList");
    }

    /**
     * 查询全部数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = service.queryBooks();
        req.setAttribute("bookList", bookList);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

}
