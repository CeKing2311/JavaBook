package com.ceking.service;

import com.ceking.entity.Book;
import com.ceking.entity.Page;

import java.math.BigDecimal;
import java.util.List;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 15:40
 */
public interface BookService {
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();
    public Page<Book> queryPageList(int pageIndex,int pageSize);
    public Page<Book> queryPageList(int pageIndex, int pageSize, Double minPrice, Double maxPrice);
}
