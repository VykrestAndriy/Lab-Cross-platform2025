import java.util.Scanner;
import java.util.regex.Pattern;

public class Exercise_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть рядок: ");
        String input = scanner.nextLine();

        // Шаблон для перевірки
        String regex = "^[A-Z][a-zA-Z]{1,8}tion[.,!;?]$";

        // Розділяємо рядок на слова
        String[] words = input.split(" ");

        // Перевірка кожного слова
        for (String word : words) {
            if (word.matches(regex)) {
                System.out.println("Слово відповідає шаблону: " + word);
            } else {
                System.out.println("Слово не відповідає шаблону: " + word);
            }
        }

        scanner.close();
    }
}
