package com.ceking.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 *@author ceking
 *@description
 *@date 2020-12-22 15:15
 */
public class Cart {

    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     *
     * @param item
     */
    public void addItem(CartItem item) {
        CartItem cartItem = items.get(item.getId());
        if (cartItem != null) {
            cartItem.setCount(cartItem.getCount() + 1);
            cartItem.setTotalPrice((double) Math.round(cartItem.getPrice() * cartItem.getCount()));
        } else {
            items.put(item.getId(), item);
        }
    }

    /**
     * 删除商品项
     *
     * @param id
     */
    public void deleteItem(int id) {
        items.remove(id);
    }

    /**
     * 清空商品
     */
    public void clear() {
        this.items.clear();
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(int id, int count) {
        CartItem cartItem = this.items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice((double) Math.round(cartItem.getPrice() * cartItem.getCount()));
        }
    }

    public int getTotalCount() {
        int totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : this.items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for (Map.Entry<Integer, CartItem> entry : this.items.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
