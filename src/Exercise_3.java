import java.util.Scanner;

public class Exercise_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] menu = {"Кава - 50 грн", "Чай - 30 грн", "Круасан - 40 грн", "Торт - 60 грн", "Сік - 25 грн"};
        double[] prices = {50, 30, 40, 60, 25};

        System.out.print("Введіть кількість осіб у компанії: ");
        int people = scanner.nextInt();
        double totalAmount = 0;

        for (int i = 1; i <= people; i++) {
            double clientTotal = 0;
            System.out.println("\nКлієнт " + i + ":");
            while (true) {
                System.out.println("Меню:");
                for (int j = 0; j < menu.length; j++) {
                    System.out.println((j + 1) + ". " + menu[j]);
                }
                System.out.print("Оберіть номер страви (1-5) або 0 для завершення: ");
                int choice = scanner.nextInt();
                if (choice == 0) break;
                if (choice >= 1 && choice <= 5) {
                    clientTotal += prices[choice - 1];
                } else {
                    System.out.println("Невірний вибір.");
                }
            }
            System.out.println("Загальна сума для клієнта " + i + ": " + clientTotal + " грн");
            totalAmount += clientTotal;
        }

        System.out.println("\nЗагальна сума для всієї компанії: " + totalAmount + " грн");
        scanner.close();
    }
}
