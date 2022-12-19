package com.example.ApiTourist.repository;


import com.example.ApiTourist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Role findByName(String name);

}
