package com.ceking.dao.impl;

import com.ceking.dao.OrderItemDao;
import com.ceking.entity.OrderItem;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 20:56
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        int count = update(sql,item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
        return count;
    }
}
