import java.util.Scanner;

public class Exercise_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть пароль: ");
        String password = scanner.nextLine();

        if (isStrongPassword(password)) {
            System.out.println("Пароль надійний.");
        } else {
            System.out.println("Пароль не надійний.");
        }

        scanner.close();
    }

    public static boolean isStrongPassword(String password) {
        // Перевірка довжини паролю
        if (password.length() < 8) {
            return false;
        }

        // Перевірка на наявність хоча б одного з необхідних типів символів
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!*_.".contains(String.valueOf(c))) {
                hasSpecialChar = true;
            }
        }

        // Якщо є хоча б один символ кожного типу
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}
