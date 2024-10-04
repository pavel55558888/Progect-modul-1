package com.example.progect.marketplace.dao.orders.impl;

import com.example.progect.marketplace.dao.orders.OrdersDao;
import com.example.progect.marketplace.entity.OrdersEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public void insertOrders(OrdersEntity ordersEntity) {
        entityManager.persist(ordersEntity);
    }

    @Override
    public List<OrdersEntity> getOrdersByUsername(String username) {
        return entityManager.createQuery("from OrdersEntity where username = :param1").setParameter("param1", username).getResultList();
    }
}
