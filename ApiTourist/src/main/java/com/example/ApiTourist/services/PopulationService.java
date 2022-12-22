package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Population;

import java.util.List;

public interface PopulationService  {
    Population ajout(Population population);

    List<Population> liste();
    Population findbyannnee(Long annee);

    Population Modifier(Population population, Long id_population);

    String SupprimerbyId(Long id_population);

}
