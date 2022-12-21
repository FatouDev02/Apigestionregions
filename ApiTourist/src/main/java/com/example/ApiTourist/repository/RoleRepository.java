package com.example.ApiTourist.repository;


import com.example.ApiTourist.model.ERole;
import com.example.ApiTourist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


   // Role findByName(String name);
   Optional<Role> findByName(ERole name);

}
