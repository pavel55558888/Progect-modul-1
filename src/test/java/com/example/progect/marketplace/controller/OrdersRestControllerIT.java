package com.example.progect.marketplace.controller;

import com.example.progect.marketplace.dto.ListBasketDto;
import com.example.progect.marketplace.entity.MarketEntity;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class OrdersRestControllerIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    HttpSession session;

    @Test
    void getOrders_returnOrderDtoList() throws Exception {
        //given
        var requestBuilder = MockMvcRequestBuilders.get("/api/orders");
        //when

        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void newOrder_returnRedirectValid_returnDtoErrorBasketClearNotValid_ReturnDtoErrorMarketNotAvailableValid() throws Exception {
        //given
        var requestBuilder = MockMvcRequestBuilders.post("/api/orders");

        //when

        this.mockMvc.perform(requestBuilder)
        //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isBadRequest(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                        {
                            "error": "Корзина пуста!"
                        }
""")
                );
    }


    @Test
    void newOrder_returnRedirectValid_returnDtoErrorBasketClearValid_ReturnDtoErrorMarketNotAvailableNotValid() throws Exception {
        //given

        ListBasketDto listBasketDto = new ListBasketDto();
        MarketEntity market = new MarketEntity("name", 1, "description", 3);
        market.setId(2);
        market.setInStock(false);
        listBasketDto.setMarket(market);

        var requestBuilder = MockMvcRequestBuilders.post("/api/orders")
                .sessionAttr("basket", listBasketDto);
        //when

        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isBadRequest(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                        {
                            "error": "В заказе есть товары, которых нет в наличии!"
                        }
""")
                );
    }

    @Test
    void newOrder_returnRedirectValid_returnDtoErrorBasketClearValid_ReturnDtoErrorMarketNotAvailableValid() throws Exception {
        //given
        ListBasketDto listBasketDto = new ListBasketDto();
        MarketEntity market = new MarketEntity("name", 1, "description", 3);
        market.setId(1);
        market.setInStock(true);
        listBasketDto.setMarket(market);

        var requestBuilder = MockMvcRequestBuilders.post("/api/orders")
                .sessionAttr("basket", listBasketDto);
        //when

        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isFound(),
                        MockMvcResultMatchers.header().string("Location", "/api/market")
                );
    }

}