package com.example.ApiTourist.controller;

import com.example.ApiTourist.model.Population;
import com.example.ApiTourist.services.PopulationService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "hello", description = "Population's methods")
@RestController
@RequestMapping("/population")
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")

public class PopulationController {
    @Autowired
    PopulationService populationService;

  //  @ApiOperation(value = "Ajouter un chiffre de la population")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/

    public Population ajout(@RequestBody Population population){


            return populationService.ajout(population);

    }

 //   @ApiOperation(value = "Lister la table Population")
    @GetMapping("mylist")
    public List<Population> l(){

        return this.populationService.liste();
    }

   // @ApiOperation(value = "Modifier la table,population")
    @PutMapping("/update/{id_population}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Population population,@PathVariable Long id_population){
        this.populationService.Modifier(population,id_population);
        return"mise à jour valider";
    }
    //@ApiOperation(value = "supprimer un element dans la table population")
    @DeleteMapping("/delete/{id_population}")
    public String supp(@PathVariable Long id_population){
        this.populationService.SupprimerbyId(id_population);
        return"population supprimer avec Succès";

    }
}
