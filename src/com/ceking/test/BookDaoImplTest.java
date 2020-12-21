package com.ceking.test;

import com.ceking.dao.BookDao;
import com.ceking.dao.impl.BookDaoImpl;
import com.ceking.entity.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 15:24
 */
public class BookDaoImplTest {

    private BookDao bookDao =new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(0,"Java从入门到放弃",new BigDecimal(8888),"C大大",0,100,"/static/img/default.jpg"));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }
}