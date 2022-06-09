package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateInformationServiceImpl implements ExchangeRateInformationService {
    @Value("${currencyLayer.accessKey}")
    private String accessKey;
    @Value("${currencyLayer.endPoint}")
    private String endPoint;
    @Value("${currencyLayer.remittance}")
    private String source;
    @Value("${currencyLayer.currencies}")
    private String currencies;


    private final RestTemplate restTemplate;
    private ExchangeRateDto exchangeRateDto;

    public ExchangeRateInformationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public ExchangeRateDto getExchangeRateDto() {
        HttpHeaders headers = createHttpHeaders(accessKey);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<ExchangeRateDto> response = restTemplate.exchange(
                endPoint + "?source=" + source
                        + "&currencies=" + currencies,
                HttpMethod.GET, entity,
                ExchangeRateDto.class);
        return response.getBody();
    }

    private HttpHeaders createHttpHeaders(String accessKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("apikey", accessKey);
        return headers;
    }
}
