package com.example.ApiTourist.services.implementationservices;

import com.example.ApiTourist.model.Pays;
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

        return paysRepository.save(pays);
    }

    @Override
    public List<Pays> lister() {
        return this.paysRepository.findAll();

    }

    @Override
    public Pays RecupererParNom(String nompays) {
        return paysRepository.findByNompays(nompays);
    }

    @Override
    public Pays Modifier(Pays pays,Long id) {
        return paysRepository.findById(id)
                .map(pays1 -> {
                        pays1.setNompays(pays.getNompays());
                        pays1.setCapitale(pays.getCapitale());
                    pays1.setDescription(pays.getDescription());
                    pays1.setDrapeau(pays.getDrapeau());
                    pays1.setSuperficie(pays.getSuperficie());
                    return  paysRepository.save(pays);
                }).orElseThrow(() -> new RuntimeException("Pays non trouvéé"));

    }

    public Pays getbyId(Long id){
        return paysRepository.findById(id).get();
    }


    @Override
    public String SupprimerbyId(Long id_pays) {
        this.paysRepository.deleteById(id_pays);
        return "suppppriméeeeeeeeeee";
    }
}
