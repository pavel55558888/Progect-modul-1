package com.example.progect.marketplace.dao.orders;

import com.example.progect.marketplace.entity.OrdersEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdersDao {
    @Transactional
    public void insertOrders(OrdersEntity ordersEntity);
    public List<OrdersEntity> getOrdersByUsername(String username);
}
