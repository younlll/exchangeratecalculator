package com.exchangerate.exchangeratecalculator.dto;

import com.exchangerate.exchangeratecalculator.domain.Country;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRateRequest {
    @NotNull
    private Country remittanceCountry;

    @NotNull
    private Country recipientCountry;

    @Min(value = 0, message = "송금액은 0 이상이어야 합니다.")
    @Max(value = 10000, message = "송금액은 10000 이하이어야 합니다.")
    private Double amount;
}
