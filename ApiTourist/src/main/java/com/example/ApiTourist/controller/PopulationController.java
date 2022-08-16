package com.example.ApiTourist.controller;

import com.example.ApiTourist.model.Population;
import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.services.PopulationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "hello", description = "Population's methods")
@RestController
@RequestMapping("/population")
@AllArgsConstructor
public class PopulationController {
    @Autowired
    PopulationService populationService;

    @ApiOperation(value = "Ajouter un chiffre de la population")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/

    public Population ajout(@RequestBody Population population){
        return this.populationService.ajout(population);
    }

    @ApiOperation(value = "Lister la table Population")
    @GetMapping("mylist")
    public List<Population> l(){

        return this.populationService.liste();
    }

    @ApiOperation(value = "Modifier la table,population")
    @PutMapping("/update/{id_population}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Population population,@PathVariable Long id_population){
        this.populationService.Modifier(population,id_population);
        return"mise à jour valider";
    }
    @ApiOperation(value = "supprimer un element dans la table population")
    @DeleteMapping("/delete/{id}")
    public String supp(@PathVariable Long id_population){
        this.populationService.SupprimerbyId(id_population);
        return"region supprimer avec Succès";

    }
}
