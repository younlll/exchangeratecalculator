package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.domain.Country;

public interface ExchangeRateCalculatorService {
    Double getExchangeRate(Country remittanceCountry, Country recipientCountry);
}
