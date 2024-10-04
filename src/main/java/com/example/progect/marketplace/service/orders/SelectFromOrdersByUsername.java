package com.example.progect.marketplace.service.orders;

import com.example.progect.marketplace.entity.OrdersEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectFromOrdersByUsername {
    public List<OrdersEntity> getOrdersByUsername(String username);
}
