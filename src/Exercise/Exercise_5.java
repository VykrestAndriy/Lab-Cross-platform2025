package Exercise;

import Exercise.Domain.StreetSegment;
import Exercise.Factories.StreetCreator;
import Exercise.UserInterface.StreetConsoleMenu;

public class Exercise_5 {
    public static void main(String[] args) {
        StreetSegment headSegment = StreetCreator.createTestStreet("вулиця Космонавтів");

        StreetConsoleMenu streetMenu = new StreetConsoleMenu(headSegment);

        streetMenu.run();
    }
}