package com.exchangerate.exchangeratecalculator.controller;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateRequest;
import com.exchangerate.exchangeratecalculator.service.ExchangeRateCalculatorServiceImpl;
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

    private final ExchangeRateCalculatorServiceImpl currencyCalculatorService;

    @GetMapping("/exchange-rate")
    public ResponseEntity<String> getExchangeRate(@Valid @ModelAttribute ExchangeRateRequest exchangeRateRequest) {
        Double exchangeRate = getExchangeRate(exchangeRateRequest.getRemittanceCountry(),
                exchangeRateRequest.getRecipientCountry());
        return ResponseEntity.ok(DECIMAL_FORMAT.format(exchangeRate));
    }

    @GetMapping("/remittance-amount")
    public ResponseEntity<Map<String, String>> getExchangeRateCalculation(
            @Valid @ModelAttribute ExchangeRateRequest exchangeRateRequest) {
        Map<String, String> calculationResult = new HashMap<>();

        Double exchangeRate = getExchangeRate(exchangeRateRequest.getRemittanceCountry(),
                exchangeRateRequest.getRecipientCountry());
        Double receptionAmount = exchangeRate * exchangeRateRequest.getAmount();

        calculationResult.put("amountReceivable", DECIMAL_FORMAT.format(receptionAmount));
        calculationResult.put("exchangeRate", DECIMAL_FORMAT.format(exchangeRate));

        return ResponseEntity.ok(calculationResult);
    }

    private Double getExchangeRate(String remittanceCountry, String recipientCountry) {
        return currencyCalculatorService.getExchangeRate(remittanceCountry, recipientCountry);
    }
}
