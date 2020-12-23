package com.ceking.dao;

import com.ceking.entity.Order;

import java.util.List;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 20:50
 */
public interface OrderDao {
    public  int saveOrder(Order order);
    List<Order> getOrderList(int userId);
    List<Order> getAllOrderList(int begin,int pageSize);
    int queryTotalCount();
    int sendOrder(String orderId);
    int receiveOrder(String orderId);
}
