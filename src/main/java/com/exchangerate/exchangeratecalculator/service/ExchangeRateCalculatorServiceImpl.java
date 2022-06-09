package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateCalculatorServiceImpl implements ExchangeRateCalculatorService {
    @Value("${currencyLayer.remittance}")
    private String remittanceCountry;

    @Autowired
    private final ExchangeRateInformationService exchangeRateInformationService;

    @Override
    public Double getExchangeRate(String remittanceCountry, String recipientCountry) {
        ExchangeRateDto exchangeRateDto = exchangeRateInformationService.getExchangeRateDto();
        return exchangeRateDto.getQuotes().get(remittanceCountry + recipientCountry);
    }
}
