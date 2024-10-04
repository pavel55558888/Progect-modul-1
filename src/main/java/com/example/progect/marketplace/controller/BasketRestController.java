package com.example.progect.marketplace.controller;

import com.example.progect.marketplace.dto.ListBasketDto;
import com.example.progect.marketplace.entity.MarketEntity;
import com.example.progect.marketplace.service.market.SelectFromMarketById;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BasketRestController {
    @Autowired
    private SelectFromMarketById selectFromMarketById;
    private ListBasketDto listBasketDto = new ListBasketDto();

    @Schema(name = "Корзина", description = "Предъявить корзину пользователя")
    @GetMapping("/basket")
    public ResponseEntity<List<MarketEntity>> basket(HttpSession session){
        listBasketDto = (ListBasketDto) session.getAttribute("basket");
        if (listBasketDto == null) {
            listBasketDto = new ListBasketDto();
            session.setAttribute("basket", listBasketDto);
        }

        List<MarketEntity> ordersEntities = listBasketDto.getMarket();
        for (MarketEntity obj : ordersEntities){
            MarketEntity marketEntity = selectFromMarketById.selectById(obj.getId()).getFirst();
            if (marketEntity.getQuantity() <= 0){
                obj.setInStock(false);
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listBasketDto.getMarket());
    }

    @Schema(name = "Добавить в корзину", description = "Добавить определенный товар в корзину, по id товара")
    @PostMapping("/basket/{id}")
    public String setBasket(@PathVariable long id, HttpSession session){
        listBasketDto.setMarket(selectFromMarketById.selectById(id).getFirst());
        session.setAttribute("basket", listBasketDto);

        return "redirect:/api/market";
    }
}
