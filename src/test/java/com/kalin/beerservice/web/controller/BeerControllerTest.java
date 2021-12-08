package com.kalin.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalin.beerservice.web.model.BeerDto;
import com.kalin.beerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.kalin.beerservice.web.mappers")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("iscold","yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("v1/beer", pathParameters(
                        parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("I Beer Cold Query param")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version of Beer"),
                                fieldWithPath("createdDate").description("Create Date of Beer"),
                                fieldWithPath("lastModifiedDate").description("Id of Beer"),
                                fieldWithPath("beerName").description("Id of Beer"),
                                fieldWithPath("beerStyle").description("Id of Beer"),
                                fieldWithPath("upc").description("Id of Beer"),
                                fieldWithPath("price").description("Id of Beer"),
                                fieldWithPath("quantityOnHand").description("Id of Beer")
                        )));
    }

    @Test
    void saveNewBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(document("v1/beer",
                requestFields(
                        fieldWithPath("id").ignored(),
                        fieldWithPath("version").ignored(),
                        fieldWithPath("createdDate").ignored(),
                        fieldWithPath("lastModifiedDate").ignored(),
                        fieldWithPath("beerName").description("Name of the beer"),
                        fieldWithPath("beerStyle").description("Style of the beer"),
                        fieldWithPath("upc").description("Beer UPC").attributes(),
                        fieldWithPath("price").description("Price of the beer"),
                        fieldWithPath("quantityOnHand").ignored()
                )));


    }

    @Test
    void updateBeerById() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
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