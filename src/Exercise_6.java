import java.util.*;

public class Exercise_6 {
    public static void main(String[] args) {
        String input = "1.2; 0.5; 7.0; 2.6; 5.0";
        System.out.println("Вхідний рядок: " + input);

        String sorted = sortArrayRelativeToMax(input);
        System.out.println("Відсортований рядок: " + sorted);
    }

    public static String sortArrayRelativeToMax(String input) {
        // Перетворення рядка у список чисел
        String[] elements = input.split("; ");
        List<Double> numbers = new ArrayList<>();
        for (String element : elements) {
            numbers.add(Double.parseDouble(element));
        }

        // Знаходження найбільшого елемента
        double max = Collections.max(numbers);

        // Сортування елементів до і після максимального елемента
        numbers.sort((a, b) -> {
            if (a == max) return 1;  // Переміщаємо максимальний елемент в кінець
            if (b == max) return -1;
            return Double.compare(a, b); // Звичайне порівняння для інших елементів
        });

        // Перетворення відсортованого списку назад у рядок
        StringBuilder result = new StringBuilder();
        for (double number : numbers) {
            result.append(number).append("; ");
        }

        // Видаляємо останній зайвий символ "; "
        result.delete(result.length() - 2, result.length());
        return result.toString();
    }
}
