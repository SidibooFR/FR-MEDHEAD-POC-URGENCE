package fr.medhead.urgence;


import fr.medhead.urgence.controller.UrgenceRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SmokeTest {
    @Autowired
    private UrgenceRestController controller;
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
