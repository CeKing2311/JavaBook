package com.ceking.dao.impl;

import com.ceking.dao.BookDao;
import com.ceking.entity.Book;
import com.ceking.entity.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 15:13
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) VALUES(?,?,?,?,?,?)";
       return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql ="delete from t_book where id = ? ";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ? , `author` = ? , `price` = ? , `sales` = ? , `stock` =? , `img_path`=? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql ="select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id =?";
        return queryForObject(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql ="select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book ";
        return queryForList(Book.class, sql );
    }

    @Override
    public List<Book> queryPageList(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ? , ? ";
        return  queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public int queryTotalCount() {
        String sql ="select count(*) from t_book ";
        Number num= (Number) queryForObjectValue(sql);
        return  num.intValue();
    }

    @Override
    public List<Book> queryPageList(int begin, int pageSize, Double minPrice, Double maxPrice) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where price between ? and ? order by price limit ? , ? ";
        return  queryForList(Book.class,sql,minPrice,maxPrice,begin,pageSize);
    }

    @Override
    public int queryTotalCount(Double minPrice, Double maxPrice) {
        String sql ="select count(*) from t_book where price between ? and ? ";
        Number num= (Number) queryForObjectValue(sql,minPrice,maxPrice);
        return  num.intValue();
    }

}
