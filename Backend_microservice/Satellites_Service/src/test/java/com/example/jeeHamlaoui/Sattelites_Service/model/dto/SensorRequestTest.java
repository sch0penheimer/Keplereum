package com.example.jeeHamlaoui.Sattelites_Service.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.jeeHamlaoui.Sattelites_Service.model.enumeration.SensorActivity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SensorRequestTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link SensorRequest}
   *   <li>{@link SensorRequest#setActivity(SensorActivity)}
   *   <li>{@link SensorRequest#setMaxHeight(Double)}
   *   <li>{@link SensorRequest#setMaxRadius(Double)}
   *   <li>{@link SensorRequest#setSatelliteId(Long)}
   *   <li>{@link SensorRequest#setType(String)}
   *   <li>{@link SensorRequest#getActivity()}
   *   <li>{@link SensorRequest#getMaxHeight()}
   *   <li>{@link SensorRequest#getMaxRadius()}
   *   <li>{@link SensorRequest#getSatelliteId()}
   *   <li>{@link SensorRequest#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SensorRequest.<init>()", "SensorActivity SensorRequest.getActivity()",
      "Double SensorRequest.getMaxHeight()", "Double SensorRequest.getMaxRadius()",
      "Long SensorRequest.getSatelliteId()", "String SensorRequest.getType()",
      "void SensorRequest.setActivity(SensorActivity)", "void SensorRequest.setMaxHeight(Double)",
      "void SensorRequest.setMaxRadius(Double)", "void SensorRequest.setSatelliteId(Long)",
      "void SensorRequest.setType(String)"})
  void testGettersAndSetters() {
    // Arrange and Act
    SensorRequest actualSensorRequest = new SensorRequest();
    actualSensorRequest.setActivity(SensorActivity.ACTIVE);
    actualSensorRequest.setMaxHeight(10.0d);
    actualSensorRequest.setMaxRadius(10.0d);
    actualSensorRequest.setSatelliteId(1L);
    actualSensorRequest.setType("Type");
    SensorActivity actualActivity = actualSensorRequest.getActivity();
    Double actualMaxHeight = actualSensorRequest.getMaxHeight();
    Double actualMaxRadius = actualSensorRequest.getMaxRadius();
    Long actualSatelliteId = actualSensorRequest.getSatelliteId();

    // Assert
    assertEquals("Type", actualSensorRequest.getType());
    assertEquals(10.0d, actualMaxHeight.doubleValue());
    assertEquals(10.0d, actualMaxRadius.doubleValue());
    assertEquals(1L, actualSatelliteId.longValue());
    assertEquals(SensorActivity.ACTIVE, actualActivity);
  }
}
