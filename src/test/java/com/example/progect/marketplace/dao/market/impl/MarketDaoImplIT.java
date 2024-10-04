package com.example.progect.marketplace.dao.market.impl;

import com.example.progect.marketplace.entity.MarketEntity;
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
class MarketDaoImplIT {
    @Mock
    EntityManager entityManager;
    @InjectMocks
    MarketDaoImpl marketDao;
    @Test
    void insertMarket_saveObjMarket(){
        MarketEntity market = new MarketEntity("name", 3,"description", 3);

        marketDao.insertMarket(market);

        Mockito.verify(entityManager).persist(market);
    }

    @Test
    void selectById(){
        MarketEntity market = new MarketEntity("name", 3,"description", 3);
        market.setId(1);

        Query queryMock = mock(Query.class);
        when(entityManager.createQuery("from MarketEntity where id = :param1")).thenReturn(queryMock);
        when(queryMock.setParameter("param1", 1L)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(List.of(market));

        marketDao.insertMarket(market);

        List<MarketEntity> listMarketEntity = marketDao.selectById(1);

        assertFalse(listMarketEntity.isEmpty());
        assertEquals(1, listMarketEntity.size());
        assertEquals(1, listMarketEntity.get(0).getId());

    }

    @Test
    void getMarket(){
        MarketEntity market = new MarketEntity("name", 3,"description", 3);
        market.setId(1);


        Query queryMock = mock(Query.class);
        when(entityManager.createQuery("from MarketEntity")).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(List.of(market));

        marketDao.insertMarket(market);




        List<MarketEntity> listMarketEntity = marketDao.getMarket();

        assertFalse(listMarketEntity.isEmpty());
        assertEquals(1, listMarketEntity.size());
        assertEquals(1, listMarketEntity.get(0).getId());


    }
}