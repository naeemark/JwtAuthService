package com.naeemark.jas.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Data
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotBlank(message = "Username or Email is mandatory")
    private String userNameOrEmail;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
