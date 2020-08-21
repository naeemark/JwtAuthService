package com.naeemark.jas.models.response;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

    private Integer id;
    private String name;
    private String userName;
    private String email;
}
