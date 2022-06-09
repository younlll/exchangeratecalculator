package com.exchangerate.exchangeratecalculator.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    @NotBlank
    private String remittanceCountry;

    @NotBlank
    private String recipientCountry;
}
