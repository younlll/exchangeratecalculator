package com.exchangerate.exchangeratecalculator.dto;

import lombok.Data;

@Data
public class ExchangeRateResponse {
    private String exchangeRate;
    private String recipientAmount;
}
