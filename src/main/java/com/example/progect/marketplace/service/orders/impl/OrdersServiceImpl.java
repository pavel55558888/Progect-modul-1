package com.example.progect.marketplace.service.orders.impl;

import com.example.progect.marketplace.dao.orders.OrdersDao;
import com.example.progect.marketplace.entity.OrdersEntity;
import com.example.progect.marketplace.service.orders.InsertFromOrders;
import com.example.progect.marketplace.service.orders.SelectFromOrdersByUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements InsertFromOrders, SelectFromOrdersByUsername {
    @Autowired
    OrdersDao ordersDao;
    @Override
    public void insertOrders(OrdersEntity ordersEntity) {
        ordersDao.insertOrders(ordersEntity);
    }

    @Override
    public List<OrdersEntity> getOrdersByUsername(String username) {
        return ordersDao.getOrdersByUsername(username);
    }
}
