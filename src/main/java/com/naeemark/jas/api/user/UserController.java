package com.naeemark.jas.api.user;

import com.naeemark.jas.models.response.UserResponse;
import com.naeemark.jas.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
@Api(tags = "3 - User", description = "Gets User Data by the header Token")
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "Get User", notes = "Gets User Object", response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @GetMapping(value = "/user")
    public UserResponse getUser(@RequestHeader("Authorization") String authorization) {

        UserResponse userResponse = JwtTokenUtils.validateAuthorization(authorization);
        logger.info(userResponse.toString());
        return userResponse;
    }
}
