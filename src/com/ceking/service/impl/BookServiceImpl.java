package com.ceking.service.impl;

import com.ceking.dao.BookDao;
import com.ceking.dao.impl.BookDaoImpl;
import com.ceking.entity.Book;
import com.ceking.service.BookService;

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
}
