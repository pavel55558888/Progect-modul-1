package com.example.progect.marketplace.dao.market.impl;

import com.example.progect.marketplace.dao.market.MarketDao;
import com.example.progect.marketplace.dto.MarketDto;
import com.example.progect.marketplace.entity.MarketEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarketDaoImpl implements MarketDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<MarketEntity> getMarket() {
        return entityManager.createQuery("from MarketEntity").getResultList();
    }

    @Override
    public List<MarketEntity> selectById(long id) {
        return entityManager.createQuery("from MarketEntity where id = :param1").setParameter("param1", id).getResultList();
    }

    @Override
    public void insertMarket(MarketEntity obj) {
        entityManager.persist(obj);
    }
}
