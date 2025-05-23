package com.example.jeeHamlaoui.Sattelites_Service.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OrbitalTrajectoryResponseTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link OrbitalTrajectoryResponse#OrbitalTrajectoryResponse(List, List, double)}
   *   <li>{@link OrbitalTrajectoryResponse#getCartesianValues()}
   *   <li>{@link OrbitalTrajectoryResponse#getPeriodSeconds()}
   *   <li>{@link OrbitalTrajectoryResponse#getPoints3D()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OrbitalTrajectoryResponse.<init>(List, List, double)",
      "List OrbitalTrajectoryResponse.getCartesianValues()", "double OrbitalTrajectoryResponse.getPeriodSeconds()",
      "List OrbitalTrajectoryResponse.getPoints3D()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<Double> cartesianValues = new ArrayList<>();
    ArrayList<Point3D> points3D = new ArrayList<>();

    // Act
    OrbitalTrajectoryResponse actualOrbitalTrajectoryResponse = new OrbitalTrajectoryResponse(cartesianValues, points3D,
        10.0d);
    List<Double> actualCartesianValues = actualOrbitalTrajectoryResponse.getCartesianValues();
    double actualPeriodSeconds = actualOrbitalTrajectoryResponse.getPeriodSeconds();
    List<Point3D> actualPoints3D = actualOrbitalTrajectoryResponse.getPoints3D();

    // Assert
    assertEquals(10.0d, actualPeriodSeconds);
    assertTrue(actualCartesianValues.isEmpty());
    assertTrue(actualPoints3D.isEmpty());
    assertSame(cartesianValues, actualCartesianValues);
    assertSame(points3D, actualPoints3D);
  }
}
