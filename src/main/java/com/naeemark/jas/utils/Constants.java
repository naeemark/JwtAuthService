package com.naeemark.jas.utils;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
public class Constants {
    public static final String SERVICE_NAME = "jwt-auth-service";
    public static final String JWT_SECRET_KEY = "this-is-a-secret-key";
    public static final long JWT_ACCESS_TOKEN_EXPIRY_MILLIS = 1000L * 60 * 60; // ONE HOUR

    public static final String ERROR_DUPLICATE_KEY_ATTRIBUTE = "Duplication of such attribute is not allowed";
    public static final String ERROR_USER_NOT_FOUND = "Requested user is not found in the system";
    public static final String ERROR_WRONG_PASSWORD = "The provided password does not match";
    public static final String ERROR_AUTH_SERVICE = "Something wrong happend in AuthService";
    public static final String ERROR_AUTHORIZATION_REQUIRED = "Should you provide authorization";
    public static final String ERROR_INVALID_AUTHORIZATION = "Expired or Invalid authorization Token was provided";
}
