package com.naeemark.jas.api.signup;

import com.naeemark.jas.models.AuthResponse;
import com.naeemark.jas.models.SignupRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */

@Api(tags = "1 - Sign Up", description = "Operations related to User Registration")
@RestController
@RequestMapping("/api")
public class SignupController {

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    /**
     * Sample Request Params
     * {
     *      "name": "James Oak",
     *      "userName": "james",
     *      "email": "abcd@gmail.com"
     *      "password": "Abc@12345"
     * }
     * @param signupRequest
     * @return Auth Response
     */
    @ApiOperation(value = "Signup", response = AuthResponse.class, tags = {"1 - Sign Up"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 400, message = "Validation Error")
    })
    @PostMapping(value = "/signup")
    public AuthResponse signUp(@Valid @RequestBody SignupRequest signupRequest){
        logger.info(signupRequest.toString());

        AuthResponse authResponse = new AuthResponse("Hi...you are registered");

        logger.info(authResponse.toString());

        return authResponse;
    }
}
