package com.exchangerate.exchangeratecalculator.service;

public interface ExchangeRateCalculatorService {
    Double getExchangeRate(String remittanceCountry, String recipientCountry);
}
