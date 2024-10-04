package com.example.progect.marketplace.service.market.impl;

import com.example.progect.marketplace.dao.market.MarketDao;
import com.example.progect.marketplace.dto.MarketDto;
import com.example.progect.marketplace.entity.MarketEntity;

import com.example.progect.marketplace.service.market.InsertFromMarket;
import com.example.progect.marketplace.service.market.SelectFromMarketAll;
import com.example.progect.marketplace.service.market.SelectFromMarketById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements SelectFromMarketAll, InsertFromMarket, SelectFromMarketById {
    @Autowired
    MarketDao marketDao;

    @Override
    public List<MarketEntity> getMarket() {
        return marketDao.getMarket();
    }


    @Override
    public void insertMarket(MarketEntity obj) {
        marketDao.insertMarket(obj);
    }

    @Override
    public List<MarketEntity> selectById(long id) {
        return marketDao.selectById(id);
    }
}
