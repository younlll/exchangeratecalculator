package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateCalculatorServiceImpl implements ExchangeRateCalculatorService {
    @Value("${currencyLayer.remittance}")
    private String remittanceCountry;

    private final ExchangeRateInformationService exchangeRateInformationService;

    @Override
    public Double getExchangeRate(String remittanceCountry, String recipientCountry) {
        ExchangeRateApiResponse exchangeRateApiResponse = exchangeRateInformationService.getExchangeRateInformation();
        return exchangeRateApiResponse.getQuotes().get(remittanceCountry + recipientCountry);
    }
}
