package com.example.ApiTourist.controller;


import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.services.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*RestController est utilisé pour créer des services
Web restful à l’aide de l’annotation,
il permet de gérer toutes les API REST telles que les requêtes GET, POST, Delete, PUT.*/

@RequestMapping("/ApiTourist/region")
/*Elle est utilisée pour traiter les requêtes HTTP avec des modèles d’URL spécifiés.
Il est utilisé dans et avec les @Controller et les @RestController.*/
@AllArgsConstructor

@Api(value = "hello", description = "Methodes sur Region")
public class RegionController {

    @Autowired
    RegionService regionService;
    @ApiOperation(value = "Ajouter une region ")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    public Region ajout(@RequestBody Region region){
        return regionService.ajout(region);


    }


    @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylist")
    public List<Object[]> l(){

        return regionService.lister();
    }
    @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylistwithoutp")
    public Iterable<Object[]> regionIterable(){
        return  regionService.listersanspays();
    }

    @ApiOperation(value = "Modifier une region par Id")
    @PutMapping("/update/{id}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Region region,@PathVariable Long id){
        this.regionService.Modifier(region,id);
                return"mise à jour valider";
    }
    @ApiOperation(value = "Supprimer une region par Id")
    @DeleteMapping("/delete/{id}")
    public String supp(@PathVariable Long id){
        this.regionService.SupprimerbyId(id);
        return"region supprimer avec Succès";

    }


}
