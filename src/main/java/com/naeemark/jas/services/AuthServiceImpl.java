package com.naeemark.jas.services;

import com.naeemark.jas.models.SignupRequest;
import com.naeemark.jas.models.User;
import com.naeemark.jas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(SignupRequest signupRequest) {

        User user = new User(signupRequest);
        User save = userRepository.save(user);

        return save;
    }
}
