package com.example.ApiTourist.controller;


import com.example.ApiTourist.Security.jwt.JwtUtils;
import com.example.ApiTourist.Security.services.UserDetailsImpl;
import com.example.ApiTourist.model.ERole;
import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.User;
import com.example.ApiTourist.repository.RoleRepository;
import com.example.ApiTourist.repository.UserRepository;
import com.example.ApiTourist.services.userservices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.ApiTourist.payload.request.LoginRequest;
import com.example.ApiTourist.payload.request.SignupRequest;
import com.example.ApiTourist.payload.response.JwtResponse;
import com.example.ApiTourist.payload.response.MessageResponse;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ApiTourist/user")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class UserControllere {
    @Autowired
    userservices userservices;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
  //  @PreAuthorize("hasRole('Role_ADMIN')")
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
              .map(item -> item.getAuthority())
              .collect(Collectors.toList());

      return ResponseEntity.ok(new JwtResponse(jwt,
              userDetails.getId(),
              userDetails.getUsername(),
              userDetails.getEmail(),
              roles));
  }
  //inscriptionn
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/all")
    public String userAccesds() {
        return "Public Content.";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }



    @PreAuthorize("hasRole('Role_ADMIN')")
    @PostMapping("/addrole")
    public ResponseEntity<Role> addRole(@RequestBody Role role ){
        return ResponseEntity.ok().body( userservices.ajoutrole(role));
    }



//    @PreAuthorize("hasRole('Role_ADMIN')")
//    @PostMapping("/addroletoColl")
//    public ResponseEntity<?> addroletouser(@RequestBody RoletoColl roletoColl ){
//        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/addroletocoll").toUriString());
//        userservices.addroletouser(roletoColl.getUsername(),roletoColl.getRolename());
//        return ResponseEntity.ok().build();
//    }


}

/*

@Data
class RoletoColl{
    private String username;
    private String rolename;
}


*/
