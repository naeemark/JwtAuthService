package com.naeemark.jas.utils;

import com.naeemark.jas.models.User;
import com.naeemark.jas.models.response.UserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static com.naeemark.jas.utils.Constants.*;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
public class JwtTokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    /**
     * Generates an Access Token based on provided payload
     *
     * @param user User
     * @return String
     */
    public static String generateToken(User user) {

        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + JWT_ACCESS_TOKEN_EXPIRY_MILLIS))
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("userName", user.getUserName())
                .compact();

        logger.info(token);
        return token;
    }

    /**
     * Validates and returns claim values
     *
     * @param authorization
     * @return
     */
    public static UserResponse validateAuthorization(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ERROR_AUTHORIZATION_REQUIRED);
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(authorization).getBody();
            return new UserResponse(
                    Integer.parseInt(claims.get("id").toString()),
                    claims.get("name").toString(),
                    claims.get("userName").toString(),
                    claims.get("email").toString()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ERROR_INVALID_AUTHORIZATION);
        }
    }
}
