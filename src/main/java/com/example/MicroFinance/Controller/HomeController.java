package com.example.MicroFinance.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "MicroFinance uygulamasına hoşgeldiniz!";
    }
}
