package com.example.demo.contollers;

import com.example.demo.controllers.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloControllerTest {

    @Test
    void sayHello() {
        HelloController controller = new HelloController();
        Model model = new BindingAwareModelMap();
        String result = controller.sayHello("Dolly", model);
        assertAll(
                () -> assertEquals("welcome", result),
                () -> assertEquals("Dolly", model.getAttribute("user"))
        );
    }
}
