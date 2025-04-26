package com.example.jeeHamlaoui.Sattelites_Service.repository;

import com.example.jeeHamlaoui.Sattelites_Service.model.SatelliteTrajectory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SatelliteTrajectory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SatelliteTrajectoryRepository extends JpaRepository<SatelliteTrajectory, Long> {}
