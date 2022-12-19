package com.example.ApiTourist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String superficie;
    private String capitale;
    private String drapeau;


    // @OneToMany(mappedBy = "pays")
    //List<Region> regionList;



}
