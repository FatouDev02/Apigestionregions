package com.example.ApiTourist.controller;


import com.example.ApiTourist.config.ResponseMessage;
import com.example.ApiTourist.config.SaveImage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Population;
import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.repository.PaysRepository;
import com.example.ApiTourist.repository.PopulationRepository;
import com.example.ApiTourist.repository.RegionRepository;
import com.example.ApiTourist.services.PopulationService;
import com.example.ApiTourist.services.RegionService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
public class RegionController {
@Autowired
    PopulationService populationService;
    @Autowired
    RegionService regionService;
    @Autowired
    PaysRepository paysRepository;
    @Autowired
    RegionRepository regionRepository;
    //@ApiOperation(value = "Ajouter une region ")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    public ResponseEntity<Object> Createregion(@RequestParam(value = "region") String region,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
       Region region1=new JsonMapper().readValue(region,Region.class);
        Region find=regionService.findByNomm(region1.getNom());
        if(find==null){
            if (file != null){
                region1.setImg(SaveImage.save("region",file, region1.getNom()));
               // regionService.ajout(region1);
            }
            else {
                return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
            }

        }else{
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Une region existe déja avec le même nom !");

        }

        return ResponseMessage.generateResponse("error", HttpStatus.OK, " Région enregistrée !");


    }

    @PostMapping("/add/{nompays}")
    public Object createregiontrue(/*@Param("coderegion") String coderegion,
                                   @Param("nom") String nom,
                                   @Param("domaineactivite") String domaineactivite,
                                   @Param("langue") String langue,
                                   @Param("Superficie") String Superficie,
                                   @Param("description") String description,
                                   @Param("file") MultipartFile file,
                                   @Param("Chiffre") String Chiffre,
                                   @Param("annee") Long annee,*/
            @PathVariable String nompays,
            @RequestParam(value = "population",required = true) String population,
            @RequestParam(value = "region",required = true) String region,
            @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        Region region1 = new JsonMapper().readValue(region,Region.class);

        Population habitant1 = new JsonMapper().readValue(population,Population.class);
        //region1.setNom(region1.getNom());
        //habitant1.setRegion(region1);
        Population p= populationService.ajout(habitant1);
        region1.getPopulations().add(p);
        if (file != null){
            region1.setImg(SaveImage.save("region",file, region1.getNom()));
           return regionService.ajout(region1,nompays);
           // return populationService.ajout(habitant1);
        }
        else {
            return ResponseMessage.generateResponse("error", HttpStatus.OK, "Fichier vide");
        }

    }

    @GetMapping("/listregionbypays/{id}")
    public List<Region> listeparrpays(@PathVariable  Long id){
        Pays pfind=paysRepository.findById(id).get();
        List<Region> newregion = new ArrayList<>();
        List<Region> allregion=regionRepository.findAll();
        for (Region r: allregion ){
            try{
                if(r.getPays().getId().equals(pfind.getId())){
                    newregion.add(r);
                }
            }catch (Exception e){

            }
        }
        return newregion;
    }

    @GetMapping("/get/{id}")
    public Region get(Long id){
        return regionRepository.findById(id).get();
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
