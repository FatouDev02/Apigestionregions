package com.example.ApiTourist.services;

import com.example.ApiTourist.model.Region;

import java.util.List;

public interface RegionService {
    Region ajout(Region region);

    List<Object[]> lister();
    Region Modifier(Region region , Long id);
    String SupprimerbyId(Long id);

    Iterable<Object[]> listersanspays();
}
