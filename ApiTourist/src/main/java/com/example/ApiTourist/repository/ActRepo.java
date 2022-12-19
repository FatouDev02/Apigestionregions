package com.example.ApiTourist.repository;


import com.example.ApiTourist.model.Activites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepo extends JpaRepository<Activites,Long> {
    Activites findByTitre(String titre);

}
