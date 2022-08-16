package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.repository.RegionRepository;
import com.example.ApiTourist.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionImpl implements RegionService {
    @Autowired
    RegionRepository regionRepository;
    @Override
    public Region ajout(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public List<Object[]> lister() {
        return regionRepository.MesRegionsavecPays();
    }

    @Override
    public Region Modifier(Region region, Long id) {
        Region r = this.regionRepository.findById(id).orElseThrow();
        r.setActivité(region.getActivité());
        r.setNomregion(region.getNomregion());
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
    public Iterable<Object[]> listersanspays() {
        return regionRepository.MesRegionsansPays();
    }
}
