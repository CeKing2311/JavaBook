package com.ceking.dao.impl;

import com.ceking.dao.OrderDao;
import com.ceking.entity.Order;
import com.sun.org.apache.regexp.internal.REUtil;

import java.util.List;

/**
 * @author ceking
 * @desc
 * @date 2020/12/22 20:51
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        int count = update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        return count;
    }

    public List<Order> getOrderList(int userId) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order where user_id=?";
        List<Order> orders = queryForList(Order.class, sql, userId);
        return orders;
    }

    @Override
    public List<Order> getAllOrderList(int begin, int pageSize) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order limit ? , ?";
        List<Order> orders = queryForList(Order.class, sql, begin, pageSize);
        return orders;
    }

    @Override
    public int queryTotalCount() {
        String sql = "select count(*) from t_order";
        Number num = (Number) queryForObjectValue(sql);
        return num.intValue();
    }

    @Override
    public int sendOrder(String orderId) {
        String sql = " update t_order set status = 1 where order_id = ?";
        return update(sql, orderId);
    }

    @Override
    public int receiveOrder(String orderId) {
        String sql = " update t_order set status = 2 where order_id = ?";
       return update(sql, orderId);
    }

}
