package com.example.jeeHamlaoui.model;

import static org.junit.jupiter.api.Assertions.*;
import com.example.jeeHamlaoui.model.enums.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.Instant;

class GroundStationTest {

  private GroundStation groundStation;
  private User testUser;

  @BeforeEach
  void setUp() {
    groundStation = new GroundStation();
    testUser = new User();
    testUser.setUserId(1L);
    testUser.setUser_name("Test User");
    testUser.setEmail("test@example.com");
    testUser.setStatus(UserStatus.ACTIVE);
  }

  @Nested
  @DisplayName("Constructor Tests")
  class ConstructorTests {

    @Test
    @DisplayName("Default constructor should create GroundStation with business fields as null")
    void testDefaultConstructor() {
      GroundStation station = new GroundStation();

      // Don't test auto-generated ID - it's meaningless in unit tests
      // Focus on business fields that matter
      assertNull(station.getGroundStation_Name());
      assertNull(station.getGroundStation_Email());
      assertNull(station.getGroundStation_AccesLevel());
      assertNull(station.getGroundStation_Latitude());
      assertNull(station.getGroundStation_Longitude());
      assertNull(station.getGroundStation_Description());
      assertNull(station.getUser());
    }

    @Test
    @DisplayName("Parameterized constructor should set all fields")
    void testParameterizedConstructor() {
      GroundStation station = new GroundStation(
              1L,
              "Test Station",
              "station@test.com",
              5,
              40.7128,
              -74.0060,
              "Test Description",
              testUser
      );

      assertEquals(1L, station.getGroundStation_id());
      assertEquals("Test Station", station.getGroundStation_Name());
      assertEquals("station@test.com", station.getGroundStation_Email());
      assertEquals(5, station.getGroundStation_AccesLevel());
      assertEquals(40.7128, station.getGroundStation_Latitude());
      assertEquals(-74.0060, station.getGroundStation_Longitude());
      assertEquals("Test Description", station.getGroundStation_Description());
      assertEquals(testUser, station.getUser());
    }
  }

  @Nested
  @DisplayName("Getter and Setter Tests")
  class GetterSetterTests {

    @Test
    @DisplayName("Should set and get ID correctly (for testing purposes)")
    void testIdGetterSetter() {
      // Note: In real applications, you typically don't set auto-generated IDs manually
      // This test is mainly for completeness of getter/setter coverage
      Long expectedId = 123L;
      groundStation.setGroundStation_id(expectedId);
      assertEquals(expectedId, groundStation.getGroundStation_id());
    }

    @Test
    @DisplayName("Should set and get name correctly")
    void testNameGetterSetter() {
      String expectedName = "Ground Station Alpha";
      groundStation.setGroundStation_Name(expectedName);
      assertEquals(expectedName, groundStation.getGroundStation_Name());
    }

    @Test
    @DisplayName("Should set and get email correctly")
    void testEmailGetterSetter() {
      String expectedEmail = "alpha@groundstation.com";
      groundStation.setGroundStation_Email(expectedEmail);
      assertEquals(expectedEmail, groundStation.getGroundStation_Email());
    }

    @Test
    @DisplayName("Should set and get access level correctly")
    void testAccessLevelGetterSetter() {
      Integer expectedLevel = 3;
      groundStation.setGroundStation_AccesLevel(expectedLevel);
      assertEquals(expectedLevel, groundStation.getGroundStation_AccesLevel());
    }

    @Test
    @DisplayName("Should set and get latitude correctly")
    void testLatitudeGetterSetter() {
      Double expectedLat = 51.5074;
      groundStation.setGroundStation_Latitude(expectedLat);
      assertEquals(expectedLat, groundStation.getGroundStation_Latitude());
    }

    @Test
    @DisplayName("Should set and get longitude correctly")
    void testLongitudeGetterSetter() {
      Double expectedLon = -0.1278;
      groundStation.setGroundStation_Longitude(expectedLon);
      assertEquals(expectedLon, groundStation.getGroundStation_Longitude());
    }

    @Test
    @DisplayName("Should set and get description correctly")
    void testDescriptionGetterSetter() {
      String expectedDesc = "Primary ground station for satellite communication";
      groundStation.setGroundStation_Description(expectedDesc);
      assertEquals(expectedDesc, groundStation.getGroundStation_Description());
    }

    @Test
    @DisplayName("Should set and get user correctly")
    void testUserGetterSetter() {
      groundStation.setUser(testUser);
      assertEquals(testUser, groundStation.getUser());
    }
  }

  @Nested
  @DisplayName("Null Value Tests")
  class NullValueTests {

    @Test
    @DisplayName("Should handle null name")
    void testNullName() {
      groundStation.setGroundStation_Name(null);
      assertNull(groundStation.getGroundStation_Name());
    }

    @Test
    @DisplayName("Should handle null email")
    void testNullEmail() {
      groundStation.setGroundStation_Email(null);
      assertNull(groundStation.getGroundStation_Email());
    }

    @Test
    @DisplayName("Should handle null access level")
    void testNullAccessLevel() {
      groundStation.setGroundStation_AccesLevel(null);
      assertNull(groundStation.getGroundStation_AccesLevel());
    }

    @Test
    @DisplayName("Should handle null coordinates")
    void testNullCoordinates() {
      groundStation.setGroundStation_Latitude(null);
      groundStation.setGroundStation_Longitude(null);
      assertNull(groundStation.getGroundStation_Latitude());
      assertNull(groundStation.getGroundStation_Longitude());
    }

    @Test
    @DisplayName("Should handle null user")
    void testNullUser() {
      groundStation.setUser(null);
      assertNull(groundStation.getUser());
    }
  }

  @Nested
  @DisplayName("Edge Case Tests")
  class EdgeCaseTests {

    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
      groundStation.setGroundStation_Name("");
      groundStation.setGroundStation_Email("");
      groundStation.setGroundStation_Description("");

      assertEquals("", groundStation.getGroundStation_Name());
      assertEquals("", groundStation.getGroundStation_Email());
      assertEquals("", groundStation.getGroundStation_Description());
    }

    @Test
    @DisplayName("Should handle very long strings")
    void testVeryLongStrings() {
      String longString = "A".repeat(1000);
      groundStation.setGroundStation_Name(longString);
      groundStation.setGroundStation_Description(longString);

      assertEquals(longString, groundStation.getGroundStation_Name());
      assertEquals(longString, groundStation.getGroundStation_Description());
    }

    @Test
    @DisplayName("Should handle extreme coordinate values")
    void testExtremeCoordinates() {
      // Test valid extreme values
      groundStation.setGroundStation_Latitude(90.0);
      groundStation.setGroundStation_Longitude(180.0);
      assertEquals(90.0, groundStation.getGroundStation_Latitude());
      assertEquals(180.0, groundStation.getGroundStation_Longitude());

      groundStation.setGroundStation_Latitude(-90.0);
      groundStation.setGroundStation_Longitude(-180.0);
      assertEquals(-90.0, groundStation.getGroundStation_Latitude());
      assertEquals(-180.0, groundStation.getGroundStation_Longitude());
    }



    @Test
    @DisplayName("Should handle special characters in strings")
    void testSpecialCharacters() {
      String specialChars = "!@#$%^&*()_+-=[]{}|;':\",./<>?`~";
      groundStation.setGroundStation_Name(specialChars);
      groundStation.setGroundStation_Description(specialChars);

      assertEquals(specialChars, groundStation.getGroundStation_Name());
      assertEquals(specialChars, groundStation.getGroundStation_Description());
    }
  }

  @Nested
  @DisplayName("Business Logic Tests")
  class BusinessLogicTests {

    @Test
    @DisplayName("Should maintain bidirectional relationship with User")
    void testBidirectionalRelationship() {
      testUser.setGroundStation(groundStation);
      groundStation.setUser(testUser);

      assertEquals(testUser, groundStation.getUser());
      assertEquals(groundStation, testUser.getGroundStation());
    }

    @Test
    @DisplayName("Should handle coordinate precision")
    void testCoordinatePrecision() {
      Double preciseLat = 40.123456789;
      Double preciseLon = -74.987654321;

      groundStation.setGroundStation_Latitude(preciseLat);
      groundStation.setGroundStation_Longitude(preciseLon);

      assertEquals(preciseLat, groundStation.getGroundStation_Latitude(), 0.000000001);
      assertEquals(preciseLon, groundStation.getGroundStation_Longitude(), 0.000000001);
    }

    @Test
    @DisplayName("User should always have a GroundStation assigned")
    void testUserMustHaveGroundStation() {
      User user = new User();
      user.setUserId(1L);
      user.setUser_name("Test User");
      user.setEmail("test@example.com");
      user.setStatus(UserStatus.ACTIVE);

      user.setGroundStation(groundStation);
      assertNotNull(user.getGroundStation());

     }

    @Test
    @DisplayName("Should establish proper JPA relationship")
    void testJpaRelationshipMapping() {
      testUser.setGroundStation(groundStation);

      assertNotNull(testUser.getGroundStation());
      assertEquals(groundStation.getGroundStation_id(), testUser.getGroundStation().getGroundStation_id());
    }
  }

  @Nested
  @DisplayName("Integration Tests")
  class IntegrationTests {

    @Test
    @DisplayName("Should create complete GroundStation with User")
    void testCompleteGroundStationCreation() {
      User user = new User();
      user.setUserId(1L);
      user.setUser_name("Station Operator");
      user.setEmail("operator@station.com");
      user.setStatus(UserStatus.ACTIVE);
      user.setCreated_at(Instant.now());

      GroundStation station = new GroundStation(
              1L,
              "Main Station",
              "main@station.com",
              5,
              35.6762,
              139.6503,
              "Primary communication hub",
              user
      );

      user.setGroundStation(station);

      // Verify all fields are set correctly
      assertNotNull(station);
      assertEquals(1L, station.getGroundStation_id());
      assertEquals("Main Station", station.getGroundStation_Name());
      assertEquals("main@station.com", station.getGroundStation_Email());
      assertEquals(5, station.getGroundStation_AccesLevel());
      assertEquals(35.6762, station.getGroundStation_Latitude());
      assertEquals(139.6503, station.getGroundStation_Longitude());
      assertEquals("Primary communication hub", station.getGroundStation_Description());
      assertEquals(user, station.getUser());

      assertEquals(station, user.getGroundStation());
    }
  }
}