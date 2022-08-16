package com.example.ApiTourist.controller;

import com.example.ApiTourist.model.ErrorMessage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Population;
import com.example.ApiTourist.services.PaysService;
import com.example.ApiTourist.services.PopulationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Object ajout(@RequestBody Pays pays){
        try {
            return  paysService.Ajout(pays);
        }catch (Exception e){
            return  ErrorMessage.ErreurReponse("Pays  existe deja!", HttpStatus.OK,null);
        }

    }
    @ApiOperation(value = "Lister les pays")
    @GetMapping("mylist")
    public List<Pays> l(){
        return this.paysService.liste();
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
        return"region supprimer avec Succès";

    }
}
