package com.example.jeeHamlaoui.Sattelites_Service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
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

@ContextConfiguration(classes = {SatelliteTrajectory.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class SatelliteTrajectoryTest {
  @Autowired
  private SatelliteTrajectory satelliteTrajectory;

  /**
   * Test {@link SatelliteTrajectory#id(Long)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#id(Long)}
   */
  @Test
  @DisplayName("Test id(Long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.id(Long)"})
  void testId() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualIdResult = satelliteTrajectory2.id(1L);

    // Assert
    assertEquals(1L, satelliteTrajectory2.getId().longValue());
    assertSame(satelliteTrajectory2, actualIdResult);
  }

  /**
   * Test {@link SatelliteTrajectory#status(String)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#status(String)}
   */
  @Test
  @DisplayName("Test status(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.status(String)"})
  void testStatus() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualStatusResult = satelliteTrajectory2.status("Status");

    // Assert
    assertEquals("Status", satelliteTrajectory2.getStatus());
    assertSame(satelliteTrajectory2, actualStatusResult);
  }

  /**
   * Test {@link SatelliteTrajectory#startTime(Instant)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#startTime(Instant)}
   */
  @Test
  @DisplayName("Test startTime(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.startTime(Instant)"})
  void testStartTime() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertSame(satelliteTrajectory2, satelliteTrajectory2.startTime(startTime));
    Instant expectedStartTime = startTime.EPOCH;
    assertSame(expectedStartTime, satelliteTrajectory2.getStartTime());
  }

  /**
   * Test {@link SatelliteTrajectory#endTime(Instant)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#endTime(Instant)}
   */
  @Test
  @DisplayName("Test endTime(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.endTime(Instant)"})
  void testEndTime() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    Instant endTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertSame(satelliteTrajectory2, satelliteTrajectory2.endTime(endTime));
    Instant expectedEndTime = endTime.EPOCH;
    assertSame(expectedEndTime, satelliteTrajectory2.getEndTime());
  }

  /**
   * Test {@link SatelliteTrajectory#orbitEccentricity(Double)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#orbitEccentricity(Double)}
   */
  @Test
  @DisplayName("Test orbitEccentricity(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.orbitEccentricity(Double)"})
  void testOrbitEccentricity() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualOrbitEccentricityResult = satelliteTrajectory2.orbitEccentricity(10.0d);

    // Assert
    assertEquals(10.0d, satelliteTrajectory2.getOrbitEccentricity().doubleValue());
    assertSame(satelliteTrajectory2, actualOrbitEccentricityResult);
  }

  /**
   * Test {@link SatelliteTrajectory#orbitInclination(Double)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#orbitInclination(Double)}
   */
  @Test
  @DisplayName("Test orbitInclination(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.orbitInclination(Double)"})
  void testOrbitInclination() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualOrbitInclinationResult = satelliteTrajectory2.orbitInclination(10.0d);

    // Assert
    assertEquals(10.0d, satelliteTrajectory2.getOrbitInclination().doubleValue());
    assertSame(satelliteTrajectory2, actualOrbitInclinationResult);
  }

  /**
   * Test {@link SatelliteTrajectory#orbitRightAscension(Double)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#orbitRightAscension(Double)}
   */
  @Test
  @DisplayName("Test orbitRightAscension(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.orbitRightAscension(Double)"})
  void testOrbitRightAscension() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualOrbitRightAscensionResult = satelliteTrajectory2.orbitRightAscension(10.0d);

    // Assert
    assertEquals(10.0d, satelliteTrajectory2.getOrbitRightAscension().doubleValue());
    assertSame(satelliteTrajectory2, actualOrbitRightAscensionResult);
  }

  /**
   * Test {@link SatelliteTrajectory#orbitArgumentOfPerigee(Double)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#orbitArgumentOfPerigee(Double)}
   */
  @Test
  @DisplayName("Test orbitArgumentOfPerigee(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.orbitArgumentOfPerigee(Double)"})
  void testOrbitArgumentOfPerigee() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualOrbitArgumentOfPerigeeResult = satelliteTrajectory2.orbitArgumentOfPerigee(10.0d);

    // Assert
    assertEquals(10.0d, satelliteTrajectory2.getOrbitArgumentOfPerigee().doubleValue());
    assertSame(satelliteTrajectory2, actualOrbitArgumentOfPerigeeResult);
  }

  /**
   * Test {@link SatelliteTrajectory#orbitMeanAnomaly(Double)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#orbitMeanAnomaly(Double)}
   */
  @Test
  @DisplayName("Test orbitMeanAnomaly(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.orbitMeanAnomaly(Double)"})
  void testOrbitMeanAnomaly() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualOrbitMeanAnomalyResult = satelliteTrajectory2.orbitMeanAnomaly(10.0d);

    // Assert
    assertEquals(10.0d, satelliteTrajectory2.getOrbitMeanAnomaly().doubleValue());
    assertSame(satelliteTrajectory2, actualOrbitMeanAnomalyResult);
  }

  /**
   * Test {@link SatelliteTrajectory#orbitPeriapsis(Double)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#orbitPeriapsis(Double)}
   */
  @Test
  @DisplayName("Test orbitPeriapsis(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.orbitPeriapsis(Double)"})
  void testOrbitPeriapsis() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualOrbitPeriapsisResult = satelliteTrajectory2.orbitPeriapsis(10.0d);

    // Assert
    assertEquals(10.0d, satelliteTrajectory2.getOrbitPeriapsis().doubleValue());
    assertSame(satelliteTrajectory2, actualOrbitPeriapsisResult);
  }

  /**
   * Test {@link SatelliteTrajectory#changeReason(String)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#changeReason(String)}
   */
  @Test
  @DisplayName("Test changeReason(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.changeReason(String)"})
  void testChangeReason() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

    // Act
    SatelliteTrajectory actualChangeReasonResult = satelliteTrajectory2.changeReason("Just cause");

    // Assert
    assertEquals("Just cause", satelliteTrajectory2.getChangeReason());
    assertSame(satelliteTrajectory2, actualChangeReasonResult);
  }

  /**
   * Test {@link SatelliteTrajectory#satellite(Satellite)}.
   * <p>
   * Method under test: {@link SatelliteTrajectory#satellite(Satellite)}
   */
  @Test
  @DisplayName("Test satellite(Satellite)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SatelliteTrajectory SatelliteTrajectory.satellite(Satellite)"})
  void testSatellite() {
    // Arrange
    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();

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

    // Act
    SatelliteTrajectory actualSatelliteResult = satelliteTrajectory2.satellite(satellite);

    // Assert
    assertSame(satellite, satelliteTrajectory2.getSatellite());
    assertSame(satelliteTrajectory2, actualSatelliteResult);
  }

  /**
   * Test {@link SatelliteTrajectory#equals(Object)}, and {@link SatelliteTrajectory#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteTrajectory#equals(Object)}
   *   <li>{@link SatelliteTrajectory#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SatelliteTrajectory.equals(Object)", "int SatelliteTrajectory.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
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

    SatelliteTrajectory satelliteTrajectory = new SatelliteTrajectory();
    satelliteTrajectory.setChangeReason("Just cause");
    satelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setId(1L);
    satelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory.setOrbitEccentricity(10.0d);
    satelliteTrajectory.setOrbitInclination(10.0d);
    satelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory.setOrbitPeriapsis(10.0d);
    satelliteTrajectory.setOrbitRightAscension(10.0d);
    satelliteTrajectory.setSatellite(satellite);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(10.0d);
    satelliteModel2.setDimensions("Dimensions");
    satelliteModel2.setDryMass(10.0d);
    satelliteModel2.setExpectedLifespan(1);
    satelliteModel2.setLaunchMass(10.0d);
    satelliteModel2.setManufacturer("Manufacturer");
    satelliteModel2.setName("Name");
    satelliteModel2.setPowerCapacity(10.0d);
    satelliteModel2.setWeight(10.0d);

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel2);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    satelliteTrajectory2.setChangeReason("Just cause");
    satelliteTrajectory2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setId(1L);
    satelliteTrajectory2.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory2.setOrbitEccentricity(10.0d);
    satelliteTrajectory2.setOrbitInclination(10.0d);
    satelliteTrajectory2.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory2.setOrbitPeriapsis(10.0d);
    satelliteTrajectory2.setOrbitRightAscension(10.0d);
    satelliteTrajectory2.setSatellite(satellite2);
    satelliteTrajectory2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setStatus("Status");

    // Act and Assert
    assertEquals(satelliteTrajectory, satelliteTrajectory2);
    int expectedHashCodeResult = satelliteTrajectory.hashCode();
    assertEquals(expectedHashCodeResult, satelliteTrajectory2.hashCode());
  }

  /**
   * Test {@link SatelliteTrajectory#equals(Object)}, and {@link SatelliteTrajectory#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteTrajectory#equals(Object)}
   *   <li>{@link SatelliteTrajectory#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SatelliteTrajectory.equals(Object)", "int SatelliteTrajectory.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
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

    SatelliteTrajectory satelliteTrajectory = new SatelliteTrajectory();
    satelliteTrajectory.setChangeReason("Just cause");
    satelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setId(1L);
    satelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory.setOrbitEccentricity(10.0d);
    satelliteTrajectory.setOrbitInclination(10.0d);
    satelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory.setOrbitPeriapsis(10.0d);
    satelliteTrajectory.setOrbitRightAscension(10.0d);
    satelliteTrajectory.setSatellite(satellite);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    // Act and Assert
    assertEquals(satelliteTrajectory, satelliteTrajectory);
    int expectedHashCodeResult = satelliteTrajectory.hashCode();
    assertEquals(expectedHashCodeResult, satelliteTrajectory.hashCode());
  }

  /**
   * Test {@link SatelliteTrajectory#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SatelliteTrajectory#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SatelliteTrajectory.equals(Object)", "int SatelliteTrajectory.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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

    SatelliteTrajectory satelliteTrajectory = new SatelliteTrajectory();
    satelliteTrajectory.setChangeReason("Just cause");
    satelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setId(2L);
    satelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory.setOrbitEccentricity(10.0d);
    satelliteTrajectory.setOrbitInclination(10.0d);
    satelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory.setOrbitPeriapsis(10.0d);
    satelliteTrajectory.setOrbitRightAscension(10.0d);
    satelliteTrajectory.setSatellite(satellite);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(10.0d);
    satelliteModel2.setDimensions("Dimensions");
    satelliteModel2.setDryMass(10.0d);
    satelliteModel2.setExpectedLifespan(1);
    satelliteModel2.setLaunchMass(10.0d);
    satelliteModel2.setManufacturer("Manufacturer");
    satelliteModel2.setName("Name");
    satelliteModel2.setPowerCapacity(10.0d);
    satelliteModel2.setWeight(10.0d);

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel2);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    satelliteTrajectory2.setChangeReason("Just cause");
    satelliteTrajectory2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setId(1L);
    satelliteTrajectory2.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory2.setOrbitEccentricity(10.0d);
    satelliteTrajectory2.setOrbitInclination(10.0d);
    satelliteTrajectory2.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory2.setOrbitPeriapsis(10.0d);
    satelliteTrajectory2.setOrbitRightAscension(10.0d);
    satelliteTrajectory2.setSatellite(satellite2);
    satelliteTrajectory2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setStatus("Status");

    // Act and Assert
    assertNotEquals(satelliteTrajectory, satelliteTrajectory2);
  }

  /**
   * Test {@link SatelliteTrajectory#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SatelliteTrajectory#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SatelliteTrajectory.equals(Object)", "int SatelliteTrajectory.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
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

    SatelliteTrajectory satelliteTrajectory = new SatelliteTrajectory();
    satelliteTrajectory.setChangeReason("Just cause");
    satelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setId(null);
    satelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory.setOrbitEccentricity(10.0d);
    satelliteTrajectory.setOrbitInclination(10.0d);
    satelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory.setOrbitPeriapsis(10.0d);
    satelliteTrajectory.setOrbitRightAscension(10.0d);
    satelliteTrajectory.setSatellite(satellite);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(10.0d);
    satelliteModel2.setDimensions("Dimensions");
    satelliteModel2.setDryMass(10.0d);
    satelliteModel2.setExpectedLifespan(1);
    satelliteModel2.setLaunchMass(10.0d);
    satelliteModel2.setManufacturer("Manufacturer");
    satelliteModel2.setName("Name");
    satelliteModel2.setPowerCapacity(10.0d);
    satelliteModel2.setWeight(10.0d);

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel2);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    satelliteTrajectory2.setChangeReason("Just cause");
    satelliteTrajectory2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setId(1L);
    satelliteTrajectory2.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory2.setOrbitEccentricity(10.0d);
    satelliteTrajectory2.setOrbitInclination(10.0d);
    satelliteTrajectory2.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory2.setOrbitPeriapsis(10.0d);
    satelliteTrajectory2.setOrbitRightAscension(10.0d);
    satelliteTrajectory2.setSatellite(satellite2);
    satelliteTrajectory2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setStatus("Status");

    // Act and Assert
    assertNotEquals(satelliteTrajectory, satelliteTrajectory2);
  }

  /**
   * Test {@link SatelliteTrajectory#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SatelliteTrajectory#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SatelliteTrajectory.equals(Object)", "int SatelliteTrajectory.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
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

    SatelliteTrajectory satelliteTrajectory = new SatelliteTrajectory();
    satelliteTrajectory.setChangeReason("Just cause");
    satelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setId(1L);
    satelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory.setOrbitEccentricity(10.0d);
    satelliteTrajectory.setOrbitInclination(10.0d);
    satelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory.setOrbitPeriapsis(10.0d);
    satelliteTrajectory.setOrbitRightAscension(10.0d);
    satelliteTrajectory.setSatellite(satellite);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    // Act and Assert
    assertNotEquals(satelliteTrajectory, null);
  }

  /**
   * Test {@link SatelliteTrajectory#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SatelliteTrajectory#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SatelliteTrajectory.equals(Object)", "int SatelliteTrajectory.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
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

    SatelliteTrajectory satelliteTrajectory = new SatelliteTrajectory();
    satelliteTrajectory.setChangeReason("Just cause");
    satelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setId(1L);
    satelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    satelliteTrajectory.setOrbitEccentricity(10.0d);
    satelliteTrajectory.setOrbitInclination(10.0d);
    satelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    satelliteTrajectory.setOrbitPeriapsis(10.0d);
    satelliteTrajectory.setOrbitRightAscension(10.0d);
    satelliteTrajectory.setSatellite(satellite);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    // Act and Assert
    assertNotEquals(satelliteTrajectory, "Different type to SatelliteTrajectory");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link SatelliteTrajectory}
   *   <li>{@link SatelliteTrajectory#setChangeReason(String)}
   *   <li>{@link SatelliteTrajectory#setEndTime(Instant)}
   *   <li>{@link SatelliteTrajectory#setId(Long)}
   *   <li>{@link SatelliteTrajectory#setOrbitArgumentOfPerigee(Double)}
   *   <li>{@link SatelliteTrajectory#setOrbitEccentricity(Double)}
   *   <li>{@link SatelliteTrajectory#setOrbitInclination(Double)}
   *   <li>{@link SatelliteTrajectory#setOrbitMeanAnomaly(Double)}
   *   <li>{@link SatelliteTrajectory#setOrbitPeriapsis(Double)}
   *   <li>{@link SatelliteTrajectory#setOrbitRightAscension(Double)}
   *   <li>{@link SatelliteTrajectory#setSatellite(Satellite)}
   *   <li>{@link SatelliteTrajectory#setStartTime(Instant)}
   *   <li>{@link SatelliteTrajectory#setStatus(String)}
   *   <li>{@link SatelliteTrajectory#toString()}
   *   <li>{@link SatelliteTrajectory#getChangeReason()}
   *   <li>{@link SatelliteTrajectory#getEndTime()}
   *   <li>{@link SatelliteTrajectory#getId()}
   *   <li>{@link SatelliteTrajectory#getOrbitArgumentOfPerigee()}
   *   <li>{@link SatelliteTrajectory#getOrbitEccentricity()}
   *   <li>{@link SatelliteTrajectory#getOrbitInclination()}
   *   <li>{@link SatelliteTrajectory#getOrbitMeanAnomaly()}
   *   <li>{@link SatelliteTrajectory#getOrbitPeriapsis()}
   *   <li>{@link SatelliteTrajectory#getOrbitRightAscension()}
   *   <li>{@link SatelliteTrajectory#getSatellite()}
   *   <li>{@link SatelliteTrajectory#getStartTime()}
   *   <li>{@link SatelliteTrajectory#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteTrajectory.<init>()", "String SatelliteTrajectory.getChangeReason()",
      "Instant SatelliteTrajectory.getEndTime()", "Long SatelliteTrajectory.getId()",
      "Double SatelliteTrajectory.getOrbitArgumentOfPerigee()", "Double SatelliteTrajectory.getOrbitEccentricity()",
      "Double SatelliteTrajectory.getOrbitInclination()", "Double SatelliteTrajectory.getOrbitMeanAnomaly()",
      "Double SatelliteTrajectory.getOrbitPeriapsis()", "Double SatelliteTrajectory.getOrbitRightAscension()",
      "Satellite SatelliteTrajectory.getSatellite()", "Instant SatelliteTrajectory.getStartTime()",
      "String SatelliteTrajectory.getStatus()", "void SatelliteTrajectory.setChangeReason(String)",
      "void SatelliteTrajectory.setEndTime(Instant)", "void SatelliteTrajectory.setId(Long)",
      "void SatelliteTrajectory.setOrbitArgumentOfPerigee(Double)",
      "void SatelliteTrajectory.setOrbitEccentricity(Double)", "void SatelliteTrajectory.setOrbitInclination(Double)",
      "void SatelliteTrajectory.setOrbitMeanAnomaly(Double)", "void SatelliteTrajectory.setOrbitPeriapsis(Double)",
      "void SatelliteTrajectory.setOrbitRightAscension(Double)", "void SatelliteTrajectory.setSatellite(Satellite)",
      "void SatelliteTrajectory.setStartTime(Instant)", "void SatelliteTrajectory.setStatus(String)",
      "String SatelliteTrajectory.toString()"})
  void testGettersAndSetters() {
    // Arrange and Act
    SatelliteTrajectory actualSatelliteTrajectory = new SatelliteTrajectory();
    actualSatelliteTrajectory.setChangeReason("Just cause");
    actualSatelliteTrajectory.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectory.setId(1L);
    actualSatelliteTrajectory.setOrbitArgumentOfPerigee(10.0d);
    actualSatelliteTrajectory.setOrbitEccentricity(10.0d);
    actualSatelliteTrajectory.setOrbitInclination(10.0d);
    actualSatelliteTrajectory.setOrbitMeanAnomaly(10.0d);
    actualSatelliteTrajectory.setOrbitPeriapsis(10.0d);
    actualSatelliteTrajectory.setOrbitRightAscension(10.0d);
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
    actualSatelliteTrajectory.setSatellite(satellite);
    actualSatelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualSatelliteTrajectory.setStatus("Status");
    String actualToStringResult = actualSatelliteTrajectory.toString();
    String actualChangeReason = actualSatelliteTrajectory.getChangeReason();
    Instant actualEndTime = actualSatelliteTrajectory.getEndTime();
    Long actualId = actualSatelliteTrajectory.getId();
    Double actualOrbitArgumentOfPerigee = actualSatelliteTrajectory.getOrbitArgumentOfPerigee();
    Double actualOrbitEccentricity = actualSatelliteTrajectory.getOrbitEccentricity();
    Double actualOrbitInclination = actualSatelliteTrajectory.getOrbitInclination();
    Double actualOrbitMeanAnomaly = actualSatelliteTrajectory.getOrbitMeanAnomaly();
    Double actualOrbitPeriapsis = actualSatelliteTrajectory.getOrbitPeriapsis();
    Double actualOrbitRightAscension = actualSatelliteTrajectory.getOrbitRightAscension();
    Satellite actualSatellite = actualSatelliteTrajectory.getSatellite();
    Instant actualStartTime = actualSatelliteTrajectory.getStartTime();

    // Assert
    assertEquals("Just cause", actualChangeReason);
    assertEquals(
        "SatelliteTrajectory{id=1, status='Status', startTime='1970-01-01T00:00:00Z', endTime='1970-01-01T00:00:00Z',"
            + " orbitEccentricity=10.0, orbitInclination=10.0, orbitRightAscension=10.0, orbitArgumentOfPerigee=10.0,"
            + " orbitMeanAnomaly=10.0, orbitPeriapsis=10.0, changeReason='Just cause'}",
        actualToStringResult);
    assertEquals("Status", actualSatelliteTrajectory.getStatus());
    assertEquals(10.0d, actualOrbitArgumentOfPerigee.doubleValue());
    assertEquals(10.0d, actualOrbitEccentricity.doubleValue());
    assertEquals(10.0d, actualOrbitInclination.doubleValue());
    assertEquals(10.0d, actualOrbitMeanAnomaly.doubleValue());
    assertEquals(10.0d, actualOrbitPeriapsis.doubleValue());
    assertEquals(10.0d, actualOrbitRightAscension.doubleValue());
    assertEquals(1L, actualId.longValue());
    assertSame(satellite, actualSatellite);
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, actualEndTime);
    assertSame(instant, actualStartTime);
  }
}
