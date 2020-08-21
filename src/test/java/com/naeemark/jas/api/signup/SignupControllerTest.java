package com.naeemark.jas.api.signup;

import com.naeemark.jas.models.User;
import com.naeemark.jas.models.request.SignupRequest;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

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
@WebMvcTest(SignupController.class)
@DisplayName("Unit tests for SignupController")
class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("POST Signup - success")
    void testSignup_Success() throws Exception {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abc@gmail.com";
        String password = "ABC@123";

        SignupRequest signupRequest = new SignupRequest(name, userName, email, password);
        String hashedPassword = StringUtils.getHashedPassword(signupRequest.getPassword());
        User user = new User(1, name, userName, email, hashedPassword);
        AuthResponse authResponse = new AuthResponse("this is access Token", new User(user.getId(), user.getUserName()));

        //given
        when(authService.register(signupRequest)).thenReturn(user);
        when(authService.getAuthResponse(user)).thenReturn(authResponse);

        // when
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(signupRequest))
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
    @DisplayName("POST Signup - validationFailure")
    void testSignup_validationFailure() throws Exception {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abcgmail.com";
        String password = "ABC@123";

        SignupRequest signupRequest = new SignupRequest(name, userName, email, password);
        // when
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(signupRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }


    @Test
    @DisplayName("POST Signup - noPasswordValidationFailure")
    void testFeatureAccess_noPasswordValidationFailure() throws Exception {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abc@gmail.com";
        String password = "";

        SignupRequest signupRequest = new SignupRequest(name, userName, email, password);

        // when
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(signupRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }


    @Test
    @DisplayName("POST Signup - longPasswordValidationFailure")
    void testFeatureAccess_longPasswordValidationFailure() throws Exception {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abc@gmail.com";
        String password = "abcdef123456789asdfgh";

        SignupRequest signupRequest = new SignupRequest(name, userName, email, password);

        // when
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(signupRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    @DisplayName("POST Signup - shortPasswordValidationFailure")
    void testFeatureAccess_shortPasswordValidationFailure() throws Exception {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abc@gmail.com";
        String password = "123";

        SignupRequest signupRequest = new SignupRequest(name, userName, email, password);

        // when
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(signupRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    @DisplayName("POST Signup - userNameConflictFailure")
    void testFeatureAccess_userNameConflictFailure() throws Exception {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abc@gmail.com";
        String password = "ABC@123";

        SignupRequest signupRequest = new SignupRequest(name, userName, email, password);
        String hashedPassword = StringUtils.getHashedPassword(signupRequest.getPassword());
        User user = new User(1, name, userName, email, hashedPassword);
        AuthResponse authResponse = new AuthResponse("this is access Token", new User(user.getId(), user.getUserName()));

        //given
        when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);

        // when
        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.asJsonString(signupRequest))
                .characterEncoding("utf-8"))
                // then
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

}