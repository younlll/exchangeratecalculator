package com.exchangerate.exchangeratecalculator.service;

import com.exchangerate.exchangeratecalculator.dto.ExchangeRateApiResponse;
import java.util.HashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalExchangeRateInformationServiceImpl implements ExchangeRateInformationService {

    @Override
    public ExchangeRateApiResponse getExchangeRateInformation() {
        return new ExchangeRateApiResponse(
                true,
                System.currentTimeMillis(),
                "USD",
                new HashMap<>() {
                    {
                        put("USDKRW", 1280.00);
                        put("USDJPY", 953.55);
                        put("USDPHP", 53.07);
                    }
                },
                null
        );
    }
}
