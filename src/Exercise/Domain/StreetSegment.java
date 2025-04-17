package Exercise.Domain;

import java.util.ArrayList;
import java.util.List;

public class StreetSegment {
    private String name;
    private List<String> buildings;

    public StreetSegment(String name) {
        this.name = name;
        this.buildings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getBuildings() {
        return buildings;
    }

    public void addBuilding(String building) {
        buildings.add(building);
    }

    public void removeBuilding(String building) {
        buildings.remove(building);
    }

    public void displayBuildings() {
        if (buildings.isEmpty()) {
            System.out.println("На вулиці '" + name + "' немає будівель.");
        } else {
            System.out.println("Будівлі на вулиці '" + name + "':");
            for (String building : buildings) {
                System.out.println("- " + building);
            }
        }
    }
}