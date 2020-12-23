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
import java.util.List;
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

    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    @Override
    public String creatOrder(Cart cart, int userId) {
        String orderId= System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            CartItem item = entry.getValue();
            OrderItem orderItem =new OrderItem(0,item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),orderId);
            //保存订单详情
            itemDao.saveOrderItem(orderItem);
            //扣减商品库存
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales()+item.getCount());
            book.setStock(book.getStock()-item.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> getOrder(int userId) {
        return  orderDao.getOrderList(userId);
    }

    @Override
    public Page<Order> getAllOrder(int pageIndex, int pageSize) {
        Page<Order> page = new Page<>();
        int totalCount = orderDao.queryTotalCount();
        page.setTotalCount(totalCount);
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPage += 1;
        }
        page.setTotalPage(totalPage);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        int begin = (page.getPageIndex() - 1) * pageSize;
        List<Order> orderList = orderDao.getAllOrderList(begin, pageSize);
        page.setData(orderList);
        return page;
    }

    @Override
    public int sendOrder(String orderId) {
       return orderDao.sendOrder(orderId);
    }

    @Override
    public int receiveOrder(String orderId) {
       return  orderDao.receiveOrder(orderId);
    }
}
