package Exercise.UserInterface;

import Exercise.Domain.StreetSegment;
import java.util.Scanner;

public class StreetConsoleMenu {
    private StreetSegment street;
    private Scanner scanner = new Scanner(System.in);

    public StreetConsoleMenu(StreetSegment street) {
        this.street = street;
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            executeAction(choice);
            if (choice == 0) {
                break;
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nМеню вулиці '" + street.getName() + "':");
        System.out.println("1. Показати всі будівлі");
        System.out.println("2. Додати будівлю");
        System.out.println("3. Видалити будівлю");
        System.out.println("0. Вийти");
        System.out.print("Ваш вибір: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Будь ласка, введіть число.");
            scanner.next();
            System.out.print("Ваш вибір: ");
        }
        return scanner.nextInt();
    }

    private void executeAction(int choice) {
        scanner.nextLine();
        switch (choice) {
            case 1:
                street.displayBuildings();
                break;
            case 2:
                System.out.print("Введіть назву будівлі для додавання: ");
                String buildingToAdd = scanner.nextLine();
                street.addBuilding(buildingToAdd);
                System.out.println("Будівлю '" + buildingToAdd + "' додано.");
                break;
            case 3:
                System.out.print("Введіть назву будівлі для видалення: ");
                String buildingToRemove = scanner.nextLine();
                street.removeBuilding(buildingToRemove);
                System.out.println("Будівлю '" + buildingToRemove + "' видалено (якщо існувала).");
                break;
            case 0:
                System.out.println("Робота з вулицею завершена.");
                break;
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }
}