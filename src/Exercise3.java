import java.io.*;
import java.util.*;

import LB_5.Models.Employee;

public class Exercise3 {
    private static List<Employee> roster = new ArrayList<>();
    private static String dataLocation = "./src/Lab5/Files/Employees.dat";
    private static boolean changedData = false;

    public static void main(String[] args) {
        retrieveEmployeeData();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.println("\nОсновне меню:");
            System.out.println("1. Додати нового працівника");
            System.out.println("2. Оновити інформацію про працівника");
            System.out.println("3. Видалити працівника");
            System.out.println("4. Знайти працівника за прізвищем");
            System.out.println("5. Показати всіх працівників (за віком)");
            System.out.println("6. Відобразити працівників за першою літерою прізвища");
            System.out.println("7. Експортувати звіт до файлу");
            System.out.println("8. Зберегти внесені зміни");
            System.out.println("9. Завершити програму");

            System.out.println("Введіть код операції: ");
            int choice = userInput.nextInt();
            userInput.nextLine();

            switch (choice) {
                case 1:
                    createEmployee(userInput);
                    break;
                case 2:
                    updateEmployee(userInput);
                    break;
                case 3:
                    fireEmployee(userInput);
                    break;
                case 4:
                    findEmployeeByFamilyName(userInput);
                    break;
                case 5:
                    displayStaffSortedByAge();
                    break;
                case 6:
                    showStaffWithInitial(userInput);
                    break;
                case 7:
                    generateStaffListReport(userInput);
                    break;
                case 8:
                    persistEmployeeData();
                    break;
                case 9:
                    shutdownProgram();
                    return;
                default:
                    System.out.println("Невідома команда.");
            }
        }
    }

    private static void retrieveEmployeeData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataLocation))) {
            roster = (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Файл даних не знайдено або пошкоджено. Ініціалізовано новий список працівників.");
        }
    }

    private static void persistEmployeeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataLocation))) {
            oos.writeObject(roster);
            changedData = false;
            System.out.println("Поточні дані працівників збережено.");
        } catch (IOException e) {
            System.err.println("Помилка при збереженні даних: " + e.getMessage());
        }
    }

    private static void createEmployee(Scanner scanner) {
        System.out.print("Введіть прізвище: ");
        String lastName = scanner.nextLine();
        System.out.print("Введіть ім'я: ");
        String givenName = scanner.nextLine();
        System.out.print("Введіть вік: ");
        int personAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введіть посаду: ");
        String jobTitle = scanner.nextLine();

        roster.add(new Employee(lastName, givenName, personAge, jobTitle));
        changedData = true;
        System.out.println("Працівника додано до списку.");
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Введіть прізвище працівника для оновлення: ");
        String surnameToEdit = scanner.nextLine();

        for (int i = 0; i < roster.size(); i++) {
            if (roster.get(i).getSurname().equalsIgnoreCase(surnameToEdit)) {
                System.out.println("Редагування інформації про: " + roster.get(i));
                System.out.print("Введіть нове ім'я: ");
                roster.get(i).setName(scanner.nextLine());
                System.out.print("Введіть новий вік: ");
                roster.get(i).setAge(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Введіть нову посаду: ");
                roster.get(i).setPosition(scanner.nextLine());
                changedData = true;
                System.out.println("Інформацію про працівника оновлено.");
                return;
            }
        }
        System.out.println("Працівника з таким прізвищем не знайдено.");
    }

    private static void fireEmployee(Scanner scanner) {
        System.out.print("Введіть прізвище працівника для звільнення: ");
        String surnameToRemove = scanner.nextLine();

        roster.removeIf(employee -> employee.getSurname().equalsIgnoreCase(surnameToRemove));
        changedData = true;
        System.out.println("Працівника звільнено.");
    }

    private static void findEmployeeByFamilyName(Scanner scanner) {
        System.out.print("Введіть прізвище для пошуку: ");
        String searchSurname = scanner.nextLine();

        roster.stream()
                .filter(employee -> employee.getSurname().equalsIgnoreCase(searchSurname))
                .forEach(System.out::println);
    }

    private static void displayStaffSortedByAge() {
        roster.stream()
                .sorted(Comparator.comparingInt(Employee::getAge))
                .forEach(System.out::println);
    }

    private static void showStaffWithInitial(Scanner scanner) {
        System.out.print("Введіть першу літеру прізвища: ");
        String initial = scanner.nextLine().toUpperCase();

        roster.stream()
                .filter(employee -> employee.getSurname().toUpperCase().startsWith(initial))
                .forEach(System.out::println);
    }

    private static void generateStaffListReport(Scanner scanner) {
        System.out.print("Введіть ім'я файлу для звіту: ");
        String reportFileName = scanner.nextLine();

        String reportDirectory = "src/Lab5/Files";

        File reportFolder = new File(reportDirectory);

        if (!reportFolder.exists()) {
            if (!reportFolder.mkdirs()) {
                System.err.println("Помилка створення папки для звітів: " + reportDirectory);
                return;
            }
        }

        File reportFile = new File(reportFolder, reportFileName);

        try (PrintWriter writer = new PrintWriter(reportFile)) {
            roster.forEach(writer::println);
            System.out.println("Звіт про працівників збережено у файл: " + reportFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.err.println("Помилка при збереженні звіту: " + e.getMessage());
        }
    }

    private static void shutdownProgram() {
        if (changedData) {
            persistEmployeeData();
        }
        System.out.println("Роботу програми завершено.");
    }
}