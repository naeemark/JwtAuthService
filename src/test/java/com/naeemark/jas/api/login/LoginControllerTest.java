package com.naeemark.jas.api.login;

import com.naeemark.jas.models.User;
import com.naeemark.jas.models.request.LoginRequest;
import com.naeemark.jas.models.response.AuthResponse;
import com.naeemark.jas.repositories.UserRepository;
import com.naeemark.jas.services.AuthService;
import com.naeemark.jas.utils.StringUtils;
import com.naeemark.jas.utils.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
@DisplayName("Unit tests for LoginController")
class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserRepository userRepository;


    @Test
    @DisplayName("POST Login - success")
    void testLogin_Success() throws Exception {
        String userNameOrEmail = "jimiOk";
        String password = "ABC@123";
        LoginRequest loginRequest= new LoginRequest(userNameOrEmail, password);
        String hashedPassword = StringUtils.getHashedPassword(loginRequest.getPassword());
        User user = new User(1, "my name", userNameOrEmail, "abc@xyz.com", hashedPassword);
        AuthResponse authResponse = new AuthResponse("this is access Token", new User(user.getId(), user.getUserName()));
        //given
        when(authService.login(loginRequest)).thenReturn(user);
        when(authService.getAuthResponse(user)).thenReturn(authResponse);

        // when
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(loginRequest))
                .characterEncoding("utf-8"))

                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.accessToken").value(authResponse.getAccessToken()))
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user.id").exists())
                .andExpect(jsonPath("$.user.id").value(authResponse.getUser().getId()));
    }

    @Test
    @DisplayName("POST Login - success - Not Found")
    void testLogin_SuccessNotFound() throws Exception {
        String userNameOrEmail = "jimiOk";
        String password = "ABC@123";
        LoginRequest loginRequest= new LoginRequest(userNameOrEmail, password);
        String hashedPassword = StringUtils.getHashedPassword(loginRequest.getPassword());
        User user = new User(1, "my name", userNameOrEmail, "abc@xyz.com", hashedPassword);
        AuthResponse authResponse = new AuthResponse("this is access Token", new User(user.getId(), user.getUserName()));

        //given
        when(authService.login(loginRequest)).thenReturn(null);
//        when(authService.getAuthResponse(user)).thenReturn(authResponse);

        // when
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(loginRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("POST Login - noPasswordValidationFailure")
    void testFeatureAccess_noPasswordValidationFailure() throws Exception {


        String userNameOrEmail = "jimiOk";
        String password = "";

        LoginRequest loginRequest = new LoginRequest(userNameOrEmail, password);

        // when
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(loginRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

}