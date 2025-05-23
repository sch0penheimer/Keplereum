package com.example.jeeHamlaoui.Sattelites_Service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SatelliteModelTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteModel#SatelliteModel()}
   *   <li>{@link SatelliteModel#setDesignTrajectoryPredictionFactor(Double)}
   *   <li>{@link SatelliteModel#setDimensions(String)}
   *   <li>{@link SatelliteModel#setDryMass(Double)}
   *   <li>{@link SatelliteModel#setExpectedLifespan(Integer)}
   *   <li>{@link SatelliteModel#setLaunchMass(Double)}
   *   <li>{@link SatelliteModel#setManufacturer(String)}
   *   <li>{@link SatelliteModel#setName(String)}
   *   <li>{@link SatelliteModel#setPowerCapacity(Double)}
   *   <li>{@link SatelliteModel#setWeight(Double)}
   *   <li>{@link SatelliteModel#getDesignTrajectoryPredictionFactor()}
   *   <li>{@link SatelliteModel#getDimensions()}
   *   <li>{@link SatelliteModel#getDryMass()}
   *   <li>{@link SatelliteModel#getExpectedLifespan()}
   *   <li>{@link SatelliteModel#getLaunchMass()}
   *   <li>{@link SatelliteModel#getManufacturer()}
   *   <li>{@link SatelliteModel#getName()}
   *   <li>{@link SatelliteModel#getPowerCapacity()}
   *   <li>{@link SatelliteModel#getSatelliteModel_id()}
   *   <li>{@link SatelliteModel#getWeight()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteModel.<init>()",
      "void SatelliteModel.<init>(String, Double, String, String, Double, Integer, Double, Double, Double)",
      "Double SatelliteModel.getDesignTrajectoryPredictionFactor()", "String SatelliteModel.getDimensions()",
      "Double SatelliteModel.getDryMass()", "Integer SatelliteModel.getExpectedLifespan()",
      "Double SatelliteModel.getLaunchMass()", "String SatelliteModel.getManufacturer()",
      "String SatelliteModel.getName()", "Double SatelliteModel.getPowerCapacity()",
      "Long SatelliteModel.getSatelliteModel_id()", "Double SatelliteModel.getWeight()",
      "void SatelliteModel.setDesignTrajectoryPredictionFactor(Double)", "void SatelliteModel.setDimensions(String)",
      "void SatelliteModel.setDryMass(Double)", "void SatelliteModel.setExpectedLifespan(Integer)",
      "void SatelliteModel.setLaunchMass(Double)", "void SatelliteModel.setManufacturer(String)",
      "void SatelliteModel.setName(String)", "void SatelliteModel.setPowerCapacity(Double)",
      "void SatelliteModel.setWeight(Double)"})
  void testGettersAndSetters() {
    // Arrange and Act
    SatelliteModel actualSatelliteModel = new SatelliteModel();
    actualSatelliteModel.setDesignTrajectoryPredictionFactor(10.0d);
    actualSatelliteModel.setDimensions("Dimensions");
    actualSatelliteModel.setDryMass(10.0d);
    actualSatelliteModel.setExpectedLifespan(1);
    actualSatelliteModel.setLaunchMass(10.0d);
    actualSatelliteModel.setManufacturer("Manufacturer");
    actualSatelliteModel.setName("Name");
    actualSatelliteModel.setPowerCapacity(10.0d);
    actualSatelliteModel.setWeight(10.0d);
    Double actualDesignTrajectoryPredictionFactor = actualSatelliteModel.getDesignTrajectoryPredictionFactor();
    String actualDimensions = actualSatelliteModel.getDimensions();
    Double actualDryMass = actualSatelliteModel.getDryMass();
    Integer actualExpectedLifespan = actualSatelliteModel.getExpectedLifespan();
    Double actualLaunchMass = actualSatelliteModel.getLaunchMass();
    String actualManufacturer = actualSatelliteModel.getManufacturer();
    String actualName = actualSatelliteModel.getName();
    Double actualPowerCapacity = actualSatelliteModel.getPowerCapacity();
    Long actualSatelliteModel_id = actualSatelliteModel.getSatelliteModel_id();
    Double actualWeight = actualSatelliteModel.getWeight();

    // Assert
    assertEquals("Dimensions", actualDimensions);
    assertEquals("Manufacturer", actualManufacturer);
    assertEquals("Name", actualName);
    assertNull(actualSatelliteModel_id);
    assertEquals(1, actualExpectedLifespan.intValue());
    assertEquals(10.0d, actualDesignTrajectoryPredictionFactor.doubleValue());
    assertEquals(10.0d, actualDryMass.doubleValue());
    assertEquals(10.0d, actualLaunchMass.doubleValue());
    assertEquals(10.0d, actualPowerCapacity.doubleValue());
    assertEquals(10.0d, actualWeight.doubleValue());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code Name}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SatelliteModel#SatelliteModel(String, Double, String, String, Double, Integer, Double, Double, Double)}
   *   <li>{@link SatelliteModel#setDesignTrajectoryPredictionFactor(Double)}
   *   <li>{@link SatelliteModel#setDimensions(String)}
   *   <li>{@link SatelliteModel#setDryMass(Double)}
   *   <li>{@link SatelliteModel#setExpectedLifespan(Integer)}
   *   <li>{@link SatelliteModel#setLaunchMass(Double)}
   *   <li>{@link SatelliteModel#setManufacturer(String)}
   *   <li>{@link SatelliteModel#setName(String)}
   *   <li>{@link SatelliteModel#setPowerCapacity(Double)}
   *   <li>{@link SatelliteModel#setWeight(Double)}
   *   <li>{@link SatelliteModel#getDesignTrajectoryPredictionFactor()}
   *   <li>{@link SatelliteModel#getDimensions()}
   *   <li>{@link SatelliteModel#getDryMass()}
   *   <li>{@link SatelliteModel#getExpectedLifespan()}
   *   <li>{@link SatelliteModel#getLaunchMass()}
   *   <li>{@link SatelliteModel#getManufacturer()}
   *   <li>{@link SatelliteModel#getName()}
   *   <li>{@link SatelliteModel#getPowerCapacity()}
   *   <li>{@link SatelliteModel#getSatelliteModel_id()}
   *   <li>{@link SatelliteModel#getWeight()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SatelliteModel.<init>()",
      "void SatelliteModel.<init>(String, Double, String, String, Double, Integer, Double, Double, Double)",
      "Double SatelliteModel.getDesignTrajectoryPredictionFactor()", "String SatelliteModel.getDimensions()",
      "Double SatelliteModel.getDryMass()", "Integer SatelliteModel.getExpectedLifespan()",
      "Double SatelliteModel.getLaunchMass()", "String SatelliteModel.getManufacturer()",
      "String SatelliteModel.getName()", "Double SatelliteModel.getPowerCapacity()",
      "Long SatelliteModel.getSatelliteModel_id()", "Double SatelliteModel.getWeight()",
      "void SatelliteModel.setDesignTrajectoryPredictionFactor(Double)", "void SatelliteModel.setDimensions(String)",
      "void SatelliteModel.setDryMass(Double)", "void SatelliteModel.setExpectedLifespan(Integer)",
      "void SatelliteModel.setLaunchMass(Double)", "void SatelliteModel.setManufacturer(String)",
      "void SatelliteModel.setName(String)", "void SatelliteModel.setPowerCapacity(Double)",
      "void SatelliteModel.setWeight(Double)"})
  void testGettersAndSetters_whenName() {
    // Arrange and Act
    SatelliteModel actualSatelliteModel = new SatelliteModel("Name", 10.0d, "Manufacturer", "Dimensions", 10.0d, 1,
        10.0d, 10.0d, 10.0d);
    actualSatelliteModel.setDesignTrajectoryPredictionFactor(10.0d);
    actualSatelliteModel.setDimensions("Dimensions");
    actualSatelliteModel.setDryMass(10.0d);
    actualSatelliteModel.setExpectedLifespan(1);
    actualSatelliteModel.setLaunchMass(10.0d);
    actualSatelliteModel.setManufacturer("Manufacturer");
    actualSatelliteModel.setName("Name");
    actualSatelliteModel.setPowerCapacity(10.0d);
    actualSatelliteModel.setWeight(10.0d);
    Double actualDesignTrajectoryPredictionFactor = actualSatelliteModel.getDesignTrajectoryPredictionFactor();
    String actualDimensions = actualSatelliteModel.getDimensions();
    Double actualDryMass = actualSatelliteModel.getDryMass();
    Integer actualExpectedLifespan = actualSatelliteModel.getExpectedLifespan();
    Double actualLaunchMass = actualSatelliteModel.getLaunchMass();
    String actualManufacturer = actualSatelliteModel.getManufacturer();
    String actualName = actualSatelliteModel.getName();
    Double actualPowerCapacity = actualSatelliteModel.getPowerCapacity();
    Long actualSatelliteModel_id = actualSatelliteModel.getSatelliteModel_id();
    Double actualWeight = actualSatelliteModel.getWeight();

    // Assert
    assertEquals("Dimensions", actualDimensions);
    assertEquals("Manufacturer", actualManufacturer);
    assertEquals("Name", actualName);
    assertNull(actualSatelliteModel_id);
    assertEquals(1, actualExpectedLifespan.intValue());
    assertEquals(10.0d, actualDesignTrajectoryPredictionFactor.doubleValue());
    assertEquals(10.0d, actualDryMass.doubleValue());
    assertEquals(10.0d, actualLaunchMass.doubleValue());
    assertEquals(10.0d, actualPowerCapacity.doubleValue());
    assertEquals(10.0d, actualWeight.doubleValue());
  }
}
