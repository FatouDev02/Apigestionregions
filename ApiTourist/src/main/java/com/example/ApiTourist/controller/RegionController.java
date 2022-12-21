package com.example.ApiTourist.controller;


import com.example.ApiTourist.config.ResponseMessage;
import com.example.ApiTourist.config.SaveImage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.services.RegionService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
/*RestController est utilisé pour créer des services
Web restful à l’aide de l’annotation,
il permet de gérer toutes les API REST telles que les requêtes GET, POST, Delete, PUT.*/

@RequestMapping("/ApiTourist/region")
/*Elle est utilisée pour traiter les requêtes HTTP avec des modèles d’URL spécifiés.
Il est utilisé dans et avec les @Controller et les @RestController.*/
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")

//@Api(value = "hello", description = "Methodes sur Region")
public class RegionController {

    @Autowired
    RegionService regionService;
    //@ApiOperation(value = "Ajouter une region ")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    public ResponseEntity<Object> Createregion(@RequestParam(value = "data") String region,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
       Region region1=null;
        Region find=regionService.findByNomm(region1.getNom());
        if(find==null){
            if (file != null){
                region1.setImg(SaveImage.save("region",file, region1.getNom()));
            }
            else {
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
            }
        }else{
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Une region existe déja avec le même nom !");

        }

        return ResponseMessage.generateResponse("error", HttpStatus.OK, " Région enregistrée !");


    }


  //  @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylist")
    public List<Object[]> l(){

        return regionService.lister();
    }
  //  @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylistwithoutp")
    public Iterable<Object[]> regionIterable(){
        return  regionService.listersanspays();
    }

   // @ApiOperation(value = "Modifier une region par Id")
    @PutMapping("/update/{id}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Region region,@PathVariable Long id){
        this.regionService.Modifier(region,id);
                return"mise à jour valider";
    }
   // @ApiOperation(value = "Supprimer une region par Id")
    @DeleteMapping("/delete/{id}")
    public String supp(@PathVariable Long id){
        this.regionService.SupprimerbyId(id);
        return"region supprimer avec Succès";

    }


}
