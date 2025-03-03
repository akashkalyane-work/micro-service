package com.example.AuthService.repository;

import com.example.AuthService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    User findByEmail(String email);

}
