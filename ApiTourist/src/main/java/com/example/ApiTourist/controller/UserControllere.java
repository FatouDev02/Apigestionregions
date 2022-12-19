package com.example.ApiTourist.controller;


import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.User;
import com.example.ApiTourist.repository.UserRepository;
import com.example.ApiTourist.services.userservices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@CrossOrigin()
@Controller
@RequestMapping("/ApiTourist/user")
public class UserControllere {
    @Autowired
    userservices userservices;
    @Autowired
    UserRepository userRepository;


    @PreAuthorize("hasRole('Role_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<User> adduser(@RequestBody User user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/adduser").toUriString());
        return ResponseEntity.created(uri).body( userservices.ajout(user));
    }
    @PreAuthorize("hasRole('Role_ADMIN')")
    @PostMapping("/addrole")
    public ResponseEntity<Role> addRole(@RequestBody Role role ){
        return ResponseEntity.ok().body( userservices.ajoutrole(role));
    }



    @PreAuthorize("hasRole('Role_ADMIN')")
    @PostMapping("/addroletoColl")
    public ResponseEntity<?> addroletouser(@RequestBody RoletoColl roletoColl ){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/addroletocoll").toUriString());
        userservices.addroletouser(roletoColl.getUsername(),roletoColl.getRolename());
        return ResponseEntity.ok().build();
    }


}


@Data
class RoletoColl{
    private String username;
    private String rolename;
}


