package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays,Long>{
    Pays findByNom(String nom);
}
