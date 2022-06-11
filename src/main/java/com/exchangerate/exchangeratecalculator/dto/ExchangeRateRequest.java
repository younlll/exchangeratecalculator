package com.exchangerate.exchangeratecalculator.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRateRequest {
    @NotBlank
    private String remittanceCountry;

    @NotBlank
    private String recipientCountry;

    @Min(value = 0, message = "송금액은 0 이상이어야 합니다.")
    private Double amount;
}
