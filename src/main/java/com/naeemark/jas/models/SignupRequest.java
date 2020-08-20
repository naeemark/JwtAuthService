package com.naeemark.jas.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Data
@NoArgsConstructor
@ToString
public class SignupRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 15)
    private String password;
}
