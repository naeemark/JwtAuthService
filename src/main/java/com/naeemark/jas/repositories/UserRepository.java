package com.naeemark.jas.repositories;

import com.naeemark.jas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-21
 */
@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findByUserNameOrEmail(String userName, String email);

    boolean existsByEmail(String email);
}