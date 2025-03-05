import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise_2 {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу: ");
        String filePath = inputScanner.nextLine();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файл не знайдений.");
                return;
            }

            Scanner scanner = new Scanner(file);
            int distanceAB = scanner.nextInt();
            int distanceBC = scanner.nextInt();
            int weight = scanner.nextInt();
            int tankCapacity = scanner.nextInt();

            // Визначення споживання палива
            int fuelConsumptionPerKm = (weight <= 500) ? 1 :
                    (weight <= 1000) ? 4 :
                            (weight <= 1500) ? 7 :
                                    (weight <= 2000) ? 9 : -1;

            if (fuelConsumptionPerKm == -1) {
                System.out.println("Літак не може підняти вантаж більше 2000 кг.");
                return;
            }

            int fuelForAB = distanceAB * fuelConsumptionPerKm;
            int fuelForBC = distanceBC * fuelConsumptionPerKm;

            if (fuelForAB > tankCapacity) {
                System.out.println("Неможливо долетіти з пункту А до пункту В.");
            } else {
                int remainingFuel = tankCapacity - fuelForAB;
                System.out.println(fuelForBC > remainingFuel
                        ? "Необхідно дозаправити " + (fuelForBC - remainingFuel) + " літрів палива в пункті В."
                        : "Палива достатньо для перельоту без дозаправки.");
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні файлу: " + e.getMessage());
        } finally {
            inputScanner.close();
        }
    }
}
