package com.example.jeeHamlaoui.Sattelites_Service.controller;


import com.example.jeeHamlaoui.Sattelites_Service.model.Satellite;
import com.example.jeeHamlaoui.Sattelites_Service.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/satellites")
public class SatelliteController {

    private SatelliteService satelliteService;

    @Autowired
    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Satellite satellite){
        satelliteService.save(satellite);
    }

    @GetMapping
    public ResponseEntity<List<Satellite>> getAllSatellites(){
        return ResponseEntity.ok(satelliteService.getAll());
    }

    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudent(
            @PathVariable("school-id") Integer schoolId
    ){
        return ResponseEntity.ok(service.findAllStudentsBySchoolId(schoolId));
    }

}
