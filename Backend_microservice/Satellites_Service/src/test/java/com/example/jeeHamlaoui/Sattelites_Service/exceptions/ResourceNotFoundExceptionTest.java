package com.example.jeeHamlaoui.Sattelites_Service.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {
  /**
   * Test {@link ResourceNotFoundException#ResourceNotFoundException(String)}.
   * <p>
   * Method under test: {@link ResourceNotFoundException#ResourceNotFoundException(String)}
   */
  @Test
  @DisplayName("Test new ResourceNotFoundException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResourceNotFoundException.<init>(String)"})
  void testNewResourceNotFoundException() {
    // Arrange and Act
    ResourceNotFoundException actualResourceNotFoundException = new ResourceNotFoundException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualResourceNotFoundException.getMessage());
    assertNull(actualResourceNotFoundException.getCause());
    assertEquals(0, actualResourceNotFoundException.getSuppressed().length);
  }
}
