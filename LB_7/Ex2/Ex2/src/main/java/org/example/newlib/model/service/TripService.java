package org.example.newlib.service;

import org.example.newlib.model.Trip;

public class TripService {

    public double calculateTripCost(Trip trip) {
        return trip.getDistance() * 2.5 + trip.getRequest().getCargoWeight() * 0.1;
    }
}