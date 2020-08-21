package com.naeemark.jas.services;

import com.naeemark.jas.models.User;
import com.naeemark.jas.models.response.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Test
    void testGetAuthResponse_success() {

        String name = "James Okalahama";
        String userName = "jimiOk";
        String email = "abc@gmail.com";
        User user = new User(1, name, userName, email, "hashedPassword");
        AuthResponse authResponse = authService.getAuthResponse(user);
        assert authResponse != null;
        assert authResponse.getUser().getId().equals(user.getId());
        assert authResponse.getUser().getName().equals(user.getName());
    }
}