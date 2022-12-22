package com.example.ApiTourist.services;


import com.example.ApiTourist.model.Activites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivitesService  {
    Activites Ajout(Activites activites);
    List<Activites> listeractivite();

    Activites RecupererPartitre(String nom);
    Activites Modifieractivite(Activites activites,Long id);
    String SupprimerbyId(Long id);
}
