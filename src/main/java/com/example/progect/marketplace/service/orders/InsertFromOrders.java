package com.example.progect.marketplace.service.orders;

import com.example.progect.marketplace.entity.OrdersEntity;
import org.springframework.stereotype.Service;

@Service
public interface InsertFromOrders {
    public void insertOrders(OrdersEntity ordersEntity);
}
