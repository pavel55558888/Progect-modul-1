package com.example.progect.marketplace.dto;

import com.example.progect.marketplace.entity.MarketEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Schema(name = "Объект списка товаров", description = "Лист с товароми пользователя")
public class ListBasketDto {
    private List<MarketEntity> market = new ArrayList<>();

    public void setMarket(MarketEntity marketObj){
        market.add(marketObj);
    }

    public List<MarketEntity> getMarket() {
        return market;
    }
}
