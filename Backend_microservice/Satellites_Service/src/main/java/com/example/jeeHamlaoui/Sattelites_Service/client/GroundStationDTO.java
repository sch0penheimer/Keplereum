package com.example.jeeHamlaoui.Sattelites_Service.client;

public record GroundStationDTO
        (Long groundStation_id,
         String groundStation_Name,
         String groundStation_Email,
         Integer groundStation_AccesLevel,
         Double groundStation_Latitude,
         Double groundStation_Longitude,
         String groundStation_Description,
         Long userId) {

}
