package com.kalin.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalin.beerservice.web.model.BeerDto;
import com.kalin.beerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.UUID;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());


    }

    @Test
    void updateBeerById() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());


    }


    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("New Beer")
                .beerStyle(BeerStyleEnum.ARIANA)
                .price(new BigDecimal("2.99"))
                .upc(123123123123L)
                .build();
    }

}