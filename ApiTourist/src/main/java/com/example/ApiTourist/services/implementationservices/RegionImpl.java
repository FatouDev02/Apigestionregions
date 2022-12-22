package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.config.ResponseMessage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.repository.PaysRepository;
import com.example.ApiTourist.repository.RegionRepository;
import com.example.ApiTourist.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionImpl implements RegionService {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    PaysRepository paysRepository;
    @Override
    public Object ajout(Region region, String nompays) {

        if(paysRepository.findByNompays(nompays)!=null){
            Pays find=paysRepository.findByNompays(nompays);
            region.setPays(find);
            // population.setAnnee(new Date());
            return regionRepository.save(region);

        }else{
            return ResponseMessage.generateResponse("error", HttpStatus.OK, " Cette rregion n'existe pas !");
        }

    }

    @Override
    public List<Object[]> lister() {
        return regionRepository.MesRegionsavecPays();
    }

    @Override
    public Region Modifier(Region region, Long id) {
        Region r = this.regionRepository.findById(id).orElseThrow();
        r.setNom(region.getNom());
        r.setCoderegion(region.getCoderegion());
        r.setLangue(region.getLangue());
        r.setSuperficie(region.getSuperficie());
        return regionRepository.save(r);
    }

    @Override
    public String SupprimerbyId(Long id) {
       this.regionRepository.deleteById(id);
       return "Region supprimée avec succes";
    }

    @Override
    public Region findByNomm(String nom) {
        return regionRepository.findByNom(nom);
    }

    @Override
    public Iterable<Object[]> listersanspays() {
        return regionRepository.MesRegionsansPays();
    }
}
