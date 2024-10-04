package com.example.progect.marketplace.controller;

import com.example.progect.marketplace.dto.ListBasketDto;
import com.example.progect.marketplace.dto.DtoError;
import com.example.progect.marketplace.dto.ListOrdersDto;
import com.example.progect.marketplace.entity.MarketEntity;
import com.example.progect.marketplace.entity.OrdersEntity;
import com.example.progect.marketplace.enums.StatusOrdersEnum;
import com.example.progect.marketplace.kafka.KafkaProducer;
import com.example.progect.marketplace.service.market.SelectFromMarketById;
import com.example.progect.marketplace.service.orders.InsertFromOrders;
import com.example.progect.marketplace.service.orders.SelectFromOrdersByUsername;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class OrdersRestController {
    @Autowired
    private InsertFromOrders insertFromOrders;
    @Autowired
    private SelectFromMarketById selectFromMarketById;
    @Autowired
    private SelectFromOrdersByUsername selectFromOrdersByUsername;
    @Autowired
    private KafkaProducer kafkaProducer;
    private DtoError dtoError = new DtoError();
    private ListBasketDto listBasketDto = new ListBasketDto();
    private String idMarket = "";
    private int price = 0;

    @Schema(name = "Новый заказ", description = "Создать заказ исходя из добавленных товаров пользователя в корзину")
    @PostMapping("/orders")
    public ResponseEntity<?> newOrder(HttpSession session){
        listBasketDto = (ListBasketDto) session.getAttribute("basket");
        if (listBasketDto == null) {
            dtoError.setError("Корзина пуста!");
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(dtoError);
        }
        for (MarketEntity obj : listBasketDto.getMarket()){
            idMarket += obj.getId() + ";";
            price += obj.getPrice();
            MarketEntity marketEntity = selectFromMarketById.selectById(obj.getId()).getFirst();
            if (marketEntity.getQuantity() <= 0){
                dtoError.setError("В заказе есть товары, которых нет в наличии!");
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(dtoError);
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        OrdersEntity ordersEntity = new OrdersEntity(idMarket, price, StatusOrdersEnum.Created.getValue(), username);
        insertFromOrders.insertOrders(ordersEntity);
        session.removeAttribute("basket");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/market");

        kafkaProducer.sendMessage(String.valueOf(ordersEntity.getId()));

        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    @Schema(name = "Список товаров в корзине", description = "Все товары пользователя")
    @GetMapping("/orders")
    public ResponseEntity<List<ListOrdersDto>> getOrders(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<OrdersEntity> ordersEntityList = selectFromOrdersByUsername.getOrdersByUsername(username);
        log.info("Выполнен selectFromOrdersByUsername, результат:   " + ordersEntityList);
        List<ListOrdersDto> ordersDtoList = new ArrayList<>();
        for (OrdersEntity obj : ordersEntityList) {
            String[] numbersArray = obj.getIdMarket().split(";");
            List<MarketEntity> marketEntityList = new ArrayList<>();
            for (String str : numbersArray){
                marketEntityList.add(selectFromMarketById.selectById(Long.parseLong(str)).getFirst());
            }
            ordersDtoList.add(new ListOrdersDto("Заказ №" + obj.getId(), "Статус заказа: " + obj.getStatus(), marketEntityList, obj.getPrice()));
            log.info("Новый объект OrdersDto:   " + ordersDtoList.getLast());
        }
        log.info("Объект \"ordersDtoList\":   " + ordersDtoList);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ordersDtoList);
    }
}
