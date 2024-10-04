package com.example.progect.marketplace.controller;

import com.example.progect.marketplace.entity.MarketEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class MarketRestControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    void allMarket_ReturnListMarketEntity() throws Exception{
        //given
        var requestBuilder = MockMvcRequestBuilders.get("/api/market");

        //when
        this.mockMvc.perform(requestBuilder)
        //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void setMarket_ReturnListOneObjMarketEntity() throws Exception{
        //given
        var marketEntity = new MarketEntity("name", 2, "description", 3);
        var requestBuilder = MockMvcRequestBuilders.post("/api/market")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(marketEntity));

        // when
        this.mockMvc.perform(requestBuilder)
        // then
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                            {
                                "name": "name",
                                "price": 2,
                                "description": "description",
                                "quantity": 3
                            }
"""),
                        MockMvcResultMatchers.jsonPath("$.id").exists()
                );

    }

    @Test
    void setMarket_ReturnListOneObjError() throws Exception{
        //given
        var marketEntity = new MarketEntity("name", 2, "description", 0);
        var requestBuilder = MockMvcRequestBuilders.post("/api/market")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(marketEntity));

        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andExpectAll(
                        MockMvcResultMatchers.status().isBadRequest(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void market_ReturnErrorNotValid_ReturnResponseEntity() throws Exception{
        //given
        var requestBuilder = MockMvcRequestBuilders.get("/api/market/1");

        //when
        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                );
    }
}