package Exercise.Entities;

import java.time.LocalDateTime;

public class Client {
    private String firstName;
    private String lastName;
    private LocalDateTime arrivalTime;

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(String name) {
        this.firstName = name;
        this.lastName = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return (lastName.isEmpty() ? firstName : firstName + " " + lastName);
    }
}