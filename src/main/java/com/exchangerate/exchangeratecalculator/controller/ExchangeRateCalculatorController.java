package com.exchangerate.exchangeratecalculator.controller;

import com.exchangerate.exchangeratecalculator.dto.RequestDto;
import com.exchangerate.exchangeratecalculator.service.ExchangeRateCalculatorServiceImpl;
import java.text.DecimalFormat;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeRateCalculatorController {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    @Autowired
    private final ExchangeRateCalculatorServiceImpl currencyCalculatorService;

    @GetMapping("/exchange-rate")
    public ResponseEntity getExchangeRate(@Valid @ModelAttribute RequestDto requestDto) {
        Double exchangeRate = currencyCalculatorService
                .getExchangeRate(requestDto.getRemittanceCountry(), requestDto.getRecipientCountry());
        return ResponseEntity.ok(DECIMAL_FORMAT.format(exchangeRate));
    }
}


