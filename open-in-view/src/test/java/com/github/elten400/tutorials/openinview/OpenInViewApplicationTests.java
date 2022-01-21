package com.github.elten400.tutorials.openinview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Open in view works with Transactional session for lazy loading procedure.
 * You must enable it when you use 'Entity' class as 'REST API' response or inside 'MVC template' but it's wrong.
 * Use DTO class and disable it. Then you will not need keep it opened. Because you will already load all fields.
 * When it's enabled, it's similar to mixed procedure with DB.
 * It will keep the DB session open during procedures that do not need it.
 */
@SpringBootTest()
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.jpa.open-in-view=true"
})
public class OpenInViewApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     * view for MVC - template rendering procedure
     */
    @Test
    public void mvc_open_in_view() throws Exception {
        System.err.println("open-in-view works for MVC view.");
        mockMvc.perform(get("/mvc/students/1"))
                .andExpect(status().isOk())
                .andExpect(e -> {
                    System.err.println("response:" + e.getResponse().getContentAsString());
                });
    }

    /**
     * View for rest api - JSON or XML creation procedure
     */
    @Test
    public void rest_open_in_view() throws Exception {
        System.err.println("open-in-view works for Rest service view.");
        mockMvc.perform(get("/rest/students/1"))
                .andExpect(status().isOk())
                .andExpect((e) -> {
                    System.err.println("response:" + e.getResponse().getContentAsString());
                });
    }

}
