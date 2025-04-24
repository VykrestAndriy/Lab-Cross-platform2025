package org.example.newlib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private Long id;
    private Request request;
    private Driver driver;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private double distance;
    private boolean isCompleted;
    private String carCondition;

    public double calculatePayment() {
        return distance * 10.5;
    }
}