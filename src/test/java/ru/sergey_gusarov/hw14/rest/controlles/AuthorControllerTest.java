package ru.sergey_gusarov.hw14.rest.controlles;

import javafx.application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorController.class)
@DataMongoTest
@ComponentScan("ru.sergey_gusarov.hw14")
@ContextConfiguration(classes= Application.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listAuthorPage() throws Exception {
        Model model = new ConcurrentModel();
        model.addAttribute("test", "test");
        given(authorController.listAuthorPage(model))
                .willReturn("list book");
        this.mvc.perform(get("/authors")).
                andExpect(status().isOk()).
                andExpect(content().string("list book"));
    }

    @Test
    void newAuthorPage() {
    }

    @Test
    void newAuthorFromBookPage() {
    }

    @Test
    void authorPage() {
    }

    @Test
    void editAuthor() {
    }

    @Test
    void deleteAuthor() {
    }
}