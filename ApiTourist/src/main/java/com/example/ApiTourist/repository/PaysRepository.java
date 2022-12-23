package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays,Long>{
    Pays findByNompays(String nompays);

    @Query(value = "SELECT count(pays_id) from region r where r.pays_id=:x", nativeQuery = true)
    public Long Total(@Param("x") Long id);

}
