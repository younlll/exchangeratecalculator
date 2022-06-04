package com.exchangerate.exchangeratecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangerateMainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
