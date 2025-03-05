import java.util.*;

public class Exercise_5 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = random.ints(20, -20, 51).toArray();  // Заповнення масиву випадковими числами від -20 до 50

        System.out.println("Початковий масив:");
        System.out.println(Arrays.toString(array));

        Set<Integer> fibonacciSet = new HashSet<>(Arrays.asList(0, 1, 2, 3, 5, 8, 13, 21, 34, 55));

        // Фільтрація масивів
        int[] evenArray = Arrays.stream(array).filter(num -> num % 2 == 0).toArray();
        int[] fibonacciArray = Arrays.stream(array).filter(fibonacciSet::contains).toArray();
        int[] negativeArray = Arrays.stream(array).filter(num -> num < 0 && num > -17).toArray();
        int[] primeArray = Arrays.stream(array).filter(Exercise_5::isPrime).toArray();

        // Виведення результатів
        System.out.println("\nМасив парних чисел: " + Arrays.toString(evenArray));
        System.out.println("\nМасив чисел Фібоначчі: " + Arrays.toString(fibonacciArray));
        System.out.println("\nМасив від'ємних чисел більших за -17: " + Arrays.toString(negativeArray));
        System.out.println("\nМасив простих чисел: " + Arrays.toString(primeArray));
    }

    // Перевірка на просте число
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
