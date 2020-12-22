package com.ceking.service.impl;

import com.ceking.dao.BookDao;
import com.ceking.dao.impl.BookDaoImpl;
import com.ceking.entity.Book;
import com.ceking.entity.Page;
import com.ceking.service.BookService;

import java.math.BigDecimal;
import java.util.List;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 15:40
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> queryPageList(int pageIndex, int pageSize) {
        Page<Book> page = new Page<>();

        int totalCount = bookDao.queryTotalCount();
        page.setTotalCount(totalCount);
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPage += 1;
        }
        page.setTotalPage(totalPage);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        int begin = (page.getPageIndex() - 1) * pageSize;
        List<Book> books = bookDao.queryPageList(begin, pageSize);
        page.setData(books);
        return page;
    }

    public Page<Book> queryPageList(int pageIndex, int pageSize, Double minPrice, Double maxPrice) {
        Page<Book> page = new Page<>();
        int totalCount = bookDao.queryTotalCount(minPrice,maxPrice);
        page.setTotalCount(totalCount);
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPage += 1;
        }
        page.setTotalPage(totalPage);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        int begin = (page.getPageIndex() - 1) * pageSize;
        List<Book> books = bookDao.queryPageList(begin, pageSize,minPrice,maxPrice);
        page.setData(books);
        return page;
    }

}
