package com.example.progect.marketplace.controller;

import com.example.progect.marketplace.dto.DtoError;
import com.example.progect.marketplace.dto.ListBasketDto;
import com.example.progect.marketplace.dto.MarketDto;
import com.example.progect.marketplace.entity.MarketEntity;
import com.example.progect.marketplace.kafka.KafkaProducer;
import com.example.progect.marketplace.service.market.InsertFromMarket;
import com.example.progect.marketplace.service.market.SelectFromMarketAll;
import com.example.progect.marketplace.service.market.SelectFromMarketById;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MarketRestController {
    @Autowired
    private SelectFromMarketAll selectFromMarketAll;
    @Autowired
    private InsertFromMarket insertFromMarket;
    @Autowired
    private SelectFromMarketById selectFromMarketById;

    private DtoError error = new DtoError();

    @Schema(name = "Маркет", description = "Полный список товаров")
    @GetMapping("/market")
    public ResponseEntity<List<MarketEntity>> allMarket(){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(selectFromMarketAll.getMarket());
    }

    @Schema(name = "Добавить товар")
    @PostMapping("/market")
    public ResponseEntity<?> setMarket(@Valid @RequestBody MarketEntity market, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
        if (bindingResult.hasErrors()){
            error.setListError(bindingResult.getAllErrors());
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(error);
        }
        insertFromMarket.insertMarket(market);
        return ResponseEntity.created(uriComponentsBuilder
                        .path("/api/market/{id}")
                        .build(List.of("id", market.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(market);
    }

    @Schema(name = "Товар", description = "Получение определенного товара по id")
    @GetMapping("/market/{id}")
    public ResponseEntity<List<MarketEntity>> market(@PathVariable long id){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(selectFromMarketById.selectById(id));
    }
}
