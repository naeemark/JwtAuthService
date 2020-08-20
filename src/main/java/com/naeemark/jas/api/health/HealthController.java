package com.naeemark.jas.api.health;

import com.naeemark.jas.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Api(tags = "0 - HealthResponse Check", description = "Provides health status of the service")
@RestController
@RequestMapping("/api")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @ApiOperation(value = "HealthResponse Check", notes = "Gets health status of the service", response = HealthResponse.class)
    @GetMapping(value = "/health")
    public HealthResponse checkHealth() {

        HealthResponse healthResponse = new HealthResponse(Constants.SERVICE_NAME, "OK");

        logger.info(healthResponse.toString());
        return healthResponse;
    }
}
