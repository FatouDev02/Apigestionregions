package com.example.ApiTourist.controller;

import com.example.ApiTourist.config.ResponseMessage;
import com.example.ApiTourist.config.SaveImage;
import com.example.ApiTourist.model.Activites;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.services.ActivitesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/ApiTourist/activite")

public class ActivitController {
    @Autowired
    ActivitesService activitesService;

    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    public ResponseEntity<Object> Createpays(@RequestParam(value = "activite",required = true) String activite,
                                             @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {

        Activites activites=new JsonMapper().readValue(activite,Activites.class);
        Activites find=activitesService.RecupererPartitre(activites.getTitre());
        if(find==null){
            if (file != null){
                activites.setImg(SaveImage.save("pays",file, activites.getTitre()));
                activitesService.Ajout(activites);
                return ResponseMessage.generateResponse("ok", HttpStatus.OK, " Activité enregistrée !");

            }
            else {
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
            }
        }else{
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Une activité existe déja avec le même nom !");

        }

    }


    // @ApiOperation(value = "Lister les pays")
    @GetMapping("mylist")
    public List<Activites> l(){
        return this.activitesService.listeractivite();
    }
    @DeleteMapping("/supp/{id}")
    public String  delete(@PathVariable Long id){
        this.activitesService.SupprimerbyId(id);
        return"activité suppriméee";
    }

    //  @ApiOperation(value = "Modifier un pays par Id")
    @PutMapping("/update/{id}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Activites activites ,@PathVariable Long id){
        this.activitesService.Modifieractivite(activites,id);
        return"mise à jour valider";
    }

}
