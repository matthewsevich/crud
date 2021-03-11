package by.matusevich.crud2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext
class ControllerTest {

    @Autowired
    HumanController humanController;

    @Test
    void contextLoads() throws Exception{
        assertNotNull(humanController);
    }

}
