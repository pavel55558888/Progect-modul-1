package com.example.progect.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Объект DTO \"Товары\"")
public class MarketDto {
    @Schema(name = "id товара")
    private long id;
    @Schema(name = "Название")
    @NotBlank(message = "Заполните название")
    private String name;
    @Schema(name = "Цена")
    @Min(value = 1, message = "Цена не может быть менее 1")
    private int price;
    @Schema(name = "Описание")
    private String description;
    @Schema(name = "Количество")
    @Min(value = 1, message = "Вы не можете добавить фантомные товары")
    private int quantity;
    @Schema(name = "В наличие?")
    private boolean inStock;

    public MarketDto(int id, String name, int price, String description, int quantity, boolean inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.inStock = inStock;
    }
}
