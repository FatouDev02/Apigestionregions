package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {
}
