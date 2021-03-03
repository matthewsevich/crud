package by.matusevich.crud2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ControllerTest {

    @Autowired
    HumanController humanController;

    @Test
    void contextLoads() throws Exception{
        assertNotNull(humanController);
    }

}
