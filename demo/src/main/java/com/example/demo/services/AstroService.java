package com.example.demo.services;

import com.example.demo.json.AstroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.print.attribute.standard.Media;
import java.time.Duration;

@Service
public class AstroService {

    private final RestTemplate template;

    private final WebClient client;

//    public AstroService(RestTemplateBuilder builder) {
//        this.template = builder.rootUri("http://api.open-notify.org").build();
//        this.client = WebClient.create("http://api.open-notify.org");
//    }

    public AstroService(@Qualifier("astroRestTemplate") RestTemplate restTemplate) {
        this.template = restTemplate;
        this.client = WebClient.create("http://api.open-notify.org");
    }

    public String getPeopleInSpace() {
        return this.template.getForObject("/astros.json", String.class);
    }

    public AstroResponse getAstroResponse() {
        return this.template.getForObject("/astros.json", AstroResponse.class);
    }

    public AstroResponse getAstroResponseAsync() {
        return this.client.get()
                .uri("/astros.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AstroResponse.class)
                .block(Duration.ofSeconds(2));
    }
}
