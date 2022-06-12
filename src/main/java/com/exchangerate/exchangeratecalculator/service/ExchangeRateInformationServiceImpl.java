package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateApiResponse;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Profile("!local")
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

    public ExchangeRateInformationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setReadTimeout(Duration.ofSeconds(5))
                .setConnectTimeout(Duration.ofSeconds(5))
                .build();
    }


    @Override
    public ExchangeRateApiResponse getExchangeRateInformation() {
        HttpHeaders headers = createHttpHeaders(accessKey);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String uri = createUri();
        ResponseEntity<ExchangeRateApiResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, entity,
                ExchangeRateApiResponse.class);
        return response.getBody();
    }

    private String createUri() {
        return UriComponentsBuilder.fromHttpUrl(endPoint)
                .queryParam("source", source)
                .queryParam("currencies", currencies)
                .build(false)
                .toString();
    }

    private HttpHeaders createHttpHeaders(String accessKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("apikey", accessKey);
        return headers;
    }
}
