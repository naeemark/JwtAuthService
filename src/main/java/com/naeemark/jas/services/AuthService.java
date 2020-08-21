package com.naeemark.jas.services;

import com.naeemark.jas.models.response.AuthResponse;
import com.naeemark.jas.models.request.LoginRequest;
import com.naeemark.jas.models.request.SignupRequest;
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
