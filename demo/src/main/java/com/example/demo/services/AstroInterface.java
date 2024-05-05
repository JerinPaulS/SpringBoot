package com.example.demo.services;

import com.example.demo.json.AstroResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface AstroInterface {

    @GetExchange("/astros.json")
    AstroResponse getResponse();
}
