package org.example.newlib.service;

import org.example.newlib.model.Car;
import org.example.newlib.model.Driver;
import org.example.newlib.model.Request;
import org.example.newlib.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DispatcherTest {

    private Dispatcher dispatcher;
    private List<Driver> drivers;
    private List<Car> cars;
    private Request request;

    @BeforeEach
    void setUp() {
        drivers = new ArrayList<>();
        cars = new ArrayList<>();

        drivers.add(new Driver(1L, "TestDriver", 5, "BC", true, 25000));
        cars.add(new Car(101L, "TestCar", 1000, "TestType", true, 50000));

        dispatcher = new Dispatcher(drivers, cars);
        request = new Request(1L, "TestDestination", 500, "B");
    }

    @Test
    void testAssignTrip() {
        Trip trip = dispatcher.assignTrip(request);
        assertNotNull(trip);
        assertEquals("TestDriver", trip.getDriver().getName());
        assertEquals("TestCar", trip.getCar().getModel());
        assertFalse(trip.getDriver().isAvailable());
        assertFalse(trip.getCar().isAvailable());
    }

    @Test
    void testAssignTripNoAvailableResources() {
        drivers.get(0).setAvailable(false);
        cars.get(0).setAvailable(false);

        Trip trip = dispatcher.assignTrip(request);
        assertNull(trip);
    }

    @Test
    void testCompleteTrip() {
        Trip trip = dispatcher.assignTrip(request);
        assertNotNull(trip);

        dispatcher.completeTrip(trip, "OK");

        assertTrue(trip.isCompleted());
        assertTrue(trip.getDriver().isAvailable());
        assertTrue(trip.getCar().isAvailable());
        assertEquals("OK", trip.getCarCondition());
    }
}