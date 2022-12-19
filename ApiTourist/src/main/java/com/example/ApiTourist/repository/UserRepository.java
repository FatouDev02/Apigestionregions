package com.example.ApiTourist.repository;


import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
