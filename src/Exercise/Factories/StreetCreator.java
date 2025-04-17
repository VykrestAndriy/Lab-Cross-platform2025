package Exercise.Factories;

import Exercise.Domain.StreetSegment;
import java.util.Random;

public class StreetCreator {
    public static StreetSegment createTestStreet(String name) {
        StreetSegment street = new StreetSegment(name);
        Random random = new Random();
        int numberOfBuildings = random.nextInt(5) + 3;
        for (int i = 1; i <= numberOfBuildings; i++) {
            street.addBuilding("Будівля №" + i);
        }
        return street;
    }
}