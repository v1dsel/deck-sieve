package com.cloakyloki.integration.controller;

import com.cloakyloki.integration.IntegrationTestBase;
import com.cloakyloki.service.CardService;
import com.cloakyloki.util.TestDataProvider;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RequiredArgsConstructor
class CardControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final CardService cardService;

    @Test
    void findById() throws Exception {
        var cardReadDto = cardService.create(TestDataProvider.createCardUpdateDto());
        mockMvc.perform(get("/cards/" + cardReadDto.getId().toString()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("card"))
                .andExpect(model().attribute("card", Matchers.equalTo(cardReadDto)))
                .andExpect(view().name("cardview/card"));
    }
}