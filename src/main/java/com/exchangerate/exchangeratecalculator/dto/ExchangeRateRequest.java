package com.exchangerate.exchangeratecalculator.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRateRequest {
    @NotBlank
    private String remittanceCountry;

    @NotBlank
    private String recipientCountry;
}
