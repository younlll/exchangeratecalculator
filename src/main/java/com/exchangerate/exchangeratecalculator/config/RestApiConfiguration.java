package com.exchangerate.exchangeratecalculator.config;

import com.exchangerate.exchangeratecalculator.dto.ApiRequestProperties;
import com.exchangerate.exchangeratecalculator.service.ExchangeRateInformationService;
import com.exchangerate.exchangeratecalculator.service.ExchangeRateInformationServiceImpl;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiConfiguration {

    @Autowired
    private ApiRequestProperties apiRequestProperties;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public ExchangeRateInformationService exchangeRateInformationService() {
        return new ExchangeRateInformationServiceImpl(apiRequestProperties, restTemplate(restTemplateBuilder));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
         return restTemplateBuilder
                 .setReadTimeout(Duration.ofSeconds(5))
                 .setConnectTimeout(Duration.ofSeconds(5))
                 .build();
    }
}
