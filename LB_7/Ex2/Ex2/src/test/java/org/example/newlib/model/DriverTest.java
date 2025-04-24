package org.example.newlib.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {

    private Driver driver;

    @BeforeEach
    void setUp() {
        driver = new Driver(1L, "TestDriver", 5, "BC", true, 25000);
    }

    @Test
    void testCanDrive() {
        assertTrue(driver.canDrive("B"));
        assertTrue(driver.canDrive("C"));
        assertFalse(driver.canDrive("E"));
    }

    @Test
    void testAddExperience() {
        driver.addExperience(2);
        assertEquals(7, driver.getExperienceYears());
    }

    @Test
    void testDriverCreation() {
        assertNotNull(driver);
        assertEquals("TestDriver", driver.getName());
        assertEquals(5, driver.getExperienceYears());
    }
}