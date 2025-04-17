package Exercise.Utilities;

import java.util.Scanner;

public class InputHelper {
    private Scanner scanner;

    public InputHelper() {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Будь ласка, введіть ціле число.");
            scanner.next();
            System.out.print("Введіть значення: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Будь ласка, введіть число.");
            scanner.next();
            System.out.print("Введіть значення: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}