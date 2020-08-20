package com.naeemark.jas.models;

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

    public User(SignupRequest signupRequest) {
        this.name = signupRequest.getName();
        this.userName= signupRequest.getUserName();
        this.email= signupRequest.getEmail();
        this.password= signupRequest.getPassword();
    }
}
