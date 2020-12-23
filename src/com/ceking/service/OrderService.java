package com.ceking.service;

import com.ceking.entity.Cart;
import com.ceking.entity.Order;
import com.ceking.entity.Page;

import java.util.List;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 20:55
 */
public interface OrderService {
    public String creatOrder(Cart cart,int userId);
    List<Order> getOrder(int id);
    Page<Order> getAllOrder(int pageIndex,int pageSize);
    int sendOrder(String orderId);
    int receiveOrder(String orderId);
}
