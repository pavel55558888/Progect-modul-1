package com.example.progect.marketplace.dto;

import com.example.progect.marketplace.entity.MarketEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Schema(name = "Объект заказов", description = "Оформленный заказ пользователя")
public class ListOrdersDto {
    @Schema(name = "Номер заказа")
    private String name;
    @Schema(name = "Статус заказа", description = "Создан/Отправлен/Выполнен")
    private String status;
    @Schema(name = "Лист объектов", description = "Товары, которые были в заказе")
    private List<MarketEntity> marketEntityList;
    @Schema(name = "Цена", description = "Итоговая цена все товаров")
    private int price;


    public ListOrdersDto(String name, String status, List<MarketEntity> marketEntityList, int price) {
        this.name = name;
        this.status = status;
        this.marketEntityList = marketEntityList;
        this.price = price;
    }

}
