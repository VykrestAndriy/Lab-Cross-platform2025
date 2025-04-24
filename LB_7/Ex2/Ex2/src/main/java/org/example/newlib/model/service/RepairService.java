package org.example.newlib.service;

import org.example.newlib.model.Car;
import org.example.newlib.model.Driver;

public class RepairService {

    public void requestRepair(Driver driver, Car car, String description) {
        System.out.println("Заявка на ремонт: Водій " + driver.getName() + ", Автомобіль " + car.getModel() + ", Опис: " + description);
    }

    public void performRepair(Car car) {
        System.out.println("Виконується ремонт автомобіля " + car.getModel());
        car.setAvailable(true);