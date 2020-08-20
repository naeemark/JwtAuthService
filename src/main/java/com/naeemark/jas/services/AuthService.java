package com.naeemark.jas.services;

import com.naeemark.jas.models.AuthResponse;
import com.naeemark.jas.models.LoginRequest;
import com.naeemark.jas.models.SignupRequest;
import com.naeemark.jas.models.User;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
public interface AuthService {

    User register(SignupRequest signupRequest);
    User login(LoginRequest loginRequest);
    AuthResponse getAuthResponse(User user);
}
