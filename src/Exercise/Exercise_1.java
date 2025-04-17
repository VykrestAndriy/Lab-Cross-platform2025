package Exercise;

import Exercise.Models.AppUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_1 {

    private static List<AppUser> users = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getUserChoice();
            processAction(choice);
        }
    }

    private static void showMenu() {
        System.out.println("\nОберіть дію:");
        System.out.println("1. Зареєструвати нового користувача");
        System.out.println("2. Видалити користувача за логіном");
        System.out.println("3. Перевірити наявність користувача за логіном");
        System.out.println("4. Оновити логін користувача");
        System.out.println("5. Змінити пароль користувача за логіном");
        System.out.println("0. Завершити програму");
        System.out.print("Ваш вибір: ");
    }

    private static int getUserChoice() {
        while (!input.hasNextInt()) {
            System.out.println("Будь ласка, введіть число.");
            input.next();
            System.out.print("Ваш вибір: ");
        }
        return input.nextInt();
    }

    private static void processAction(int choice) {
        input.nextLine();
        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                deleteUser();
                break;
            case 3:
                checkIfUserExists();
                break;
            case 4:
                updateLogin();
                break;
            case 5:
                updatePassword();
                break;
            case 0:
                System.out.println("До побачення!");
                System.exit(0);
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }

    private static void registerUser() {
        System.out.print("Введіть бажаний логін: ");
        String newLogin = input.nextLine();
        if (findUserByLogin(newLogin) != null) {
            System.out.println("Користувач з таким логіном вже зареєстрований.");
            return;
        }
        System.out.print("Введіть пароль для логіна " + newLogin + ": ");
        String newPassword = input.nextLine();
        AppUser newUser = new AppUser(newLogin, newPassword);
        users.add(newUser);
        System.out.println("Користувача " + newLogin + " успішно зареєстровано.");
    }

    private static void deleteUser() {
        System.out.print("Введіть логін користувача, якого потрібно видалити: ");
        String loginToDelete = input.nextLine();
        AppUser userToRemove = findUserByLogin(loginToDelete);
        if (userToRemove != null) {
            users.remove(userToRemove);
            System.out.println("Користувача з логіном " + loginToDelete + " видалено.");
        } else {
            System.out.println("Користувача з логіном " + loginToDelete + " не знайдено.");
        }
    }

    private static void checkIfUserExists() {
        System.out.print("Введіть логін користувача для перевірки: ");
        String loginToCheck = input.nextLine();
        if (findUserByLogin(loginToCheck) != null) {
            System.out.println("Користувач з логіном " + loginToCheck + " існує.");
        } else {
            System.out.println("Користувача з логіном " + loginToCheck + " не знайдено.");
        }
    }

    private static void updateLogin() {
        System.out.print("Введіть поточний логін користувача, який потрібно змінити: ");
        String currentLogin = input.nextLine();
        AppUser userToUpdate = findUserByLogin(currentLogin);
        if (userToUpdate != null) {
            System.out.print("Введіть новий логін: ");
            String newLogin = input.nextLine();
            if (findUserByLogin(newLogin) == null) {
                userToUpdate.setLogin(newLogin);
                System.out.println("Логін користувача " + currentLogin + " змінено на " + newLogin + ".");
            } else {
                System.out.println("Користувач з таким новим логіном вже існує.");
            }
        } else {
            System.out.println("Користувача з логіном " + currentLogin + " не знайдено.");
        }
    }

    private static void updatePassword() {
        System.out.print("Введіть логін користувача, пароль якого потрібно змінити: ");
        String loginToUpdate = input.nextLine();
        AppUser userToUpdate = findUserByLogin(loginToUpdate);
        if (userToUpdate != null) {
            System.out.print("Введіть новий пароль для користувача " + loginToUpdate + ": ");
            String newPassword = input.nextLine();
            userToUpdate.setPassword(newPassword);
            System.out.println("Пароль користувача " + loginToUpdate + " успішно змінено.");
        } else {
            System.out.println("Користувача з логіном " + loginToUpdate + " не знайдено.");
        }
    }

    private static AppUser findUserByLogin(String login) {
        for (AppUser user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}