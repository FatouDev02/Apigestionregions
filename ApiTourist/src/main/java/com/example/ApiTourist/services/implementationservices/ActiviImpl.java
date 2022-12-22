package com.example.ApiTourist.services.implementationservices;


import com.example.ApiTourist.model.Activites;
import com.example.ApiTourist.repository.ActRepo;
import com.example.ApiTourist.services.ActivitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActiviImpl implements ActivitesService {
    @Autowired
    ActRepo actRepo;
    @Override
    public Activites Ajout(Activites activites) {
        return actRepo.save(activites);
    }

    @Override
    public List<Activites> listeractivite() {
        return actRepo.findAll();
    }

    @Override
    public Activites RecupererPartitre(String titre) {
        return actRepo.findByTitre(titre);
    }

    @Override
    public Activites Modifieractivite(Activites activites, Long id) {
        return actRepo.findById(id)
                .map(activites1 -> {
                    activites1.setTitre(activites.getTitre());
                    activites1.setDescription(activites.getDescription());
                    activites1.setImg(activites.getImg());

                    return  actRepo.save(activites);
                }).orElseThrow(() -> new RuntimeException("Activité non trouvéeeeee"));
    }

    @Override
    public String SupprimerbyId(Long id) {
        this.actRepo.deleteById(id);
        return  "Activité supprimée!!!";
    }
}
