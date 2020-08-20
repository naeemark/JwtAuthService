package com.naeemark.jas.services;

import com.naeemark.jas.models.SignupRequest;
import com.naeemark.jas.models.User;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
public interface AuthService {

    public User register(SignupRequest signupRequest);
}
