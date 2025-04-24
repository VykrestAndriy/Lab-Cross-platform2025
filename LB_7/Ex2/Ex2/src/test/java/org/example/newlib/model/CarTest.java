package org.example.newlib.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car(101L, "TestCar", 1000, "TestType", true, 50000);
    }

    @Test
    void testCanCarry() {
        assertTrue(car.canCarry(500));
        assertFalse(car.canCarry(1500));
    }

    @Test
    void testAddMileage() {
        car.addMileage(100);
        assertEquals(50100, car.getMileage());
    }

    @Test
    void testCarCreation() {
        assertNotNull(car);
        assertEquals("TestCar", car.getModel());
        assertEquals(1000, car.getLoadCapacity());
    }
}