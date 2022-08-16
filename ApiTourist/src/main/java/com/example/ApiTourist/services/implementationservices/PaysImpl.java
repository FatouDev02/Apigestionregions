package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Population;
import com.example.ApiTourist.repository.PaysRepository;
import com.example.ApiTourist.services.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaysImpl implements PaysService {
    @Autowired
    PaysRepository paysRepository;
    @Override
    public Pays Ajout(Pays pays) {

        return this.paysRepository.save(pays);
    }

    @Override
    public List<Pays> liste() {
        return this.paysRepository.findAll();

    }

    @Override
    public Pays Modifier(Pays pays,Long id_pays) {
        Pays pays1=this.paysRepository.findById(id_pays).orElseThrow();
        pays1.setNom_pays(pays.getNom_pays());

        return paysRepository.save(pays1);

    }
    @Override
    public String SupprimerbyId(Long id_pays) {
        this.paysRepository.deleteById(id_pays);
        return "suppppriméeeeeeeeeee";
    }
}
