package org.example.newlib.service;

import org.example.newlib.model.Request;
import org.example.newlib.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TripServiceTest {

    private TripService tripService;
    private Trip trip;

    @BeforeEach
    void setUp() {
        tripService = new TripService();
        Request request = new Request(1L, "TestDestination", 1000, "TestCargo");
        trip = new Trip(1L, request, null, null, LocalDate.now(), LocalDate.now().plusDays(1), 100, true, "Good");
    }

    @Test
    void testCalculateTripCost() {
        double expectedCost = 100 * 2.5 + 1000 * 0.1;
        assertEquals(expectedCost, tripService.calculateTripCost(trip), 0.01);
    }
}