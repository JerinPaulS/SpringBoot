package com.example.demo.service;

import com.example.demo.services.AstroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AstroServiceTest {

    @Autowired
    private AstroService service;

    @Test
    void getPeopleInSpaceTest() {
        String people = service.getPeopleInSpace();
        assertTrue(people.contains("people"));
    }

    @Test
    void getAstroResponse() {
        var response = service.getAstroResponse();
        assertAll(
                () -> assertEquals("success", response.message()),
                () ->assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
    }

    @Test
    void getAstroResponseAsync() {
        var response = service.getAstroResponseAsync();
        assertAll(
                () -> assertEquals("success", response.message()),
                () ->assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
    }
}
