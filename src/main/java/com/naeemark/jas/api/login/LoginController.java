package com.naeemark.jas.api.login;

import com.naeemark.jas.models.AuthResponse;
import com.naeemark.jas.models.LoginRequest;
import com.naeemark.jas.services.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */

@Api(tags = "2 - Login", description = "Operations related to User Login")
@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthService authService;


    /**
     * Sample Request Params
     * {
     *      "userName": "james", or
     *      "email": "abcd@gmail.com"
     *      "password": "Abc@12345"
     * }
     * @param loginRequest
     * @return Auth Response
     */
    @ApiOperation(value = "Login", response = AuthResponse.class, tags = {"2 - Login"})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation Error")
    })
    @PostMapping(value = "/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        logger.info(loginRequest.toString());

        AuthResponse authResponse = new AuthResponse("Hi...you are logged in", null);

        logger.info(authResponse.toString());

        return authResponse;
    }
}
