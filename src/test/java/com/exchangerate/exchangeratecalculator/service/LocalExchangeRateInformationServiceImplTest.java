package com.exchangerate.exchangeratecalculator.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
class LocalExchangeRateInformationServiceImplTest {

    private final LocalExchangeRateInformationServiceImpl localExchangeRateInformationService = new LocalExchangeRateInformationServiceImpl();

    @Test
    void 환율정보_받아오기() {
        ExchangeRateApiResponse exchangeRateApiResponse = localExchangeRateInformationService.getExchangeRateInformation();

        assertAll(
                () -> assertThat(exchangeRateApiResponse.isSuccess()).isTrue(),
                () -> assertThat(exchangeRateApiResponse.getSource()).isEqualTo("USD"),
                () -> assertThat(exchangeRateApiResponse.getQuotes()).hasSize(3),
                () -> assertThat(exchangeRateApiResponse.getQuotes().get("USDKRW")).isEqualTo(1280.00),
                () -> assertThat(exchangeRateApiResponse.getQuotes().get("USDJPY")).isEqualTo(953.55),
                () -> assertThat(exchangeRateApiResponse.getQuotes().get("USDPHP")).isEqualTo(53.07)
        );
    }
}
