package com.example.jeeHamlaoui.Sattelites_Service.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.jeeHamlaoui.Sattelites_Service.model.Satellite;
import com.example.jeeHamlaoui.Sattelites_Service.model.SatelliteModel;
import com.example.jeeHamlaoui.Sattelites_Service.model.SatelliteTrajectory;
import com.example.jeeHamlaoui.Sattelites_Service.model.enumeration.SatelliteStatus;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SatelliteTrajectoryResponse.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class SatelliteTrajectoryResponseTest {
  @Autowired
  private SatelliteTrajectoryResponse satelliteTrajectoryResponse;

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteTrajectoryResponse#SatelliteTrajectoryResponse()}
   *   <li>{@link SatelliteTrajectoryResponse#setChangeReason(String)}
   *   <li>{@link SatelliteTrajectoryResponse#setEndTime(Instant)}
   *   <li>{@link SatelliteTrajectoryResponse#setId(Long)}
   *   <li>{@link SatelliteTrajectoryResponse#setOrbitArgumentOfPerigee(Double)}
   *   <li>{@link SatelliteTrajectoryResponse#setOrbitEccentricity(Double)}
   *   <li>{@link SatelliteTrajectoryResponse#setOrbitInclination(Double)}
   *   <li>{@link SatelliteTrajectoryResponse#setOrbitMeanAnomaly(Double)}
   *   <li>{@link SatelliteTrajectoryResponse#setOrbitPeriapsis(Double)}
   *   <li>{@link SatelliteTrajectoryResponse#setOrbitRightAscension(Double)}
   *   <li>{@link SatelliteTrajectoryResponse#setSatelliteId(Long)}
   *   <li>{@link SatelliteTrajectoryResponse#setStartTime(Instant)}
   *   <li>{@link SatelliteTrajectoryResponse#setStatus(String)}
   *   <li>{@link SatelliteTrajectoryResponse#getChangeReason()}
   *   <li>{@link SatelliteTrajectoryResponse#getEndTime()}
   *   <li>{@link SatelliteTrajectoryResponse#getId()}
   *   <li>{@link SatelliteTrajectoryResponse#getOrbitArgumentOfPerigee()}
   *   <li>{@link SatelliteTrajectoryResponse#getOrbitEccentricity()}
   *   <li>{@link SatelliteTrajectoryResponse#getOrbitInclination()}
   *   <li>{@link SatelliteTrajectoryResponse#getOrbitMeanAnomaly()}
   *   <li>{@link SatelliteTrajectoryResponse#getOrbitPeriapsis()}
   *   <li>{@link SatelliteTrajectoryResponse#getOrbitRightAscension()}
   *   <li>{@link SatelliteTrajectoryResponse#getSatelliteId()}
   *   <li>{@link SatelliteTrajectoryResponse#getStartTime()}
   *   <li>{@link SatelliteTrajectoryResponse#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteTrajectoryResponse.<init>()",
      "String SatelliteTrajectoryResponse.getChangeReason()", "Instant SatelliteTrajectoryResponse.getEndTime()",
      "Long SatelliteTrajectoryResponse.getId()", "Double SatelliteTrajectoryResponse.getOrbitArgumentOfPerigee()",
      "Double SatelliteTrajectoryResponse.getOrbitEccentricity()",
      "Double SatelliteTrajectoryResponse.getOrbitInclination()",
      "Double SatelliteTrajectoryResponse.getOrbitMeanAnomaly()",
      "Double SatelliteTrajectoryResponse.getOrbitPeriapsis()",
      "Double SatelliteTrajectoryResponse.getOrbitRightAscension()",
      "Long SatelliteTrajectoryResponse.getSatelliteId()", "Instant SatelliteTrajectoryResponse.getStartTime()",
      "String SatelliteTrajectoryResponse.getStatus()", "void SatelliteTrajectoryResponse.setChangeReason(String)",
      "void SatelliteTrajectoryResponse.setEndTime(Instant)", "void SatelliteTrajectoryResponse.setId(Long)",
      "void SatelliteTrajectoryResponse.setOrbitArgumentOfPerigee(Double)",
      "void SatelliteTrajectoryResponse.setOrbitEccentricity(Double)",
      "void SatelliteTrajectoryResponse.setOrbitInclination(Double)",
      "void SatelliteTrajectoryResponse.setOrbitMeanAnomaly(Double)",
      "void SatelliteTrajectoryResponse.setOrbitPeriapsis(Double)",
      "void SatelliteTrajectoryResponse.setOrbitRightAscension(Double)",
      "void SatelliteTrajectoryResponse.setSatelliteId(Long)", "void SatelliteTrajectoryResponse.setStartTime(Instant)",
      "void SatelliteTrajectoryResponse.setStatus(String)"})
  void testGettersAndSetters() {
    // Arrange and Act
    SatelliteTrajectoryResponse actualSatelliteTrajectoryResponse = new SatelliteTrajectoryResponse();
    actualSatelliteTrajectoryResponse.setChangeReason("Just cause");
    actualSatelliteTrajectoryResponse
        .setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryResponse.setId(1L);
    actualSatelliteTrajectoryResponse.setOrbitArgumentOfPerigee(10.0d);
    actualSatelliteTrajectoryResponse.setOrbitEccentricity(10.0d);
    actualSatelliteTrajectoryResponse.setOrbitInclination(10.0d);
    actualSatelliteTrajectoryResponse.setOrbitMeanAnomaly(10.0d);
    actualSatelliteTrajectoryResponse.setOrbitPeriapsis(10.0d);
    actualSatelliteTrajectoryResponse.setOrbitRightAscension(10.0d);
    actualSatelliteTrajectoryResponse.setSatelliteId(1L);
    actualSatelliteTrajectoryResponse
        .setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryResponse.setStatus("Status");
    String actualChangeReason = actualSatelliteTrajectoryResponse.getChangeReason();
    Instant actualEndTime = actualSatelliteTrajectoryResponse.getEndTime();
    Long actualId = actualSatelliteTrajectoryResponse.getId();
    Double actualOrbitArgumentOfPerigee = actualSatelliteTrajectoryResponse.getOrbitArgumentOfPerigee();
    Double actualOrbitEccentricity = actualSatelliteTrajectoryResponse.getOrbitEccentricity();
    Double actualOrbitInclination = actualSatelliteTrajectoryResponse.getOrbitInclination();
    Double actualOrbitMeanAnomaly = actualSatelliteTrajectoryResponse.getOrbitMeanAnomaly();
    Double actualOrbitPeriapsis = actualSatelliteTrajectoryResponse.getOrbitPeriapsis();
    Double actualOrbitRightAscension = actualSatelliteTrajectoryResponse.getOrbitRightAscension();
    Long actualSatelliteId = actualSatelliteTrajectoryResponse.getSatelliteId();
    Instant actualStartTime = actualSatelliteTrajectoryResponse.getStartTime();

    // Assert
    assertEquals("Just cause", actualChangeReason);
    assertEquals("Status", actualSatelliteTrajectoryResponse.getStatus());
    assertEquals(10.0d, actualOrbitArgumentOfPerigee.doubleValue());
    assertEquals(10.0d, actualOrbitEccentricity.doubleValue());
    assertEquals(10.0d, actualOrbitInclination.doubleValue());
    assertEquals(10.0d, actualOrbitMeanAnomaly.doubleValue());
    assertEquals(10.0d, actualOrbitPeriapsis.doubleValue());
    assertEquals(10.0d, actualOrbitRightAscension.doubleValue());
    assertEquals(1L, actualId.longValue());
    assertEquals(1L, actualSatelliteId.longValue());
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, actualEndTime);
    assertSame(instant, actualStartTime);
  }

  /**
   * Test {@link SatelliteTrajectoryResponse#SatelliteTrajectoryResponse(SatelliteTrajectory)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then return SatelliteId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SatelliteTrajectoryResponse#SatelliteTrajectoryResponse(SatelliteTrajectory)}
   */
  @Test
  @DisplayName("Test new SatelliteTrajectoryResponse(SatelliteTrajectory); given 'null'; then return SatelliteId is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteTrajectoryResponse.<init>(SatelliteTrajectory)"})
  void testNewSatelliteTrajectoryResponse_givenNull_thenReturnSatelliteIdIsNull() {
    // Arrange
    SatelliteTrajectory trajectory = new SatelliteTrajectory();
    trajectory.setChangeReason("Just cause");
    trajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    trajectory.setId(1L);
    trajectory.setOrbitArgumentOfPerigee(10.0d);
    trajectory.setOrbitEccentricity(10.0d);
    trajectory.setOrbitInclination(10.0d);
    trajectory.setOrbitMeanAnomaly(10.0d);
    trajectory.setOrbitPeriapsis(10.0d);
    trajectory.setOrbitRightAscension(10.0d);
    trajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    trajectory.setStatus("Status");
    trajectory.setSatellite(null);

    // Act
    SatelliteTrajectoryResponse actualSatelliteTrajectoryResponse = new SatelliteTrajectoryResponse(trajectory);

    // Assert
    assertEquals("Just cause", actualSatelliteTrajectoryResponse.getChangeReason());
    assertEquals("Status", actualSatelliteTrajectoryResponse.getStatus());
    assertNull(actualSatelliteTrajectoryResponse.getSatelliteId());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitArgumentOfPerigee().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitEccentricity().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitInclination().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitMeanAnomaly().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitPeriapsis().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitRightAscension().doubleValue());
    assertEquals(1L, actualSatelliteTrajectoryResponse.getId().longValue());
  }

  /**
   * Test {@link SatelliteTrajectoryResponse#SatelliteTrajectoryResponse(SatelliteTrajectory)}.
   * <ul>
   *   <li>Then return SatelliteId longValue is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SatelliteTrajectoryResponse#SatelliteTrajectoryResponse(SatelliteTrajectory)}
   */
  @Test
  @DisplayName("Test new SatelliteTrajectoryResponse(SatelliteTrajectory); then return SatelliteId longValue is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteTrajectoryResponse.<init>(SatelliteTrajectory)"})
  void testNewSatelliteTrajectoryResponse_thenReturnSatelliteIdLongValueIsOne() {
    // Arrange
    SatelliteModel satelliteModel = new SatelliteModel();
    satelliteModel.setDesignTrajectoryPredictionFactor(10.0d);
    satelliteModel.setDimensions("Dimensions");
    satelliteModel.setDryMass(10.0d);
    satelliteModel.setExpectedLifespan(1);
    satelliteModel.setLaunchMass(10.0d);
    satelliteModel.setManufacturer("Manufacturer");
    satelliteModel.setName("Name");
    satelliteModel.setPowerCapacity(10.0d);
    satelliteModel.setWeight(10.0d);

    Satellite satellite = new Satellite();
    satellite.setGroundStationId(1L);
    satellite.setId(1L);
    satellite.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite.setModel(satelliteModel);
    satellite.setName("Name");
    satellite.setNetworkNodeId("42");
    satellite.setSatellite_id(1L);
    satellite.setSensors(new HashSet<>());
    satellite.setStatus(SatelliteStatus.LAUNCHED);
    satellite.setTrajectories(new HashSet<>());

    SatelliteTrajectory trajectory = new SatelliteTrajectory();
    trajectory.setChangeReason("Just cause");
    trajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    trajectory.setId(1L);
    trajectory.setOrbitArgumentOfPerigee(10.0d);
    trajectory.setOrbitEccentricity(10.0d);
    trajectory.setOrbitInclination(10.0d);
    trajectory.setOrbitMeanAnomaly(10.0d);
    trajectory.setOrbitPeriapsis(10.0d);
    trajectory.setOrbitRightAscension(10.0d);
    trajectory.setSatellite(satellite);
    trajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    trajectory.setStatus("Status");

    // Act
    SatelliteTrajectoryResponse actualSatelliteTrajectoryResponse = new SatelliteTrajectoryResponse(trajectory);

    // Assert
    assertEquals("Just cause", actualSatelliteTrajectoryResponse.getChangeReason());
    assertEquals("Status", actualSatelliteTrajectoryResponse.getStatus());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitArgumentOfPerigee().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitEccentricity().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitInclination().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitMeanAnomaly().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitPeriapsis().doubleValue());
    assertEquals(10.0d, actualSatelliteTrajectoryResponse.getOrbitRightAscension().doubleValue());
    assertEquals(1L, actualSatelliteTrajectoryResponse.getId().longValue());
    assertEquals(1L, actualSatelliteTrajectoryResponse.getSatelliteId().longValue());
  }
}
