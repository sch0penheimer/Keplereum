package com.example.jeeHamlaoui.Sattelites_Service.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Point3DTest {
  /**
   * Test {@link Point3D#Point3D(double, double, double)}.
   * <p>
   * Method under test: {@link Point3D#Point3D(double, double, double)}
   */
  @Test
  @DisplayName("Test new Point3D(double, double, double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Point3D.<init>(double, double, double)"})
  void testNewPoint3D() {
    // Arrange and Act
    Point3D actualPoint3D = new Point3D(2.0d, 3.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualPoint3D.getZ());
    assertEquals(2.0d, actualPoint3D.getX());
    assertEquals(3.0d, actualPoint3D.getY());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Point3D#getX()}
   *   <li>{@link Point3D#getY()}
   *   <li>{@link Point3D#getZ()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double Point3D.getX()", "double Point3D.getY()", "double Point3D.getZ()"})
  void testGettersAndSetters() {
    // Arrange
    Point3D point3D = new Point3D(2.0d, 3.0d, 10.0d);

    // Act
    double actualX = point3D.getX();
    double actualY = point3D.getY();

    // Assert
    assertEquals(10.0d, point3D.getZ());
    assertEquals(2.0d, actualX);
    assertEquals(3.0d, actualY);
  }
}
