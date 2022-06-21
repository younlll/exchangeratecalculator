package com.exchangerate.exchangeratecalculator.controller;

import com.exchangerate.exchangeratecalculator.domain.Country;
import com.exchangerate.exchangeratecalculator.dto.ExchangeRateRequest;
import com.exchangerate.exchangeratecalculator.dto.ExchangeRateResponse;
import com.exchangerate.exchangeratecalculator.service.ExchangeRateCalculatorService;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeRateCalculatorController {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.00");

    private final ExchangeRateCalculatorService currencyCalculatorService;
    private final ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

    @GetMapping("/exchange-rate")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(
            @Valid @ModelAttribute ExchangeRateRequest exchangeRateRequest) {
        Double exchangeRate = getExchangeRate(exchangeRateRequest.getRemittanceCountry(),
                exchangeRateRequest.getRecipientCountry());
        exchangeRateResponse.setExchangeRate(DECIMAL_FORMAT.format(exchangeRate));
        return ResponseEntity.ok(exchangeRateResponse);
    }

    @GetMapping("/remittance-amount")
    public ResponseEntity<ExchangeRateResponse> getExchangeRateCalculation(
            @Valid @ModelAttribute ExchangeRateRequest exchangeRateRequest) {
        Map<String, String> calculationResult = new HashMap<>();

        Double exchangeRate = getExchangeRate(exchangeRateRequest.getRemittanceCountry(),
                exchangeRateRequest.getRecipientCountry());
        Double receptionAmount = exchangeRate * exchangeRateRequest.getAmount();

        exchangeRateResponse.setRecipientAmount(DECIMAL_FORMAT.format(receptionAmount));
        exchangeRateResponse.setExchangeRate(DECIMAL_FORMAT.format(exchangeRate));

        return ResponseEntity.ok(exchangeRateResponse);
    }

    private Double getExchangeRate(Country remittanceCountry, Country recipientCountry) {
        return currencyCalculatorService.getExchangeRate(remittanceCountry, recipientCountry);
    }
}
