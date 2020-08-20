package com.naeemark.jas.controller;

import com.naeemark.jas.api.health.HealthController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.naeemark.jas.utils.Constants.SERVICE_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(HealthController.class)
@DisplayName("Unit tests for HealthController")
class HealthResponseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Check HealthResponse")
    void checkHealth() throws Exception{

        mockMvc.perform(get("/api/health/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serviceName").value(SERVICE_NAME))
                .andExpect(jsonPath("$.status").value("OK"));
    }
}