package com.example.progect.marketplace.service.market;

import com.example.progect.marketplace.entity.MarketEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SelectFromMarketAll {
    public List<MarketEntity> getMarket();
}
