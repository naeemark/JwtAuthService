package com.naeemark.jas.services;

import com.naeemark.jas.models.SignupRequest;
import com.naeemark.jas.models.User;
import com.naeemark.jas.repositories.UserRepository;
import com.naeemark.jas.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.naeemark.jas.utils.Constants.ERROR_AUTH_SERVICE;
import static com.naeemark.jas.utils.Constants.ERROR_DUPLICATE_KEY_ATTRIBUTE;

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
     * - Returns JWT Token with UserInfo
     *
     * @param signupRequest
     * @return
     */
    @Override
    public User register(SignupRequest signupRequest) {

        try {
            User user = new User(signupRequest);
            String hashedPassword = StringUtils.getHashedPassword(user.getPassword());
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
}
