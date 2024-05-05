package com.example.demo.service;

import com.example.demo.services.AstroInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AstroInterfaceTest {

    @Autowired
    private AstroInterface astroInterface;

    @Test
    void getAstroResponse() {
        var response = astroInterface.getResponse();
        assertAll(
                () -> assertEquals("success", response.message()),
                () ->assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
    }
}
