package com.example.progect.marketplace.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "market")
@ToString
@Schema(name = "Объект таблицы базы данных \"Товары\"")
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(name = "id товара")
    private long id;
    @Column(name = "name")
    @Schema(name = "Название")
    @NotBlank(message = "Заполните название")
    private String name;
    @Column(name = "price")
    @Schema(name = "Цена")
    @Min(value = 1, message = "Цена не может быть менее 1")
    private int price;
    @Column(name = "description")
    @Schema(name = "Описание")
    private String description;
    @Column(name = "quantity")
    @Schema(name = "Количество")
    @Min(value = 1, message = "Вы не можете добавить фантомные товары")
    private int quantity;
    @Column(name = "in_stock")
    @Schema(name = "В наличие?")
    private boolean inStock;

    public MarketEntity(String name, int price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.inStock = true;
    }
}
