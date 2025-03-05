import java.util.Scanner;

public class Exercise_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Виведення запиту для користувача
        System.out.println("Введіть два числа:");

        // Введення чисел
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        // Нормалізація меж
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // Виведення непарних чисел
        for (int i = start; i <= end; i++)
            if (i % 2 != 0) System.out.print(i + " ");

        scanner.close();
    }
}
