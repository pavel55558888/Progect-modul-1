package com.example.progect.marketplace.controller;

import com.example.progect.marketplace.dto.ListBasketDto;
import com.example.progect.marketplace.entity.MarketEntity;
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
class BasketRestControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    void basket_returnAllBasketUser() throws Exception {
        //given
        ListBasketDto listBasketDto = new ListBasketDto();
        MarketEntity market = new MarketEntity("name", 1, "description", 3);
        market.setId(1);
        market.setInStock(true);
        listBasketDto.setMarket(market);

        var requestBuilder = MockMvcRequestBuilders.get("/api/basket")
                .sessionAttr("basket", listBasketDto);
        //when

        this.mockMvc.perform(requestBuilder)
        //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                            [
                            {
                                "name": "name",
                                "price": 1,
                                "description": "description",
                                "quantity": 3
                            }
                            ]
""")
                );

    }

    @Test
    void setBasket_returnRedirect() throws Exception{
        //given

        var requestBuilder = MockMvcRequestBuilders.post("/api/basket/1");
        //when

        this.mockMvc.perform(requestBuilder)
        //then
                .andExpectAll(
                    MockMvcResultMatchers.status().isOk()
        );

    }
}