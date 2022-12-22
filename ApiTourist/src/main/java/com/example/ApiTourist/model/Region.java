package com.example.ApiTourist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity/*Cette annotation spécifie que la classe est une entité : et sera enregistré dans la BDD */
@Getter/*de lombok */
@Setter/*de lombok */
public class Region {

    @Id /*Cette annotation spécifie la clé primaire de l’entité :*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Cette annotation spécifie les stratégies de génération pour les valeurs
    des clés primaires : La valeur peut être AUTO, TABLE, SEQUENCE ou IDENTITY. */
    private long id;
    private String coderegion;
    private String nom;
    private String Superficie;
    private String langue;
    private String domaineactivite;

    private String description;
    private String img;


    @ManyToOne
    private Pays pays;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    List<Population> populations=new ArrayList<>();
   /* @ManyToMany
    @JoinTable(
            name = "Regionactivites",
            joinColumns = @JoinColumn(name="region_id"),
            inverseJoinColumns = @JoinColumn(name = "activite_id"))
    List<Activites> activitesList;*/
}
