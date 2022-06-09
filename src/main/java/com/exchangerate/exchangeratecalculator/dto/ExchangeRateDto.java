package com.exchangerate.exchangeratecalculator.dto;


import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExchangeRateDto {
    private boolean success;
    private int timestamp;
    private String source;
    private Map<String, Double> quotes;
    private Map<String, String> error;
}
