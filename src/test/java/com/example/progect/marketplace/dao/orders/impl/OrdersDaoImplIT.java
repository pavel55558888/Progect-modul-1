package com.example.progect.marketplace.dao.orders.impl;

import com.example.progect.marketplace.entity.MarketEntity;
import com.example.progect.marketplace.entity.OrdersEntity;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class OrdersDaoImplIT {
    @Mock
    EntityManager entityManager;
    @InjectMocks
    OrdersDaoImpl ordersDao;

    @Test
    void insertOrders(){
        OrdersEntity ordersEntity = new OrdersEntity("3;3;",500,"Создан","username");

        ordersDao.insertOrders(ordersEntity);

        Mockito.verify(entityManager).persist(ordersEntity);
    }

    @Test
    void getOrdersByUsername(){
        OrdersEntity ordersEntity = new OrdersEntity("3;3;",500,"Создан","username");
        ordersEntity.setId(1);

        Query queryMock = mock(Query.class);
        when(entityManager.createQuery("from OrdersEntity where username = :param1")).thenReturn(queryMock);
        when(queryMock.setParameter("param1", "username")).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(List.of(ordersEntity));

        ordersDao.insertOrders(ordersEntity);

        List<OrdersEntity> ordersEntities = ordersDao.getOrdersByUsername("username");

        assertFalse(ordersEntities.isEmpty());
        assertEquals(1, ordersEntities.size());
        assertEquals(1, ordersEntities.get(0).getId());
        assertEquals("3;3;",ordersEntities.get(0).getIdMarket());
    }

}