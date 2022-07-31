package com.thalasoft.learnintouch.data.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

public class OrderList {

    private List<Order> orders;
    
    public OrderList add(Order order) {
        if (orders == null) {
            orders = new ArrayList<Order>();            
        }
        orders.add(order);
        return this;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
}
