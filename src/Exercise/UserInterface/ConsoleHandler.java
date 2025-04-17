package Exercise.UserInterface;

import Exercise.Services.DictionaryService;
import java.util.Scanner;

public class ConsoleHandler {
    private DictionaryService dictionaryService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleHandler(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            executeAction(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\nОберіть дію зі словником:");
        System.out.println("1. Додати слово");
        System.out.println("2. Видалити слово");
        System.out.println("3. Перевірити наявність слова");
        System.out.println("4. Показати всі слова");
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
                System.out.print("Введіть слово для додавання: ");
                String wordToAdd = scanner.nextLine();
                dictionaryService.add(wordToAdd);
                System.out.println("Слово '" + wordToAdd + "' додано до словника.");
                break;
            case 2:
                System.out.print("Введіть слово для видалення: ");
                String wordToRemove = scanner.nextLine();
                if (dictionaryService.remove(wordToRemove)) {
                    System.out.println("Слово '" + wordToRemove + "' видалено зі словника.");
                } else {
                    System.out.println("Слово '" + wordToRemove + "' не знайдено в словнику.");
                }
                break;
            case 3:
                System.out.print("Введіть слово для перевірки: ");
                String wordToCheck = scanner.nextLine();
                if (dictionaryService.check(wordToCheck)) {
                    System.out.println("Слово '" + wordToCheck + "' є в словнику.");
                } else {
                    System.out.println("Слово '" + wordToCheck + "' відсутнє в словнику.");
                }
                break;
            case 4:
                dictionaryService.listAllWords();
                break;
            case 0:
                System.out.println("Програма завершена.");
                System.exit(0);
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }
}