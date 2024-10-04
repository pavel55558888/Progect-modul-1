package com.example.progect.marketplace.service.market;

import com.example.progect.marketplace.dto.MarketDto;
import com.example.progect.marketplace.entity.MarketEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface InsertFromMarket {
    @Transactional
    public void insertMarket(MarketEntity obj);
}
