package com.example.jeeHamlaoui.Sattelites_Service.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.jeeHamlaoui.Sattelites_Service.model.Satellite;
import com.example.jeeHamlaoui.Sattelites_Service.model.SatelliteModel;
import com.example.jeeHamlaoui.Sattelites_Service.model.Sensor;
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

@ContextConfiguration(classes = {SensorResponse.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class SensorResponseTest {
  @Autowired
  private SensorResponse sensorResponse;

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SensorResponse#SensorResponse()}
   *   <li>{@link SensorResponse#setActivity(SensorActivity)}
   *   <li>{@link SensorResponse#setId(Long)}
   *   <li>{@link SensorResponse#setMaxHeight(Double)}
   *   <li>{@link SensorResponse#setMaxRadius(Double)}
   *   <li>{@link SensorResponse#setSatelliteId(Long)}
   *   <li>{@link SensorResponse#setType(String)}
   *   <li>{@link SensorResponse#getActivity()}
   *   <li>{@link SensorResponse#getId()}
   *   <li>{@link SensorResponse#getMaxHeight()}
   *   <li>{@link SensorResponse#getMaxRadius()}
   *   <li>{@link SensorResponse#getSatelliteId()}
   *   <li>{@link SensorResponse#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SensorResponse.<init>()", "SensorActivity SensorResponse.getActivity()",
      "Long SensorResponse.getId()", "Double SensorResponse.getMaxHeight()", "Double SensorResponse.getMaxRadius()",
      "Long SensorResponse.getSatelliteId()", "String SensorResponse.getType()",
      "void SensorResponse.setActivity(SensorActivity)", "void SensorResponse.setId(Long)",
      "void SensorResponse.setMaxHeight(Double)", "void SensorResponse.setMaxRadius(Double)",
      "void SensorResponse.setSatelliteId(Long)", "void SensorResponse.setType(String)"})
  void testGettersAndSetters() {
    // Arrange and Act
    SensorResponse actualSensorResponse = new SensorResponse();
    actualSensorResponse.setActivity(SensorActivity.ACTIVE);
    actualSensorResponse.setId(1L);
    actualSensorResponse.setMaxHeight(10.0d);
    actualSensorResponse.setMaxRadius(10.0d);
    actualSensorResponse.setSatelliteId(1L);
    actualSensorResponse.setType("Type");
    SensorActivity actualActivity = actualSensorResponse.getActivity();
    Long actualId = actualSensorResponse.getId();
    Double actualMaxHeight = actualSensorResponse.getMaxHeight();
    Double actualMaxRadius = actualSensorResponse.getMaxRadius();
    Long actualSatelliteId = actualSensorResponse.getSatelliteId();

    // Assert
    assertEquals("Type", actualSensorResponse.getType());
    assertEquals(10.0d, actualMaxHeight.doubleValue());
    assertEquals(10.0d, actualMaxRadius.doubleValue());
    assertEquals(1L, actualId.longValue());
    assertEquals(1L, actualSatelliteId.longValue());
    assertEquals(SensorActivity.ACTIVE, actualActivity);
  }

  /**
   * Test {@link SensorResponse#SensorResponse(Sensor)}.
   * <p>
   * Method under test: {@link SensorResponse#SensorResponse(Sensor)}
   */
  @Test
  @DisplayName("Test new SensorResponse(Sensor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SensorResponse.<init>(Sensor)"})
  void testNewSensorResponse() {
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

    // Act
    SensorResponse actualSensorResponse = new SensorResponse(sensor);

    // Assert
    assertEquals("Type", actualSensorResponse.getType());
    assertEquals(10.0d, actualSensorResponse.getMaxHeight().doubleValue());
    assertEquals(10.0d, actualSensorResponse.getMaxRadius().doubleValue());
    assertEquals(1L, actualSensorResponse.getId().longValue());
    assertEquals(1L, actualSensorResponse.getSatelliteId().longValue());
    assertEquals(SensorActivity.ACTIVE, actualSensorResponse.getActivity());
  }
}
