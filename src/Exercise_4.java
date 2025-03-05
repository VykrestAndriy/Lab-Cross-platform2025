import java.util.Scanner;

public class Exercise_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = Integer.MAX_VALUE;

        while (true) {
            System.out.print("Введіть додатнє число: ");
            int number = scanner.nextInt();
            if (number <= 9) break;
            min = Math.min(min, number);
        }

        System.out.println(min == Integer.MAX_VALUE ? "Числа не були введені." : "Мінімальне число: " + min);
        scanner.close();
    }
}
