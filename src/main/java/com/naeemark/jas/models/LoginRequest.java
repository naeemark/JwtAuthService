package com.naeemark.jas.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {

    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank(message = "Feature Name is mandatory")
    private String password;
}
