package com.example.progect.marketplace.dao.market;

import com.example.progect.marketplace.entity.MarketEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarketDao {
    public void insertMarket(MarketEntity obj);
    public List<MarketEntity> getMarket();
    public List<MarketEntity> selectById(long id);
}
