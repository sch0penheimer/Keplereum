package com.example.jeeHamlaoui.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;

@Entity
public class GroundStation {
    @Id
    private Long groundStation_id;
    private String groundStation_Name;

    @Email
    private String groundStation_Email;

    private Integer groundStation_AccesLevel;

    private Double groundStation_Latitude;
    private Double groundStation_Longitude;


    private String groundStation_Description;

    @OneToOne(mappedBy = "groundStation")
    private User user;
}
