package com.example.jeeHamlaoui.Sattelites_Service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.jeeHamlaoui.Sattelites_Service.model.enumeration.SatelliteStatus;
import com.example.jeeHamlaoui.Sattelites_Service.model.enumeration.SensorActivity;
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

@ContextConfiguration(classes = {Sensor.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class SensorTest {
  @Autowired
  private Sensor sensor;

  /**
   * Test {@link Sensor#id(Long)}.
   * <p>
   * Method under test: {@link Sensor#id(Long)}
   */
  @Test
  @DisplayName("Test id(Long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Sensor Sensor.id(Long)"})
  void testId() {
    // Arrange
    Sensor sensor2 = new Sensor();

    // Act
    Sensor actualIdResult = sensor2.id(1L);

    // Assert
    assertEquals(1L, sensor2.getId().longValue());
    assertSame(sensor2, actualIdResult);
  }

  /**
   * Test {@link Sensor#type(String)}.
   * <p>
   * Method under test: {@link Sensor#type(String)}
   */
  @Test
  @DisplayName("Test type(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Sensor Sensor.type(String)"})
  void testType() {
    // Arrange
    Sensor sensor2 = new Sensor();

    // Act
    Sensor actualTypeResult = sensor2.type("Type");

    // Assert
    assertEquals("Type", sensor2.getType());
    assertSame(sensor2, actualTypeResult);
  }

  /**
   * Test {@link Sensor#maxHeight(Double)}.
   * <p>
   * Method under test: {@link Sensor#maxHeight(Double)}
   */
  @Test
  @DisplayName("Test maxHeight(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Sensor Sensor.maxHeight(Double)"})
  void testMaxHeight() {
    // Arrange
    Sensor sensor2 = new Sensor();

    // Act
    Sensor actualMaxHeightResult = sensor2.maxHeight(10.0d);

    // Assert
    assertEquals(10.0d, sensor2.getMaxHeight().doubleValue());
    assertSame(sensor2, actualMaxHeightResult);
  }

  /**
   * Test {@link Sensor#maxRadius(Double)}.
   * <p>
   * Method under test: {@link Sensor#maxRadius(Double)}
   */
  @Test
  @DisplayName("Test maxRadius(Double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Sensor Sensor.maxRadius(Double)"})
  void testMaxRadius() {
    // Arrange
    Sensor sensor2 = new Sensor();

    // Act
    Sensor actualMaxRadiusResult = sensor2.maxRadius(10.0d);

    // Assert
    assertEquals(10.0d, sensor2.getMaxRadius().doubleValue());
    assertSame(sensor2, actualMaxRadiusResult);
  }

  /**
   * Test {@link Sensor#activity(SensorActivity)}.
   * <p>
   * Method under test: {@link Sensor#activity(SensorActivity)}
   */
  @Test
  @DisplayName("Test activity(SensorActivity)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Sensor Sensor.activity(SensorActivity)"})
  void testActivity() {
    // Arrange
    Sensor sensor2 = new Sensor();

    // Act
    Sensor actualActivityResult = sensor2.activity(SensorActivity.ACTIVE);

    // Assert
    assertEquals(SensorActivity.ACTIVE, sensor2.getActivity());
    assertSame(sensor2, actualActivityResult);
  }

  /**
   * Test {@link Sensor#satellite(Satellite)}.
   * <p>
   * Method under test: {@link Sensor#satellite(Satellite)}
   */
  @Test
  @DisplayName("Test satellite(Satellite)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Sensor Sensor.satellite(Satellite)"})
  void testSatellite() {
    // Arrange
    Sensor sensor2 = new Sensor();

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
    Sensor actualSatelliteResult = sensor2.satellite(satellite);

    // Assert
    assertSame(satellite, sensor2.getSatellite());
    assertSame(sensor2, actualSatelliteResult);
  }

  /**
   * Test {@link Sensor#equals(Object)}, and {@link Sensor#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Sensor#equals(Object)}
   *   <li>{@link Sensor#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Sensor.equals(Object)", "int Sensor.hashCode()"})
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

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite);
    sensor.setType("Type");

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

    Sensor sensor2 = new Sensor();
    sensor2.setActivity(SensorActivity.ACTIVE);
    sensor2.setId(1L);
    sensor2.setMaxHeight(10.0d);
    sensor2.setMaxRadius(10.0d);
    sensor2.setSatellite(satellite2);
    sensor2.setType("Type");

    // Act and Assert
    assertEquals(sensor, sensor2);
    int expectedHashCodeResult = sensor.hashCode();
    assertEquals(expectedHashCodeResult, sensor2.hashCode());
  }

  /**
   * Test {@link Sensor#equals(Object)}, and {@link Sensor#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Sensor#equals(Object)}
   *   <li>{@link Sensor#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Sensor.equals(Object)", "int Sensor.hashCode()"})
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

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite);
    sensor.setType("Type");

    // Act and Assert
    assertEquals(sensor, sensor);
    int expectedHashCodeResult = sensor.hashCode();
    assertEquals(expectedHashCodeResult, sensor.hashCode());
  }

  /**
   * Test {@link Sensor#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sensor#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Sensor.equals(Object)", "int Sensor.hashCode()"})
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

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(2L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite);
    sensor.setType("Type");

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

    Sensor sensor2 = new Sensor();
    sensor2.setActivity(SensorActivity.ACTIVE);
    sensor2.setId(1L);
    sensor2.setMaxHeight(10.0d);
    sensor2.setMaxRadius(10.0d);
    sensor2.setSatellite(satellite2);
    sensor2.setType("Type");

    // Act and Assert
    assertNotEquals(sensor, sensor2);
  }

  /**
   * Test {@link Sensor#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sensor#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Sensor.equals(Object)", "int Sensor.hashCode()"})
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

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(null);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite);
    sensor.setType("Type");

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

    Sensor sensor2 = new Sensor();
    sensor2.setActivity(SensorActivity.ACTIVE);
    sensor2.setId(1L);
    sensor2.setMaxHeight(10.0d);
    sensor2.setMaxRadius(10.0d);
    sensor2.setSatellite(satellite2);
    sensor2.setType("Type");

    // Act and Assert
    assertNotEquals(sensor, sensor2);
  }

  /**
   * Test {@link Sensor#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sensor#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Sensor.equals(Object)", "int Sensor.hashCode()"})
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

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite);
    sensor.setType("Type");

    // Act and Assert
    assertNotEquals(sensor, null);
  }

  /**
   * Test {@link Sensor#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sensor#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Sensor.equals(Object)", "int Sensor.hashCode()"})
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

    Sensor sensor = new Sensor();
    sensor.setActivity(SensorActivity.ACTIVE);
    sensor.setId(1L);
    sensor.setMaxHeight(10.0d);
    sensor.setMaxRadius(10.0d);
    sensor.setSatellite(satellite);
    sensor.setType("Type");

    // Act and Assert
    assertNotEquals(sensor, "Different type to Sensor");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Sensor}
   *   <li>{@link Sensor#setActivity(SensorActivity)}
   *   <li>{@link Sensor#setId(Long)}
   *   <li>{@link Sensor#setMaxHeight(Double)}
   *   <li>{@link Sensor#setMaxRadius(Double)}
   *   <li>{@link Sensor#setSatellite(Satellite)}
   *   <li>{@link Sensor#setType(String)}
   *   <li>{@link Sensor#toString()}
   *   <li>{@link Sensor#getActivity()}
   *   <li>{@link Sensor#getId()}
   *   <li>{@link Sensor#getMaxHeight()}
   *   <li>{@link Sensor#getMaxRadius()}
   *   <li>{@link Sensor#getSatellite()}
   *   <li>{@link Sensor#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Sensor.<init>()", "SensorActivity Sensor.getActivity()", "Long Sensor.getId()",
      "Double Sensor.getMaxHeight()", "Double Sensor.getMaxRadius()", "Satellite Sensor.getSatellite()",
      "String Sensor.getType()", "void Sensor.setActivity(SensorActivity)", "void Sensor.setId(Long)",
      "void Sensor.setMaxHeight(Double)", "void Sensor.setMaxRadius(Double)", "void Sensor.setSatellite(Satellite)",
      "void Sensor.setType(String)", "String Sensor.toString()"})
  void testGettersAndSetters() {
    // Arrange and Act
    Sensor actualSensor = new Sensor();
    actualSensor.setActivity(SensorActivity.ACTIVE);
    actualSensor.setId(1L);
    actualSensor.setMaxHeight(10.0d);
    actualSensor.setMaxRadius(10.0d);
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
    actualSensor.setSatellite(satellite);
    actualSensor.setType("Type");
    String actualToStringResult = actualSensor.toString();
    SensorActivity actualActivity = actualSensor.getActivity();
    Long actualId = actualSensor.getId();
    Double actualMaxHeight = actualSensor.getMaxHeight();
    Double actualMaxRadius = actualSensor.getMaxRadius();
    Satellite actualSatellite = actualSensor.getSatellite();

    // Assert
    assertEquals("Sensor{id=1, type='Type', maxHeight=10.0, maxRadius=10.0, activity='ACTIVE'}", actualToStringResult);
    assertEquals("Type", actualSensor.getType());
    assertEquals(10.0d, actualMaxHeight.doubleValue());
    assertEquals(10.0d, actualMaxRadius.doubleValue());
    assertEquals(1L, actualId.longValue());
    assertEquals(SensorActivity.ACTIVE, actualActivity);
    assertSame(satellite, actualSatellite);
  }
}
