package com.example.ApiTourist.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "population")
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_population; /*doit etre recuperer dans la table Region*/
        private String Chiffre;
        private long annee;
    @ManyToMany
    List<Region> region;

}
