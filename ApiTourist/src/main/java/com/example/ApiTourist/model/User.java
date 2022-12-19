package com.example.ApiTourist.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom_prenom;
    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = EAGER)
    //Les collections sont des conteneurs qui permettent de regrouper des objets en une seule entité
    private Collection<Role> roles= new ArrayList<>();
    }

