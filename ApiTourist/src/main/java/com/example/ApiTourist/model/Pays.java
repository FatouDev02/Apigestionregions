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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pays;
    @Column(unique = true, length = 20)
    private String nom_pays;

    @OneToMany(mappedBy = "pays")
    List<Region> regionList;



}
