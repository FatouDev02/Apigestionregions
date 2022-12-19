package com.example.ApiTourist.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Commentaires {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String contenu;

    private Date date;
    @ManyToOne
    @JoinColumn(name = "auteur_commentaire")
    private User auteur;

    @ManyToOne
    @JoinColumn(name = "region_concerne")
    private Region region;
}
