package com.example.ApiTourist.controller;

import com.example.ApiTourist.config.ResponseMessage;
import com.example.ApiTourist.config.SaveImage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.services.PaysService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pays")
@AllArgsConstructor

@Api(value = "hello", description = "Methodes pour  Pays")
public class PaysController{
    @Autowired
    PaysService paysService;

    @ApiOperation(value = "Ajouter un Pays")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    public ResponseEntity<Object> Createactivite(@RequestParam(value = "data") String pays,

                                                 @RequestParam(value = "user") String userVenant,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        Pays pays1= null;

        Pays find=paysService.RecupererParNom(pays1.getNom());
        if(find==null){
            if (file != null){
                pays1.setDrapeau(SaveImage.save("pays",file, pays1.getNom()));
            }
            else {
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
            }
        }else{
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Un Pays existe déja avec le même nom !");

        }
    }
    @ApiOperation(value = "Lister les pays")
    @GetMapping("mylist")
    public List<Pays> l(){
        return this.paysService.lister();
    }

    @ApiOperation(value = "Modifier un pays par Id")
    @PutMapping("/update/{id_pays}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Pays pays,@PathVariable Long id_pays){
        this.paysService.Modifier(pays,id_pays);
        return"mise à jour valider";
    }

    @ApiOperation(value = "Supprimer un pays pa ID")
    @DeleteMapping("/delete/{id_pays}")
    public String supp(@PathVariable Long id_pays){
        this.paysService.SupprimerbyId(id_pays);
        return"Pays supprimer avec Succès";

    }
}
