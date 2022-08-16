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
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id_population; /*doit etre recuperer dans la table Region*/
        private String Chiffre;
        private long annee;

    //@ManyToMany(mappedBy = "population", fetch = FetchType.LAZY)
    //private Set<Region> regionsy = new HashSet<>();
    @ManyToMany(mappedBy = "populations")
    List<Region> region;

}
