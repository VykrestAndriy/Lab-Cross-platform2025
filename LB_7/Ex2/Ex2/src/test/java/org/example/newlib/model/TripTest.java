package org.example.newlib.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TripTest {

    private Trip trip;
    private Request request;
    private Driver driver;
    private Car car;

    @BeforeEach
    void setUp() {
        request = new Request(1L, "TestDestination", 1000, "TestCargo");
        driver = new Driver(1L, "TestDriver", 5, "BC", true, 25000);
        car = new Car(101L, "TestCar", 1000, "TestType", true, 50000);
        trip = new Trip(1L, request, driver, car, LocalDate.now(), LocalDate.now().plusDays(1), 100, true, "Good");
    }

    @Test
    void testCalculatePayment() {
        assertEquals(100 * 10.5, trip.calculatePayment(), 0.01);
    }

    @Test
    void testTripCreation() {
        assertNotNull(trip);
        assertEquals(request, trip.getRequest());
        assertEquals(driver, trip.getDriver());
        assertEquals(car, trip.getCar());
    }
}