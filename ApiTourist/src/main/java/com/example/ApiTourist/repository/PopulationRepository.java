package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.PipedOutputStream;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {
    Population findByAnnee(Long annee);
}
