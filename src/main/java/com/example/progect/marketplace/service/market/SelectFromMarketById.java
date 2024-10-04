package com.example.progect.marketplace.service.market;

import com.example.progect.marketplace.entity.MarketEntity;

import java.util.List;

public interface SelectFromMarketById {
    public List<MarketEntity> selectById(long id);
}
