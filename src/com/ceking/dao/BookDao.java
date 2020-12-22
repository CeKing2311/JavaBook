package com.ceking.dao;

import com.ceking.entity.Book;
import com.ceking.entity.Page;

import java.math.BigDecimal;
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
    public List<Book> queryPageList(int begin, int pageSize);
    public int queryTotalCount();
    public List<Book> queryPageList(int begin, int pageSize,Double minPrice,Double maxPrice);
    public int queryTotalCount(Double minPrice, Double maxPrice);
}
