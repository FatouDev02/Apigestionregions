package com.example.ApiTourist.services.implementationservices;


import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.User;
import com.example.ApiTourist.repository.RoleRepository;
import com.example.ApiTourist.repository.UserRepository;
import com.example.ApiTourist.services.userservices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class userimpl implements userservices,UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User ajout(User user) {
        //comment
        log.info("Ajout d'un collaborateur {} ",user.getUsername());
        //a l'enregistrement on recupère le passwor et le l'encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role ajoutrole(Role role) {
        log.info("Ajout d'un role  {} dans la Bdd",role.getName());
        return roleRepository.save(role);    }

    @Override
    public void addroletouser(String username, String roleName) {
        log.info("Attribution d'un role {} à un collaborateur {}",roleName,username);
        User user=userRepository.findByUsername(username);
        Role role=roleRepository.findByName(roleName);
        //will executing because we have transactionnal
        user.getRoles().add(role);
    }

    @Override
    public User getuser(String username) {
        return null;
    }

    @Override
    public List<User> lister() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.trim().isEmpty()) {
            throw new UsernameNotFoundException("username vide");
        }
        //recupere le collaborateurs par son username
        User users=userRepository.findByUsername(username);
        if(users == null){
            //si le coll n'existe pas retouner cette erreur
            log.error("Collaborateur non trouvé");

            throw new UsernameNotFoundException("Collaborateur non trouvé");
        } else{
            //sinon sil existe retouner ce messsage
            log.info("Collaborateur  trouvé",username);

        }

        //noonnnnnnnnnnnn compris
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        users.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new  org.springframework.security.core.userdetails.User(users.getUsername(),users.getPassword(),authorities);
        ////////////////////////
        // L'interface UserDetails  représente un objet
        // utilisateur authentifié et Spring Security fournit une implémentation prête à l'emploi de org.......
    }
}
