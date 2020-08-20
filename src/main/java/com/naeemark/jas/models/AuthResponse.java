package com.naeemark.jas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {

    private String accessToken;
}
