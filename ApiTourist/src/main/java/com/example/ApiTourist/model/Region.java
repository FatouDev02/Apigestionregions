package com.example.ApiTourist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String nomregion;
    private String activité;
    private String Superficie;
    private String langue;

    @ManyToOne
    private Pays pays;

    @ManyToMany
    @JoinTable(
            name = "RegionPopulation",
            joinColumns = @JoinColumn(name="region_id"),
            inverseJoinColumns = @JoinColumn(name = "population_id")
    )
    List<Population> populations;

}
