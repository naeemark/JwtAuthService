package com.naeemark.jas.api.user;

import com.naeemark.jas.models.User;
import com.naeemark.jas.utils.JwtTokenUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@DisplayName("Unit tests for UserController")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET User - success")
    void testLogin_Success() throws Exception {

        User user = new User(1, "my name", "userName", "abc@xyz.com", "hashedPXXXXassword");
        String authorization = JwtTokenUtils.generateToken(user);

        mockMvc.perform(get("/api/user").header("Authorization", authorization))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.userName").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.userName").value(user.getUserName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    @DisplayName("GET User - failure")
    void testLogin_Failure() throws Exception {

        User user = new User(1, "my name", "userName", "abc@xyz.com", "hashedPXXXXassword");
        String authorization = "this-is-invalid-token";

        mockMvc.perform(get("/api/user").header("Authorization", authorization))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}