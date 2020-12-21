package com.ceking.dao.impl;

import com.ceking.dao.BookDao;
import com.ceking.entity.Book;

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
        String sql ="select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` from t_book where id =?";
        return queryForObject(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql ="select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` from t_book ";
        return queryForList(Book.class, sql );
    }
}
