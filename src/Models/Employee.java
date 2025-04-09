package LB_5.Models;

import java.io.Serializable;

public class Employee implements Serializable {
    private String familyName;
    private String givenName;
    private int yearsOfAge;
    private String assignedPosition;

    public Employee(String familyName, String givenName, int yearsOfAge, String assignedPosition) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.yearsOfAge = yearsOfAge;
        this.assignedPosition = assignedPosition;
    }

    public String getSurname() {
        return familyName;
    }

    public String getName() {
        return givenName;
    }

    public int getAge() {
        return yearsOfAge;
    }

    public String getPosition() {
        return assignedPosition;
    }

    public void setSurname(String familyName) {
        this.familyName = familyName;
    }

    public void setName(String givenName) {
        this.givenName = givenName;
    }

    public void setAge(int yearsOfAge) {
        this.yearsOfAge = yearsOfAge;
    }

    public void setPosition(String assignedPosition) {
        this.assignedPosition = assignedPosition;
    }

    @Override
    public String toString() {
        return "Прізвище: " + familyName + ", Ім'я: " + givenName + ", Вік: " + yearsOfAge + ", Посада: " + assignedPosition;
    }
}