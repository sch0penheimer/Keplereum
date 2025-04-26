package com.example.jeeHamlaoui.Sattelites_Service.service;


import com.example.jeeHamlaoui.Sattelites_Service.model.Satellite;
import com.example.jeeHamlaoui.Sattelites_Service.repository.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SatelliteService {

    private final SatelliteRepository satelliteRepository;

    @Autowired
    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public void save(Satellite satellite) {
        satelliteRepository.save(satellite);
    }
    public List<Satellite> getAll() {
        return satelliteRepository.findAll();
    }
    public Satellite getById(Long id) {
        return satelliteRepository.findById(id).orElse(null);
    }

}
