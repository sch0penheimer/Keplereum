package com.example.jeeHamlaoui.Sattelites_Service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.jeeHamlaoui.Sattelites_Service.model.enumeration.SatelliteStatus;
import com.example.jeeHamlaoui.Sattelites_Service.model.enumeration.SensorActivity;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Satellite.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class SatelliteTest {
  @Autowired
  private Satellite satellite;

  /**
   * Test {@link Satellite#satellite_id(Long)}.
   * <p>
   * Method under test: {@link Satellite#satellite_id(Long)}
   */
  @Test
  @DisplayName("Test satellite_id(Long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.satellite_id(Long)"})
  void testSatellite_id() {
    // Arrange
    Satellite satellite2 = new Satellite();

    // Act
    Satellite actualSatellite_idResult = satellite2.satellite_id(1L);

    // Assert
    assertEquals(1L, satellite2.getId().longValue());
    assertEquals(1L, satellite2.getSatellite_id().longValue());
    assertSame(satellite2, actualSatellite_idResult);
  }

  /**
   * Test {@link Satellite#name(String)}.
   * <p>
   * Method under test: {@link Satellite#name(String)}
   */
  @Test
  @DisplayName("Test name(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.name(String)"})
  void testName() {
    // Arrange
    Satellite satellite2 = new Satellite();

    // Act
    Satellite actualNameResult = satellite2.name("Name");

    // Assert
    assertEquals("Name", satellite2.getName());
    assertSame(satellite2, actualNameResult);
  }

  /**
   * Test {@link Satellite#launchDate(Instant)}.
   * <p>
   * Method under test: {@link Satellite#launchDate(Instant)}
   */
  @Test
  @DisplayName("Test launchDate(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.launchDate(Instant)"})
  void testLaunchDate() {
    // Arrange
    Satellite satellite2 = new Satellite();
    Instant launchDate = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertSame(satellite2, satellite2.launchDate(launchDate));
    Instant expectedLaunchDate = launchDate.EPOCH;
    assertSame(expectedLaunchDate, satellite2.getLaunchDate());
  }

  /**
   * Test {@link Satellite#status(SatelliteStatus)}.
   * <p>
   * Method under test: {@link Satellite#status(SatelliteStatus)}
   */
  @Test
  @DisplayName("Test status(SatelliteStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.status(SatelliteStatus)"})
  void testStatus() {
    // Arrange
    Satellite satellite2 = new Satellite();

    // Act
    Satellite actualStatusResult = satellite2.status(SatelliteStatus.LAUNCHED);

    // Assert
    assertEquals(SatelliteStatus.LAUNCHED, satellite2.getStatus());
    assertSame(satellite2, actualStatusResult);
  }

  /**
   * Test {@link Satellite#setSensors(Set)}.
   * <ul>
   *   <li>Given {@link SatelliteModel#SatelliteModel()} DesignTrajectoryPredictionFactor is {@code 0.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setSensors(Set)}
   */
  @Test
  @DisplayName("Test setSensors(Set); given SatelliteModel() DesignTrajectoryPredictionFactor is '0.5'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setSensors(Set)"})
  void testSetSensors_givenSatelliteModelDesignTrajectoryPredictionFactorIs05() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite2);
    sensor.setType("Type");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(0.5d);
    satelliteModel2.setDimensions("42");
    satelliteModel2.setDryMass(0.5d);
    satelliteModel2.setExpectedLifespan(0);
    satelliteModel2.setLaunchMass(0.5d);
    satelliteModel2.setManufacturer("42");
    satelliteModel2.setName("42");
    satelliteModel2.setPowerCapacity(0.5d);
    satelliteModel2.setWeight(0.5d);

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(2L);
    satellite3.setId(2L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel2);
    satellite3.setName("com.example.jeeHamlaoui.Sattelites_Service.model.Satellite");
    satellite3.setNetworkNodeId("Network Node Id");
    satellite3.setSatellite_id(2L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.OPERATIONAL);
    satellite3.setTrajectories(new HashSet<>());

    Sensor sensor2 = new Sensor();
    sensor2.setActivity(SensorActivity.INACTIVE);
    sensor2.setId(2L);
    sensor2.setMaxHeight(0.5d);
    sensor2.setMaxRadius(0.5d);
    sensor2.setSatellite(satellite3);
    sensor2.setType("com.example.jeeHamlaoui.Sattelites_Service.model.Sensor");

    Satellite satellite4 = new Satellite();
    satellite4.addSensor(sensor2);
    satellite4.addSensor(sensor);
    HashSet<Sensor> sensors = new HashSet<>();

    // Act
    satellite4.setSensors(sensors);

    // Assert
    assertSame(sensors, satellite4.getSensors());
  }

  /**
   * Test {@link Satellite#setSensors(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Sensors is {@link HashSet#HashSet()}.</li>
   *   <li>Then {@link Satellite} (default constructor) Sensors is {@link HashSet#HashSet()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setSensors(Set)}
   */
  @Test
  @DisplayName("Test setSensors(Set); given Satellite (default constructor) Sensors is HashSet(); then Satellite (default constructor) Sensors is HashSet()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setSensors(Set)"})
  void testSetSensors_givenSatelliteSensorsIsHashSet_thenSatelliteSensorsIsHashSet() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite2);
    sensor.setType("Type");

    Satellite satellite3 = new Satellite();
    satellite3.addSensor(sensor);
    HashSet<Sensor> sensors = new HashSet<>();

    // Act
    satellite3.setSensors(sensors);

    // Assert
    assertSame(sensors, satellite3.getSensors());
  }

  /**
   * Test {@link Satellite#setSensors(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Sensors is {@code null}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link Satellite} (default constructor) Sensors is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setSensors(Set)}
   */
  @Test
  @DisplayName("Test setSensors(Set); given Satellite (default constructor) Sensors is 'null'; when 'null'; then Satellite (default constructor) Sensors is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setSensors(Set)"})
  void testSetSensors_givenSatelliteSensorsIsNull_whenNull_thenSatelliteSensorsIsNull() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());
    satellite2.setSensors(null);

    // Act
    satellite2.setSensors(null);

    // Assert that nothing has changed
    assertNull(satellite2.getSensors());
  }

  /**
   * Test {@link Satellite#setSensors(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor).</li>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then {@link Satellite} (default constructor) Sensors is {@link HashSet#HashSet()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setSensors(Set)}
   */
  @Test
  @DisplayName("Test setSensors(Set); given Satellite (default constructor); when HashSet(); then Satellite (default constructor) Sensors is HashSet()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setSensors(Set)"})
  void testSetSensors_givenSatellite_whenHashSet_thenSatelliteSensorsIsHashSet() {
    // Arrange
    Satellite satellite2 = new Satellite();
    HashSet<Sensor> sensors = new HashSet<>();

    // Act
    satellite2.setSensors(sensors);

    // Assert
    assertSame(sensors, satellite2.getSensors());
  }

  /**
   * Test {@link Satellite#sensors(Set)}.
   * <ul>
   *   <li>Given {@link SatelliteModel#SatelliteModel()} DesignTrajectoryPredictionFactor is {@code 0.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#sensors(Set)}
   */
  @Test
  @DisplayName("Test sensors(Set); given SatelliteModel() DesignTrajectoryPredictionFactor is '0.5'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.sensors(Set)"})
  void testSensors_givenSatelliteModelDesignTrajectoryPredictionFactorIs05() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite2);
    sensor.setType("Type");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(0.5d);
    satelliteModel2.setDimensions("42");
    satelliteModel2.setDryMass(0.5d);
    satelliteModel2.setExpectedLifespan(0);
    satelliteModel2.setLaunchMass(0.5d);
    satelliteModel2.setManufacturer("42");
    satelliteModel2.setName("42");
    satelliteModel2.setPowerCapacity(0.5d);
    satelliteModel2.setWeight(0.5d);

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(2L);
    satellite3.setId(2L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel2);
    satellite3.setName("com.example.jeeHamlaoui.Sattelites_Service.model.Satellite");
    satellite3.setNetworkNodeId("Network Node Id");
    satellite3.setSatellite_id(2L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.OPERATIONAL);
    satellite3.setTrajectories(new HashSet<>());

    Sensor sensor2 = new Sensor();
    sensor2.setActivity(SensorActivity.INACTIVE);
    sensor2.setId(2L);
    sensor2.setMaxHeight(0.5d);
    sensor2.setMaxRadius(0.5d);
    sensor2.setSatellite(satellite3);
    sensor2.setType("com.example.jeeHamlaoui.Sattelites_Service.model.Sensor");

    Satellite satellite4 = new Satellite();
    satellite4.addSensor(sensor2);
    satellite4.addSensor(sensor);
    HashSet<Sensor> sensors = new HashSet<>();

    // Act
    Satellite actualSensorsResult = satellite4.sensors(sensors);

    // Assert
    assertNull(actualSensorsResult.getModel());
    assertNull(actualSensorsResult.getStatus());
    assertNull(actualSensorsResult.getGroundStationId());
    assertNull(actualSensorsResult.getId());
    assertNull(actualSensorsResult.getSatellite_id());
    assertNull(actualSensorsResult.getName());
    assertNull(actualSensorsResult.getNetworkNodeId());
    assertNull(actualSensorsResult.getLaunchDate());
    assertTrue(actualSensorsResult.getSensors().isEmpty());
    assertSame(sensors, satellite4.getSensors());
  }

  /**
   * Test {@link Satellite#sensors(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Sensors is {@link HashSet#HashSet()}.</li>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return Model is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#sensors(Set)}
   */
  @Test
  @DisplayName("Test sensors(Set); given Satellite (default constructor) Sensors is HashSet(); when HashSet(); then return Model is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.sensors(Set)"})
  void testSensors_givenSatelliteSensorsIsHashSet_whenHashSet_thenReturnModelIsNull() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite2);
    sensor.setType("Type");

    Satellite satellite3 = new Satellite();
    satellite3.addSensor(sensor);
    HashSet<Sensor> sensors = new HashSet<>();

    // Act
    Satellite actualSensorsResult = satellite3.sensors(sensors);

    // Assert
    assertNull(actualSensorsResult.getModel());
    assertNull(actualSensorsResult.getStatus());
    assertNull(actualSensorsResult.getGroundStationId());
    assertNull(actualSensorsResult.getId());
    assertNull(actualSensorsResult.getSatellite_id());
    assertNull(actualSensorsResult.getName());
    assertNull(actualSensorsResult.getNetworkNodeId());
    assertNull(actualSensorsResult.getLaunchDate());
    assertTrue(actualSensorsResult.getSensors().isEmpty());
    assertSame(sensors, satellite3.getSensors());
  }

  /**
   * Test {@link Satellite#sensors(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Sensors is {@code null}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return NetworkNodeId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#sensors(Set)}
   */
  @Test
  @DisplayName("Test sensors(Set); given Satellite (default constructor) Sensors is 'null'; when 'null'; then return NetworkNodeId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.sensors(Set)"})
  void testSensors_givenSatelliteSensorsIsNull_whenNull_thenReturnNetworkNodeIdIs42() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());
    satellite2.setSensors(null);

    // Act
    Satellite actualSensorsResult = satellite2.sensors(null);

    // Assert
    assertEquals("42", actualSensorsResult.getNetworkNodeId());
    assertEquals("Name", actualSensorsResult.getName());
    assertNull(satellite2.getSensors());
    assertNull(actualSensorsResult.getSensors());
    assertEquals(1L, actualSensorsResult.getGroundStationId().longValue());
    assertEquals(1L, actualSensorsResult.getId().longValue());
    assertEquals(1L, actualSensorsResult.getSatellite_id().longValue());
    assertEquals(SatelliteStatus.LAUNCHED, actualSensorsResult.getStatus());
    assertSame(satelliteModel, actualSensorsResult.getModel());
  }

  /**
   * Test {@link Satellite#sensors(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor).</li>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return Model is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#sensors(Set)}
   */
  @Test
  @DisplayName("Test sensors(Set); given Satellite (default constructor); when HashSet(); then return Model is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.sensors(Set)"})
  void testSensors_givenSatellite_whenHashSet_thenReturnModelIsNull() {
    // Arrange
    Satellite satellite2 = new Satellite();
    HashSet<Sensor> sensors = new HashSet<>();

    // Act
    Satellite actualSensorsResult = satellite2.sensors(sensors);

    // Assert
    assertNull(actualSensorsResult.getModel());
    assertNull(actualSensorsResult.getStatus());
    assertNull(actualSensorsResult.getGroundStationId());
    assertNull(actualSensorsResult.getId());
    assertNull(actualSensorsResult.getSatellite_id());
    assertNull(actualSensorsResult.getName());
    assertNull(actualSensorsResult.getNetworkNodeId());
    assertNull(actualSensorsResult.getLaunchDate());
    assertTrue(actualSensorsResult.getSensors().isEmpty());
    assertSame(sensors, satellite2.getSensors());
  }

  /**
   * Test {@link Satellite#addSensor(Sensor)}.
   * <p>
   * Method under test: {@link Satellite#addSensor(Sensor)}
   */
  @Test
  @DisplayName("Test addSensor(Sensor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.addSensor(Sensor)"})
  void testAddSensor() {
    // Arrange
    Satellite satellite2 = new Satellite();

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

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(1L);
    satellite3.setId(1L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel);
    satellite3.setName("Name");
    satellite3.setNetworkNodeId("42");
    satellite3.setSatellite_id(1L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.LAUNCHED);
    satellite3.setTrajectories(new HashSet<>());

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite3);
    sensor.setType("Type");

    // Act and Assert
    assertSame(satellite2, satellite2.addSensor(sensor));
  }

  /**
   * Test {@link Satellite#removeSensor(Sensor)}.
   * <p>
   * Method under test: {@link Satellite#removeSensor(Sensor)}
   */
  @Test
  @DisplayName("Test removeSensor(Sensor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.removeSensor(Sensor)"})
  void testRemoveSensor() {
    // Arrange
    Satellite satellite2 = new Satellite();

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

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(1L);
    satellite3.setId(1L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel);
    satellite3.setName("Name");
    satellite3.setNetworkNodeId("42");
    satellite3.setSatellite_id(1L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.LAUNCHED);
    satellite3.setTrajectories(new HashSet<>());

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite3);
    sensor.setType("Type");

    // Act
    Satellite actualRemoveSensorResult = satellite2.removeSensor(sensor);

    // Assert
    assertNull(sensor.getSatellite());
    assertSame(satellite2, actualRemoveSensorResult);
  }

  /**
   * Test {@link Satellite#setTrajectories(Set)}.
   * <ul>
   *   <li>Given {@link SatelliteModel#SatelliteModel()} DesignTrajectoryPredictionFactor is {@code 0.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setTrajectories(Set)}
   */
  @Test
  @DisplayName("Test setTrajectories(Set); given SatelliteModel() DesignTrajectoryPredictionFactor is '0.5'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setTrajectories(Set)"})
  void testSetTrajectories_givenSatelliteModelDesignTrajectoryPredictionFactorIs05() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

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
    satelliteTrajectory.setSatellite(satellite2);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(0.5d);
    satelliteModel2.setDimensions("42");
    satelliteModel2.setDryMass(0.5d);
    satelliteModel2.setExpectedLifespan(0);
    satelliteModel2.setLaunchMass(0.5d);
    satelliteModel2.setManufacturer("42");
    satelliteModel2.setName("42");
    satelliteModel2.setPowerCapacity(0.5d);
    satelliteModel2.setWeight(0.5d);

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(2L);
    satellite3.setId(2L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel2);
    satellite3.setName("com.example.jeeHamlaoui.Sattelites_Service.model.Satellite");
    satellite3.setNetworkNodeId("Network Node Id");
    satellite3.setSatellite_id(2L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.OPERATIONAL);
    satellite3.setTrajectories(new HashSet<>());

    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    satelliteTrajectory2.setChangeReason("Change Reason");
    satelliteTrajectory2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setId(2L);
    satelliteTrajectory2.setOrbitArgumentOfPerigee(0.5d);
    satelliteTrajectory2.setOrbitEccentricity(0.5d);
    satelliteTrajectory2.setOrbitInclination(0.5d);
    satelliteTrajectory2.setOrbitMeanAnomaly(0.5d);
    satelliteTrajectory2.setOrbitPeriapsis(0.5d);
    satelliteTrajectory2.setOrbitRightAscension(0.5d);
    satelliteTrajectory2.setSatellite(satellite3);
    satelliteTrajectory2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setStatus("com.example.jeeHamlaoui.Sattelites_Service.model.SatelliteTrajectory");

    Satellite satellite4 = new Satellite();
    satellite4.addTrajectory(satelliteTrajectory2);
    satellite4.addTrajectory(satelliteTrajectory);
    HashSet<SatelliteTrajectory> satelliteTrajectories = new HashSet<>();

    // Act
    satellite4.setTrajectories(satelliteTrajectories);

    // Assert
    assertSame(satelliteTrajectories, satellite4.getTrajectories());
  }

  /**
   * Test {@link Satellite#setTrajectories(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Trajectories is {@link HashSet#HashSet()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setTrajectories(Set)}
   */
  @Test
  @DisplayName("Test setTrajectories(Set); given Satellite (default constructor) Trajectories is HashSet()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setTrajectories(Set)"})
  void testSetTrajectories_givenSatelliteTrajectoriesIsHashSet() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

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
    satelliteTrajectory.setSatellite(satellite2);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    Satellite satellite3 = new Satellite();
    satellite3.addTrajectory(satelliteTrajectory);
    HashSet<SatelliteTrajectory> satelliteTrajectories = new HashSet<>();

    // Act
    satellite3.setTrajectories(satelliteTrajectories);

    // Assert
    assertSame(satelliteTrajectories, satellite3.getTrajectories());
  }

  /**
   * Test {@link Satellite#setTrajectories(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor).</li>
   *   <li>Then {@link Satellite} (default constructor) Trajectories is {@link HashSet#HashSet()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setTrajectories(Set)}
   */
  @Test
  @DisplayName("Test setTrajectories(Set); given Satellite (default constructor); then Satellite (default constructor) Trajectories is HashSet()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setTrajectories(Set)"})
  void testSetTrajectories_givenSatellite_thenSatelliteTrajectoriesIsHashSet() {
    // Arrange
    Satellite satellite2 = new Satellite();
    HashSet<SatelliteTrajectory> satelliteTrajectories = new HashSet<>();

    // Act
    satellite2.setTrajectories(satelliteTrajectories);

    // Assert
    assertSame(satelliteTrajectories, satellite2.getTrajectories());
  }

  /**
   * Test {@link Satellite#setTrajectories(Set)}.
   * <ul>
   *   <li>Then {@link Satellite} (default constructor) Trajectories is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#setTrajectories(Set)}
   */
  @Test
  @DisplayName("Test setTrajectories(Set); then Satellite (default constructor) Trajectories is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.setTrajectories(Set)"})
  void testSetTrajectories_thenSatelliteTrajectoriesIsNull() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(null);

    // Act
    satellite2.setTrajectories(null);

    // Assert that nothing has changed
    assertNull(satellite2.getTrajectories());
  }

  /**
   * Test {@link Satellite#trajectories(Set)}.
   * <ul>
   *   <li>Given {@link SatelliteModel#SatelliteModel()} DesignTrajectoryPredictionFactor is {@code 0.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#trajectories(Set)}
   */
  @Test
  @DisplayName("Test trajectories(Set); given SatelliteModel() DesignTrajectoryPredictionFactor is '0.5'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.trajectories(Set)"})
  void testTrajectories_givenSatelliteModelDesignTrajectoryPredictionFactorIs05() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

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
    satelliteTrajectory.setSatellite(satellite2);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    SatelliteModel satelliteModel2 = new SatelliteModel();
    satelliteModel2.setDesignTrajectoryPredictionFactor(0.5d);
    satelliteModel2.setDimensions("42");
    satelliteModel2.setDryMass(0.5d);
    satelliteModel2.setExpectedLifespan(0);
    satelliteModel2.setLaunchMass(0.5d);
    satelliteModel2.setManufacturer("42");
    satelliteModel2.setName("42");
    satelliteModel2.setPowerCapacity(0.5d);
    satelliteModel2.setWeight(0.5d);

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(2L);
    satellite3.setId(2L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel2);
    satellite3.setName("com.example.jeeHamlaoui.Sattelites_Service.model.Satellite");
    satellite3.setNetworkNodeId("Network Node Id");
    satellite3.setSatellite_id(2L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.OPERATIONAL);
    satellite3.setTrajectories(new HashSet<>());

    SatelliteTrajectory satelliteTrajectory2 = new SatelliteTrajectory();
    satelliteTrajectory2.setChangeReason("Change Reason");
    satelliteTrajectory2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setId(2L);
    satelliteTrajectory2.setOrbitArgumentOfPerigee(0.5d);
    satelliteTrajectory2.setOrbitEccentricity(0.5d);
    satelliteTrajectory2.setOrbitInclination(0.5d);
    satelliteTrajectory2.setOrbitMeanAnomaly(0.5d);
    satelliteTrajectory2.setOrbitPeriapsis(0.5d);
    satelliteTrajectory2.setOrbitRightAscension(0.5d);
    satelliteTrajectory2.setSatellite(satellite3);
    satelliteTrajectory2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory2.setStatus("com.example.jeeHamlaoui.Sattelites_Service.model.SatelliteTrajectory");

    Satellite satellite4 = new Satellite();
    satellite4.addTrajectory(satelliteTrajectory2);
    satellite4.addTrajectory(satelliteTrajectory);
    HashSet<SatelliteTrajectory> satelliteTrajectories = new HashSet<>();

    // Act
    Satellite actualTrajectoriesResult = satellite4.trajectories(satelliteTrajectories);

    // Assert
    assertNull(actualTrajectoriesResult.getModel());
    assertNull(actualTrajectoriesResult.getStatus());
    assertNull(actualTrajectoriesResult.getGroundStationId());
    assertNull(actualTrajectoriesResult.getId());
    assertNull(actualTrajectoriesResult.getSatellite_id());
    assertNull(actualTrajectoriesResult.getName());
    assertNull(actualTrajectoriesResult.getNetworkNodeId());
    assertNull(actualTrajectoriesResult.getLaunchDate());
    assertTrue(actualTrajectoriesResult.getTrajectories().isEmpty());
    assertSame(satelliteTrajectories, satellite4.getTrajectories());
  }

  /**
   * Test {@link Satellite#trajectories(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Trajectories is {@link HashSet#HashSet()}.</li>
   *   <li>Then return Model is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#trajectories(Set)}
   */
  @Test
  @DisplayName("Test trajectories(Set); given Satellite (default constructor) Trajectories is HashSet(); then return Model is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.trajectories(Set)"})
  void testTrajectories_givenSatelliteTrajectoriesIsHashSet_thenReturnModelIsNull() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(new HashSet<>());

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
    satelliteTrajectory.setSatellite(satellite2);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    Satellite satellite3 = new Satellite();
    satellite3.addTrajectory(satelliteTrajectory);
    HashSet<SatelliteTrajectory> satelliteTrajectories = new HashSet<>();

    // Act
    Satellite actualTrajectoriesResult = satellite3.trajectories(satelliteTrajectories);

    // Assert
    assertNull(actualTrajectoriesResult.getModel());
    assertNull(actualTrajectoriesResult.getStatus());
    assertNull(actualTrajectoriesResult.getGroundStationId());
    assertNull(actualTrajectoriesResult.getId());
    assertNull(actualTrajectoriesResult.getSatellite_id());
    assertNull(actualTrajectoriesResult.getName());
    assertNull(actualTrajectoriesResult.getNetworkNodeId());
    assertNull(actualTrajectoriesResult.getLaunchDate());
    assertTrue(actualTrajectoriesResult.getTrajectories().isEmpty());
    assertSame(satelliteTrajectories, satellite3.getTrajectories());
  }

  /**
   * Test {@link Satellite#trajectories(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor) Trajectories is {@code null}.</li>
   *   <li>Then return NetworkNodeId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#trajectories(Set)}
   */
  @Test
  @DisplayName("Test trajectories(Set); given Satellite (default constructor) Trajectories is 'null'; then return NetworkNodeId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.trajectories(Set)"})
  void testTrajectories_givenSatelliteTrajectoriesIsNull_thenReturnNetworkNodeIdIs42() {
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

    Satellite satellite2 = new Satellite();
    satellite2.setGroundStationId(1L);
    satellite2.setId(1L);
    satellite2.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite2.setModel(satelliteModel);
    satellite2.setName("Name");
    satellite2.setNetworkNodeId("42");
    satellite2.setSatellite_id(1L);
    satellite2.setSensors(new HashSet<>());
    satellite2.setStatus(SatelliteStatus.LAUNCHED);
    satellite2.setTrajectories(null);

    // Act
    Satellite actualTrajectoriesResult = satellite2.trajectories(null);

    // Assert
    assertEquals("42", actualTrajectoriesResult.getNetworkNodeId());
    assertEquals("Name", actualTrajectoriesResult.getName());
    assertNull(satellite2.getTrajectories());
    assertNull(actualTrajectoriesResult.getTrajectories());
    assertEquals(1L, actualTrajectoriesResult.getGroundStationId().longValue());
    assertEquals(1L, actualTrajectoriesResult.getId().longValue());
    assertEquals(1L, actualTrajectoriesResult.getSatellite_id().longValue());
    assertEquals(SatelliteStatus.LAUNCHED, actualTrajectoriesResult.getStatus());
    assertSame(satelliteModel, actualTrajectoriesResult.getModel());
  }

  /**
   * Test {@link Satellite#trajectories(Set)}.
   * <ul>
   *   <li>Given {@link Satellite} (default constructor).</li>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return Model is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#trajectories(Set)}
   */
  @Test
  @DisplayName("Test trajectories(Set); given Satellite (default constructor); when HashSet(); then return Model is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.trajectories(Set)"})
  void testTrajectories_givenSatellite_whenHashSet_thenReturnModelIsNull() {
    // Arrange
    Satellite satellite2 = new Satellite();
    HashSet<SatelliteTrajectory> satelliteTrajectories = new HashSet<>();

    // Act
    Satellite actualTrajectoriesResult = satellite2.trajectories(satelliteTrajectories);

    // Assert
    assertNull(actualTrajectoriesResult.getModel());
    assertNull(actualTrajectoriesResult.getStatus());
    assertNull(actualTrajectoriesResult.getGroundStationId());
    assertNull(actualTrajectoriesResult.getId());
    assertNull(actualTrajectoriesResult.getSatellite_id());
    assertNull(actualTrajectoriesResult.getName());
    assertNull(actualTrajectoriesResult.getNetworkNodeId());
    assertNull(actualTrajectoriesResult.getLaunchDate());
    assertTrue(actualTrajectoriesResult.getTrajectories().isEmpty());
    assertSame(satelliteTrajectories, satellite2.getTrajectories());
  }

  /**
   * Test {@link Satellite#addTrajectory(SatelliteTrajectory)}.
   * <p>
   * Method under test: {@link Satellite#addTrajectory(SatelliteTrajectory)}
   */
  @Test
  @DisplayName("Test addTrajectory(SatelliteTrajectory)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.addTrajectory(SatelliteTrajectory)"})
  void testAddTrajectory() {
    // Arrange
    Satellite satellite2 = new Satellite();

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

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(1L);
    satellite3.setId(1L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel);
    satellite3.setName("Name");
    satellite3.setNetworkNodeId("42");
    satellite3.setSatellite_id(1L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.LAUNCHED);
    satellite3.setTrajectories(new HashSet<>());

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
    satelliteTrajectory.setSatellite(satellite3);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    // Act and Assert
    assertSame(satellite2, satellite2.addTrajectory(satelliteTrajectory));
  }

  /**
   * Test {@link Satellite#removeTrajectory(SatelliteTrajectory)}.
   * <p>
   * Method under test: {@link Satellite#removeTrajectory(SatelliteTrajectory)}
   */
  @Test
  @DisplayName("Test removeTrajectory(SatelliteTrajectory)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.removeTrajectory(SatelliteTrajectory)"})
  void testRemoveTrajectory() {
    // Arrange
    Satellite satellite2 = new Satellite();

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

    Satellite satellite3 = new Satellite();
    satellite3.setGroundStationId(1L);
    satellite3.setId(1L);
    satellite3.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satellite3.setModel(satelliteModel);
    satellite3.setName("Name");
    satellite3.setNetworkNodeId("42");
    satellite3.setSatellite_id(1L);
    satellite3.setSensors(new HashSet<>());
    satellite3.setStatus(SatelliteStatus.LAUNCHED);
    satellite3.setTrajectories(new HashSet<>());

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
    satelliteTrajectory.setSatellite(satellite3);
    satelliteTrajectory.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    satelliteTrajectory.setStatus("Status");

    // Act
    Satellite actualRemoveTrajectoryResult = satellite2.removeTrajectory(satelliteTrajectory);

    // Assert
    assertNull(satelliteTrajectory.getSatellite());
    assertSame(satellite2, actualRemoveTrajectoryResult);
  }

  /**
   * Test {@link Satellite#model(SatelliteModel)}.
   * <p>
   * Method under test: {@link Satellite#model(SatelliteModel)}
   */
  @Test
  @DisplayName("Test model(SatelliteModel)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Satellite Satellite.model(SatelliteModel)"})
  void testModel() {
    // Arrange
    Satellite satellite2 = new Satellite();

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

    // Act and Assert
    assertSame(satellite2, satellite2.model(satelliteModel));
    assertSame(satelliteModel, satellite2.getModel());
  }

  /**
   * Test {@link Satellite#equals(Object)}, and {@link Satellite#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Satellite#equals(Object)}
   *   <li>{@link Satellite#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Satellite.equals(Object)", "int Satellite.hashCode()"})
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

    // Act and Assert
    assertEquals(satellite, satellite2);
    int expectedHashCodeResult = satellite.hashCode();
    assertEquals(expectedHashCodeResult, satellite2.hashCode());
  }

  /**
   * Test {@link Satellite#equals(Object)}, and {@link Satellite#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Satellite#equals(Object)}
   *   <li>{@link Satellite#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Satellite.equals(Object)", "int Satellite.hashCode()"})
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

    // Act and Assert
    assertEquals(satellite, satellite);
    int expectedHashCodeResult = satellite.hashCode();
    assertEquals(expectedHashCodeResult, satellite.hashCode());
  }

  /**
   * Test {@link Satellite#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Satellite.equals(Object)", "int Satellite.hashCode()"})
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
    satellite.setSatellite_id(2L);
    satellite.setSensors(new HashSet<>());
    satellite.setStatus(SatelliteStatus.LAUNCHED);
    satellite.setTrajectories(new HashSet<>());

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

    // Act and Assert
    assertNotEquals(satellite, satellite2);
  }

  /**
   * Test {@link Satellite#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Satellite.equals(Object)", "int Satellite.hashCode()"})
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
    satellite.setSatellite_id(null);
    satellite.setSensors(new HashSet<>());
    satellite.setStatus(SatelliteStatus.LAUNCHED);
    satellite.setTrajectories(new HashSet<>());

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

    // Act and Assert
    assertNotEquals(satellite, satellite2);
  }

  /**
   * Test {@link Satellite#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Satellite.equals(Object)", "int Satellite.hashCode()"})
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

    // Act and Assert
    assertNotEquals(satellite, null);
  }

  /**
   * Test {@link Satellite#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Satellite#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Satellite.equals(Object)", "int Satellite.hashCode()"})
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

    // Act and Assert
    assertNotEquals(satellite, "Different type to Satellite");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Satellite}
   *   <li>{@link Satellite#setGroundStationId(Long)}
   *   <li>{@link Satellite#setId(Long)}
   *   <li>{@link Satellite#setLaunchDate(Instant)}
   *   <li>{@link Satellite#setModel(SatelliteModel)}
   *   <li>{@link Satellite#setName(String)}
   *   <li>{@link Satellite#setNetworkNodeId(String)}
   *   <li>{@link Satellite#setSatellite_id(Long)}
   *   <li>{@link Satellite#setStatus(SatelliteStatus)}
   *   <li>{@link Satellite#toString()}
   *   <li>{@link Satellite#getGroundStationId()}
   *   <li>{@link Satellite#getId()}
   *   <li>{@link Satellite#getLaunchDate()}
   *   <li>{@link Satellite#getModel()}
   *   <li>{@link Satellite#getName()}
   *   <li>{@link Satellite#getNetworkNodeId()}
   *   <li>{@link Satellite#getSatellite_id()}
   *   <li>{@link Satellite#getSensors()}
   *   <li>{@link Satellite#getStatus()}
   *   <li>{@link Satellite#getTrajectories()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Satellite.<init>()", "Long Satellite.getGroundStationId()", "Long Satellite.getId()",
      "Instant Satellite.getLaunchDate()", "SatelliteModel Satellite.getModel()", "String Satellite.getName()",
      "String Satellite.getNetworkNodeId()", "Long Satellite.getSatellite_id()", "Set Satellite.getSensors()",
      "SatelliteStatus Satellite.getStatus()", "Set Satellite.getTrajectories()",
      "void Satellite.setGroundStationId(Long)", "void Satellite.setId(Long)", "void Satellite.setLaunchDate(Instant)",
      "void Satellite.setModel(SatelliteModel)", "void Satellite.setName(String)",
      "void Satellite.setNetworkNodeId(String)", "void Satellite.setSatellite_id(Long)",
      "void Satellite.setStatus(SatelliteStatus)", "String Satellite.toString()"})
  void testGettersAndSetters() {
    // Arrange and Act
    Satellite actualSatellite = new Satellite();
    actualSatellite.setGroundStationId(1L);
    actualSatellite.setId(1L);
    actualSatellite.setLaunchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
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
    actualSatellite.setModel(satelliteModel);
    actualSatellite.setName("Name");
    actualSatellite.setNetworkNodeId("42");
    actualSatellite.setSatellite_id(1L);
    actualSatellite.setStatus(SatelliteStatus.LAUNCHED);
    String actualToStringResult = actualSatellite.toString();
    Long actualGroundStationId = actualSatellite.getGroundStationId();
    Long actualId = actualSatellite.getId();
    Instant actualLaunchDate = actualSatellite.getLaunchDate();
    SatelliteModel actualModel = actualSatellite.getModel();
    String actualName = actualSatellite.getName();
    String actualNetworkNodeId = actualSatellite.getNetworkNodeId();
    Long actualSatellite_id = actualSatellite.getSatellite_id();
    Set<Sensor> actualSensors = actualSatellite.getSensors();
    SatelliteStatus actualStatus = actualSatellite.getStatus();
    Set<SatelliteTrajectory> actualTrajectories = actualSatellite.getTrajectories();

    // Assert
    assertEquals("42", actualNetworkNodeId);
    assertEquals("Name", actualName);
    assertEquals("Satellite{satellite_id=1, name='Name', launchDate='1970-01-01T00:00:00Z', status='LAUNCHED'}",
        actualToStringResult);
    assertEquals(1L, actualGroundStationId.longValue());
    assertEquals(1L, actualId.longValue());
    assertEquals(1L, actualSatellite_id.longValue());
    assertEquals(SatelliteStatus.LAUNCHED, actualStatus);
    assertTrue(actualSensors.isEmpty());
    assertTrue(actualTrajectories.isEmpty());
    assertSame(satelliteModel, actualModel);
    assertSame(actualLaunchDate.EPOCH, actualLaunchDate);
  }
}
