package com.example.demo.services;

import com.example.demo.json.AstroResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AstroService {

    private final RestTemplate template;

    public AstroService(RestTemplateBuilder builder) {
        this.template = builder.rootUri("http://api.open-notify.org").build();
    }

    public String getPeopleInSpace() {
        return this.template.getForObject("/astros.json", String.class);
    }

    public AstroResponse getAstroResponse() {
        return this.template.getForObject("/astros.json", AstroResponse.class);
    }
}
