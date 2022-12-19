package com.example.ApiTourist.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Activites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titre;
    private String img;
    private String description;



}
