package com.example.ApiTourist.services;


import com.example.ApiTourist.model.Role;
import com.example.ApiTourist.model.User;

import java.util.List;

public interface userservices {
    User ajout(User user);
    Role ajoutrole(Role role);
    void addroletouser(String username,String roleName);
    User getuser(String username);
    List<User> lister();
}
