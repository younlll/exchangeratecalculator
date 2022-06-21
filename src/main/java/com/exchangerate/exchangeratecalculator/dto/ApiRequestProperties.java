package com.exchangerate.exchangeratecalculator.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "currency")
@Profile("alpha")
public class ApiRequestProperties {
    private String accessKey;
    private String endPoint;
    private String remittance;
    private String currencies;
}
