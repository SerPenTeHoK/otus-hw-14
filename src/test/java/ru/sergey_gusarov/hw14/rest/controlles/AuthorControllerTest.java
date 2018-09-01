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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@ExtendWith(SpringExtension.class)

@ComponentScan("ru.sergey_gusarov.hw14.repository")
@WebAppConfiguration
*/
//@ContextConfiguration(classes = Application.class)
//@ContextConfiguration(classes = Config.class)

@ExtendWith(SpringExtension.class)
@DataMongoTest
@SpringBootTest
//@WebAppConfiguration
//@WebMvcTest(controllers = AuthorController.class)

class AuthorControllerTest {

    public class StandaloneMvcTestViewResolver extends InternalResourceViewResolver {

        public StandaloneMvcTestViewResolver() {
            super();
        }

        @Override
        protected AbstractUrlBasedView buildView(final String viewName) throws Exception {
            final InternalResourceView view = (InternalResourceView) super.buildView(viewName);
            // prevent checking for circular view paths
            view.setPreventDispatchLoop(false);
            return view;
        }
    }

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @MockBean
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).setViewResolvers(new StandaloneMvcTestViewResolver()).build();
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void listAuthorPage() throws Exception {
        Model model = new ConcurrentModel();
        model.addAttribute("test", "test");
        given(authorController.listAuthorPage(model))
                .willReturn("list book");
        this.mockMvc.perform(get("/authors")).
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