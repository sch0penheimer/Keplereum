package com.example.jeeHamlaoui.Sattelites_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// GroundStationClient.java
@FeignClient(name = "groundstation-service", url = "${application.config.groundStation-url}")
public interface GroundStationClient {
    @GetMapping("/api/groundstations/{id}")
    GroundStationDTO getGroundStationById(@PathVariable Long id);
}


