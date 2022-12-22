package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Population;
import com.example.ApiTourist.repository.PopulationRepository;
import com.example.ApiTourist.services.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PopulationImpl implements PopulationService {
    @Autowired
    PopulationRepository populationRepository;
    @Override
    public Population ajout(Population population) {

        return populationRepository.save(population);
    }

    @Override
    public List<Population> liste() {

        return populationRepository.findAll();
    }

    @Override
    public Population findbyannnee(Long annee) {
        return populationRepository.findByAnnee(annee);
    }


    @Override
    public Population Modifier(Population population, Long id_population) {
        Population p=this.populationRepository.findById(id_population).orElseThrow();
        p.setChiffre(population.getChiffre());
        p.setAnnee(population.getAnnee());
        return populationRepository.save(p);
    }

    @Override
    public String SupprimerbyId(Long id_population) {
        this.populationRepository.deleteById(id_population);
        return "Pop supprimer";
    }
}
