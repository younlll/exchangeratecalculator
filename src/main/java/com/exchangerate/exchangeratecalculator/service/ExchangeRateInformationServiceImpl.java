package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.dto.ApiRequestProperties;
import com.exchangerate.exchangeratecalculator.dto.ExchangeRateApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@Profile("alpha")
public class ExchangeRateInformationServiceImpl implements ExchangeRateInformationService {

    private final ApiRequestProperties apiRequestProperties;
    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeRateInformationServiceImpl(
            ApiRequestProperties apiRequestProperties, RestTemplate restTemplate) {
        this.apiRequestProperties = apiRequestProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public ExchangeRateApiResponse getExchangeRateInformation() {
        HttpHeaders headers = createHttpHeaders(apiRequestProperties.getAccessKey());
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String uri = createUri();
        ResponseEntity<ExchangeRateApiResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, entity,
                ExchangeRateApiResponse.class);
        return response.getBody();
    }

    private String createUri() {
        return UriComponentsBuilder.fromHttpUrl(apiRequestProperties.getEndPoint())
                .queryParam("source", apiRequestProperties.getRemittance())
                .queryParam("currencies", apiRequestProperties.getCurrencies())
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
