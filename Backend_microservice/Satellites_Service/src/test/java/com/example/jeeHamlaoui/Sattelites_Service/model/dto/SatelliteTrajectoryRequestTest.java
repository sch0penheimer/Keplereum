package com.example.jeeHamlaoui.Sattelites_Service.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SatelliteTrajectoryRequestTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteTrajectoryRequest#SatelliteTrajectoryRequest()}
   *   <li>{@link SatelliteTrajectoryRequest#setChangeReason(String)}
   *   <li>{@link SatelliteTrajectoryRequest#setEndTime(Instant)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitArgumentOfPerigee(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitEccentricity(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitInclination(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitMeanAnomaly(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitPeriapsis(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitRightAscension(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setSatelliteId(Long)}
   *   <li>{@link SatelliteTrajectoryRequest#setStartTime(Instant)}
   *   <li>{@link SatelliteTrajectoryRequest#setStatus(String)}
   *   <li>{@link SatelliteTrajectoryRequest#getChangeReason()}
   *   <li>{@link SatelliteTrajectoryRequest#getEndTime()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitArgumentOfPerigee()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitEccentricity()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitInclination()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitMeanAnomaly()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitPeriapsis()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitRightAscension()}
   *   <li>{@link SatelliteTrajectoryRequest#getSatelliteId()}
   *   <li>{@link SatelliteTrajectoryRequest#getStartTime()}
   *   <li>{@link SatelliteTrajectoryRequest#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteTrajectoryRequest.<init>()",
      "void SatelliteTrajectoryRequest.<init>(String, Long, String, Double, Double, Double, Double, Double, Double, Instant, Instant)",
      "String SatelliteTrajectoryRequest.getChangeReason()", "Instant SatelliteTrajectoryRequest.getEndTime()",
      "Double SatelliteTrajectoryRequest.getOrbitArgumentOfPerigee()",
      "Double SatelliteTrajectoryRequest.getOrbitEccentricity()",
      "Double SatelliteTrajectoryRequest.getOrbitInclination()",
      "Double SatelliteTrajectoryRequest.getOrbitMeanAnomaly()",
      "Double SatelliteTrajectoryRequest.getOrbitPeriapsis()",
      "Double SatelliteTrajectoryRequest.getOrbitRightAscension()", "Long SatelliteTrajectoryRequest.getSatelliteId()",
      "Instant SatelliteTrajectoryRequest.getStartTime()", "String SatelliteTrajectoryRequest.getStatus()",
      "void SatelliteTrajectoryRequest.setChangeReason(String)", "void SatelliteTrajectoryRequest.setEndTime(Instant)",
      "void SatelliteTrajectoryRequest.setOrbitArgumentOfPerigee(Double)",
      "void SatelliteTrajectoryRequest.setOrbitEccentricity(Double)",
      "void SatelliteTrajectoryRequest.setOrbitInclination(Double)",
      "void SatelliteTrajectoryRequest.setOrbitMeanAnomaly(Double)",
      "void SatelliteTrajectoryRequest.setOrbitPeriapsis(Double)",
      "void SatelliteTrajectoryRequest.setOrbitRightAscension(Double)",
      "void SatelliteTrajectoryRequest.setSatelliteId(Long)", "void SatelliteTrajectoryRequest.setStartTime(Instant)",
      "void SatelliteTrajectoryRequest.setStatus(String)"})
  void testGettersAndSetters() {
    // Arrange and Act
    SatelliteTrajectoryRequest actualSatelliteTrajectoryRequest = new SatelliteTrajectoryRequest();
    actualSatelliteTrajectoryRequest.setChangeReason("Just cause");
    actualSatelliteTrajectoryRequest
        .setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryRequest.setOrbitArgumentOfPerigee(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitEccentricity(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitInclination(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitMeanAnomaly(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitPeriapsis(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitRightAscension(10.0d);
    actualSatelliteTrajectoryRequest.setSatelliteId(1L);
    actualSatelliteTrajectoryRequest
        .setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryRequest.setStatus("Status");
    String actualChangeReason = actualSatelliteTrajectoryRequest.getChangeReason();
    Instant actualEndTime = actualSatelliteTrajectoryRequest.getEndTime();
    Double actualOrbitArgumentOfPerigee = actualSatelliteTrajectoryRequest.getOrbitArgumentOfPerigee();
    Double actualOrbitEccentricity = actualSatelliteTrajectoryRequest.getOrbitEccentricity();
    Double actualOrbitInclination = actualSatelliteTrajectoryRequest.getOrbitInclination();
    Double actualOrbitMeanAnomaly = actualSatelliteTrajectoryRequest.getOrbitMeanAnomaly();
    Double actualOrbitPeriapsis = actualSatelliteTrajectoryRequest.getOrbitPeriapsis();
    Double actualOrbitRightAscension = actualSatelliteTrajectoryRequest.getOrbitRightAscension();
    Long actualSatelliteId = actualSatelliteTrajectoryRequest.getSatelliteId();
    Instant actualStartTime = actualSatelliteTrajectoryRequest.getStartTime();

    // Assert
    assertEquals("Just cause", actualChangeReason);
    assertEquals("Status", actualSatelliteTrajectoryRequest.getStatus());
    assertEquals(10.0d, actualOrbitArgumentOfPerigee.doubleValue());
    assertEquals(10.0d, actualOrbitEccentricity.doubleValue());
    assertEquals(10.0d, actualOrbitInclination.doubleValue());
    assertEquals(10.0d, actualOrbitMeanAnomaly.doubleValue());
    assertEquals(10.0d, actualOrbitPeriapsis.doubleValue());
    assertEquals(10.0d, actualOrbitRightAscension.doubleValue());
    assertEquals(1L, actualSatelliteId.longValue());
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, actualEndTime);
    assertSame(instant, actualStartTime);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Status}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteTrajectoryRequest#SatelliteTrajectoryRequest(String, Long, String, Double, Double, Double, Double, Double, Double, Instant, Instant)}
   *   <li>{@link SatelliteTrajectoryRequest#setChangeReason(String)}
   *   <li>{@link SatelliteTrajectoryRequest#setEndTime(Instant)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitArgumentOfPerigee(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitEccentricity(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitInclination(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitMeanAnomaly(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitPeriapsis(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setOrbitRightAscension(Double)}
   *   <li>{@link SatelliteTrajectoryRequest#setSatelliteId(Long)}
   *   <li>{@link SatelliteTrajectoryRequest#setStartTime(Instant)}
   *   <li>{@link SatelliteTrajectoryRequest#setStatus(String)}
   *   <li>{@link SatelliteTrajectoryRequest#getChangeReason()}
   *   <li>{@link SatelliteTrajectoryRequest#getEndTime()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitArgumentOfPerigee()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitEccentricity()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitInclination()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitMeanAnomaly()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitPeriapsis()}
   *   <li>{@link SatelliteTrajectoryRequest#getOrbitRightAscension()}
   *   <li>{@link SatelliteTrajectoryRequest#getSatelliteId()}
   *   <li>{@link SatelliteTrajectoryRequest#getStartTime()}
   *   <li>{@link SatelliteTrajectoryRequest#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'Status'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteTrajectoryRequest.<init>()",
      "void SatelliteTrajectoryRequest.<init>(String, Long, String, Double, Double, Double, Double, Double, Double, Instant, Instant)",
      "String SatelliteTrajectoryRequest.getChangeReason()", "Instant SatelliteTrajectoryRequest.getEndTime()",
      "Double SatelliteTrajectoryRequest.getOrbitArgumentOfPerigee()",
      "Double SatelliteTrajectoryRequest.getOrbitEccentricity()",
      "Double SatelliteTrajectoryRequest.getOrbitInclination()",
      "Double SatelliteTrajectoryRequest.getOrbitMeanAnomaly()",
      "Double SatelliteTrajectoryRequest.getOrbitPeriapsis()",
      "Double SatelliteTrajectoryRequest.getOrbitRightAscension()", "Long SatelliteTrajectoryRequest.getSatelliteId()",
      "Instant SatelliteTrajectoryRequest.getStartTime()", "String SatelliteTrajectoryRequest.getStatus()",
      "void SatelliteTrajectoryRequest.setChangeReason(String)", "void SatelliteTrajectoryRequest.setEndTime(Instant)",
      "void SatelliteTrajectoryRequest.setOrbitArgumentOfPerigee(Double)",
      "void SatelliteTrajectoryRequest.setOrbitEccentricity(Double)",
      "void SatelliteTrajectoryRequest.setOrbitInclination(Double)",
      "void SatelliteTrajectoryRequest.setOrbitMeanAnomaly(Double)",
      "void SatelliteTrajectoryRequest.setOrbitPeriapsis(Double)",
      "void SatelliteTrajectoryRequest.setOrbitRightAscension(Double)",
      "void SatelliteTrajectoryRequest.setSatelliteId(Long)", "void SatelliteTrajectoryRequest.setStartTime(Instant)",
      "void SatelliteTrajectoryRequest.setStatus(String)"})
  void testGettersAndSetters_whenStatus() {
    // Arrange
    Instant endTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    SatelliteTrajectoryRequest actualSatelliteTrajectoryRequest = new SatelliteTrajectoryRequest("Status", 1L,
        "Just cause", 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, endTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryRequest.setChangeReason("Just cause");
    actualSatelliteTrajectoryRequest
        .setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryRequest.setOrbitArgumentOfPerigee(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitEccentricity(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitInclination(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitMeanAnomaly(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitPeriapsis(10.0d);
    actualSatelliteTrajectoryRequest.setOrbitRightAscension(10.0d);
    actualSatelliteTrajectoryRequest.setSatelliteId(1L);
    actualSatelliteTrajectoryRequest
        .setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectoryRequest.setStatus("Status");
    String actualChangeReason = actualSatelliteTrajectoryRequest.getChangeReason();
    Instant actualEndTime = actualSatelliteTrajectoryRequest.getEndTime();
    Double actualOrbitArgumentOfPerigee = actualSatelliteTrajectoryRequest.getOrbitArgumentOfPerigee();
    Double actualOrbitEccentricity = actualSatelliteTrajectoryRequest.getOrbitEccentricity();
    Double actualOrbitInclination = actualSatelliteTrajectoryRequest.getOrbitInclination();
    Double actualOrbitMeanAnomaly = actualSatelliteTrajectoryRequest.getOrbitMeanAnomaly();
    Double actualOrbitPeriapsis = actualSatelliteTrajectoryRequest.getOrbitPeriapsis();
    Double actualOrbitRightAscension = actualSatelliteTrajectoryRequest.getOrbitRightAscension();
    Long actualSatelliteId = actualSatelliteTrajectoryRequest.getSatelliteId();
    Instant actualStartTime = actualSatelliteTrajectoryRequest.getStartTime();

    // Assert
    assertEquals("Just cause", actualChangeReason);
    assertEquals("Status", actualSatelliteTrajectoryRequest.getStatus());
    assertEquals(10.0d, actualOrbitArgumentOfPerigee.doubleValue());
    assertEquals(10.0d, actualOrbitEccentricity.doubleValue());
    assertEquals(10.0d, actualOrbitInclination.doubleValue());
    assertEquals(10.0d, actualOrbitMeanAnomaly.doubleValue());
    assertEquals(10.0d, actualOrbitPeriapsis.doubleValue());
    assertEquals(10.0d, actualOrbitRightAscension.doubleValue());
    assertEquals(1L, actualSatelliteId.longValue());
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, actualEndTime);
    assertSame(instant, actualStartTime);
  }
}
