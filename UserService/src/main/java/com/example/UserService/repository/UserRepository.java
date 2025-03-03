package com.example.UserService.repository;

import com.example.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    User findByEmail(String email);

}
