package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Pays;

import javax.swing.plaf.synth.Region;
import java.util.List;

public interface PaysService {

    Pays Ajout(Pays pays);
    List<Pays> lister();

    Pays RecupererParNom(String nom);
    Pays Modifier(Pays pays,Long id_pays);
    String SupprimerbyId(Long id_Pays);

}
