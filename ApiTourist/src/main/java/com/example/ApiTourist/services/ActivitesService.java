package com.example.ApiTourist.services;


import com.example.ApiTourist.model.Activites;
import com.example.ApiTourist.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitesService extends JpaRepository<Activites,Long> {
}
