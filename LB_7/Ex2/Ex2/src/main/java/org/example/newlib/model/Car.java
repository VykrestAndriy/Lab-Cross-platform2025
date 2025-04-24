package org.example.newlib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String model;
    private int loadCapacity;
    private String type;
    private boolean isAvailable;
    private double mileage;

    public boolean canCarry(int weight) {
        return loadCapacity >= weight;
    }

    public void addMileage(double distance) {
        this.mileage += distance;
    }
}