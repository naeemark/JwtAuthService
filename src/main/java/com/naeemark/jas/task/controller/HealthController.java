package com.naeemark.jas.task.controller;

import com.naeemark.jas.task.JwtAuthServiceApplication;
import com.naeemark.jas.task.model.Health;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.naeemark.jas.task.utils.Constants.SERVICE_NAME;


/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Api(tags = "Auth Service Health")
@RestController
@RequestMapping("/api")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthServiceApplication.class);

    @ApiOperation(value = "Health Check", notes = "Gets health status of the service", response = Health.class)
    @GetMapping(value = "/health")
    public Health checkHealth() {

        Health health = new Health(SERVICE_NAME, "OK");

        logger.info(health.toString());
        return health;
    }
}
