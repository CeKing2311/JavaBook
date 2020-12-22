package com.ceking.service.impl;

import com.ceking.dao.BookDao;
import com.ceking.dao.OrderDao;
import com.ceking.dao.OrderItemDao;
import com.ceking.dao.impl.BookDaoImpl;
import com.ceking.dao.impl.OrderDaoImpl;
import com.ceking.dao.impl.OrderItemDaoImpl;
import com.ceking.entity.*;
import com.ceking.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 21:02
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao =new OrderDaoImpl();
    private OrderItemDao itemDao =new OrderItemDaoImpl();
    private BookDao bookDao =new BookDaoImpl();
    @Override
    public String creatOrder(Cart cart, int userId) {
        String orderId= System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            CartItem item = entry.getValue();
            OrderItem orderItem =new OrderItem(0,item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),orderId);
            itemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales()+item.getCount());
            book.setStock(book.getStock()-item.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
