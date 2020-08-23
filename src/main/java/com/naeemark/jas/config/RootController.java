package com.naeemark.jas.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * Required to fix 404 error on swagger-ui console
 * <p>
 * Created on: 2020-08-24
 */
@ApiIgnore
@RestController
@RequestMapping("/")
public class RootController {
    @GetMapping
    public ResponseEntity<String> getToken(final HttpServletRequest request) {
        return ResponseEntity.ok().body("");
    }
}
