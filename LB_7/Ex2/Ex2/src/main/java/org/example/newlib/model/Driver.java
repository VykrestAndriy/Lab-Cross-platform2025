package org.example.newlib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private Long id;
    private String name;
    private int experienceYears;
    private String licenseCategory;
    private boolean isAvailable;
    private double salary;

    //  Методи (приклад):
    public boolean canDrive(String requiredCategory) {
        return licenseCategory.contains(requiredCategory);
    }

    public void addExperience(int years) {
        this.experienceYears += years;
    }
}