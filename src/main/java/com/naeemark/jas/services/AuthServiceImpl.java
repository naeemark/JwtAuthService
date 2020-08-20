package com.naeemark.jas.services;

import com.naeemark.jas.models.AuthResponse;
import com.naeemark.jas.models.LoginRequest;
import com.naeemark.jas.models.SignupRequest;
import com.naeemark.jas.models.User;
import com.naeemark.jas.repositories.UserRepository;
import com.naeemark.jas.utils.JwtUtils;
import com.naeemark.jas.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.NoSuchObjectException;

import static com.naeemark.jas.utils.Constants.*;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    /**
     * Registers new User
     * - Encodes password before sending to database
     * - Returns JWT Token with UserInfo
     *
     * @param signupRequest
     * @return
     */
    @Override
    public User register(SignupRequest signupRequest) {

        try {
            String hashedPassword = StringUtils.getHashedPassword(signupRequest.getPassword());
            User user = new User(signupRequest);
            user.setPassword(hashedPassword);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, ERROR_DUPLICATE_KEY_ATTRIBUTE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ERROR_AUTH_SERVICE);
        }
    }

    /**
     * Returns a user is credentials are correct
     * @param loginRequest
     * @return
     */
    @Override
    public User login(LoginRequest loginRequest) {

        try {
            String hashedPassword = StringUtils.getHashedPassword(loginRequest.getPassword());
            User user = userRepository.findByUserNameOrEmail(loginRequest.getUserNameOrEmail(), loginRequest.getUserNameOrEmail());
            if (user == null ) {
                throw new NoSuchObjectException(ERROR_USER_NOT_FOUND);
            }
            if(!user.getPassword().equals(hashedPassword)){
                throw new NoSuchObjectException(ERROR_WRONG_PASSWORD);
            }
            return user;
        } catch (NoSuchObjectException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ERROR_AUTH_SERVICE);
        }
    }


    /**
     * Provide an Object of AuthResponse
     * @param user
     * @return
     */
    @Override
    public AuthResponse getAuthResponse(User user) {
        String accessToken = JwtUtils.generateJwtToken(user);
        AuthResponse authResponse = new AuthResponse(accessToken, new User(user.getId(), user.getName()));
        logger.info(authResponse.toString());
        return authResponse;
    }
}
