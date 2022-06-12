package com.exchangerate.exchangeratecalculator.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exchangerate.exchangeratecalculator.domain.Country;
import com.exchangerate.exchangeratecalculator.service.ExchangeRateCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
class ExchangeRateCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateCalculatorService exchangeRateCalculatorService;

    @Test
    void 환율_기본정보_가져오기_성공() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/exchange-rate")
                    .param("remittanceCountry", Country.USD.name())
                    .param("recipientCountry", Country.KRW.name()))
                .andExpect(status().isOk());
    }

    @Test
    void 환율_기본정보_가져오기_실패_404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/exchange-rate1")
                    .param("remittanceCountry", Country.USD.name())
                    .param("recipientCountry", Country.KRW.name()))
                .andExpect(status().isNotFound());
    }

    @Test
    void 환율_기본정보_가져오기_실패_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/exchange-rate")
                        .param("remittanceCountry", Country.USD.name())
                        .param("recipientCountry", "KRM"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 송금국가에따른_환율_계산() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/remittance-amount")
                    .param("remittanceCountry", Country.USD.name())
                    .param("recipientCountry", Country.KRW.name())
                    .param("amount", "10"))
                .andExpect(status().isOk());
    }
}