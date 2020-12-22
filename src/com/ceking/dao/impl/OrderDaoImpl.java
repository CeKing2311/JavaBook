package com.ceking.dao.impl;

import com.ceking.dao.OrderDao;
import com.ceking.entity.Order;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 20:51
 */
public class OrderDaoImpl extends  BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        int count = update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        return count;
    }
}
