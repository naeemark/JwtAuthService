package com.naeemark.jas.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(unique = true, name = "user_name")
    private String userName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;


    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(SignupRequest signupRequest) {
        this.name = signupRequest.getName();
        this.userName = signupRequest.getUserName().toLowerCase();
        this.email = signupRequest.getEmail().toLowerCase();
        this.password = signupRequest.getPassword().toLowerCase();
    }
}
