package org.example.newlib.service;

import org.example.newlib.model.Car;
import org.example.newlib.model.Driver;
import org.example.newlib.model.Request;
import org.example.newlib.model.Trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Dispatcher {

    private List<Driver> availableDrivers;
    private List<Car> availableCars;
    private List<Trip> activeTrips;

    public Dispatcher(List<Driver> drivers, List<Car> cars) {
        this.availableDrivers = drivers.stream().filter(Driver::isAvailable).collect(Collectors.toList());
        this.availableCars = cars.stream().filter(Car::isAvailable).collect(Collectors.toList());
        this.activeTrips = new ArrayList<>();
    }

    public Trip assignTrip(Request request) {
        Driver driver = findSuitableDriver(request);
        Car car = findSuitableCar(request);

        if (driver != null && car != null) {
            driver.setAvailable(false);
            car.setAvailable(false);

            Random random = new Random();
            double distance = random.nextDouble() * 500 + 50;
            Trip trip = new Trip(null, request, driver, car, LocalDate.now(), null, distance, false, null);
            trip.setId((long) (activeTrips.size() + 1));
            activeTrips.add(trip);
            return trip;
        } else {
            return null;
        }
    }

    private Driver findSuitableDriver(Request request) {
        return availableDrivers.stream()
                .filter(Driver::isAvailable)
                .filter(driver -> driver.canDrive(request.getCargoType()))
                .min(Comparator.comparingInt(Driver::getExperienceYears))
                .orElse(null);
    }

    private Car findSuitableCar(Request request) {
        return availableCars.stream()
                .filter(Car::isAvailable)
                .filter(car -> car.canCarry(request.getCargoWeight()))
                .max(Comparator.comparingInt(Car::getLoadCapacity))
                .orElse(null);
    }

    public void completeTrip(Trip trip, String carCondition) {
        trip.setCompleted(true);
        trip.setEndDate(LocalDate.now());
        trip.setCarCondition(carCondition);

        trip.getDriver().setAvailable(true);
        trip.getCar().setAvailable(true);
        trip.getCar().addMileage(trip.getDistance());

        availableDrivers.add(trip.getDriver());
        availableCars.add(trip.getCar());
    }

    public List<Trip> getActiveTrips() {
        return activeTrips;
    }
}