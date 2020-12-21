package com.ceking.dao;

import com.ceking.entity.Book;

import java.util.List;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 15:11
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();
}
