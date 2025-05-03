package com.example.jeeHamlaoui.Sattelites_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "blockchain-service", url = "${application.config.blockchain-url}")
public interface NetworkNodeClient {
    @GetMapping("/network-nodes/{id}")
    NetworkNodeDTO getNetworkNodeById(@PathVariable String id);
}