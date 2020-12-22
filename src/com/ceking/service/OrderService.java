package com.ceking.service;

import com.ceking.entity.Cart;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 20:55
 */
public interface OrderService {
    public String creatOrder(Cart cart,int userId);
}
