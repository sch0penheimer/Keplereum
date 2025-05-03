package com.example.jeeHamlaoui.Sattelites_Service.client;

import java.time.Instant;

public record NetworkNodeDTO(
        String publicKey,
        boolean authorityStatus,
        Integer blocksValidated,
        Instant lastActive
) {}
