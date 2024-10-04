package com.example.progect.marketplace.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(name = "Объект таблицы базы данных \"Заказы\"")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(name = "id заказа")
    private long id;
    @Column(name = "idMarket")
    @Schema(name = "id товаров", description = "id товаров в формате \"1;2;3;4;\"")
    private String idMarket;
    @Column(name = "price")
    @Schema(name = "Итоговая цена заказа")
    private int price;
    @Column(name = "status")
    @Schema(name = "Статус заказа", description = "Создан/Отправлен/Выполнен")
    private String status;
    @Column(name = "username")
    @Schema(name = "Логин пользователя")
    private String username;

    public OrdersEntity(String idMarket, int price, String status, String username) {
        this.idMarket = idMarket;
        this.price = price;
        this.status = status;
        this.username = username;
    }
}
