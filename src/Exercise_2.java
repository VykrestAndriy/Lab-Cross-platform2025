import java.util.Scanner;

public class Exercise_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть рядок: ");
        StringBuilder sb = new StringBuilder(scanner.nextLine());

        // Підрядок (subString)
        System.out.println("Введіть початкову і кінцеву межу для підрядка: ");
        int start = scanner.nextInt(), end = scanner.nextInt();
        System.out.println("Підрядок: " + sb.substring(start, end));

        // Підрядок (getChars)
        System.out.println("Введіть межі для getChars: ");
        int startChar = scanner.nextInt(), endChar = scanner.nextInt();
        char[] chars = new char[endChar - startChar];
        sb.getChars(startChar, endChar, chars, 0);
        System.out.println("Підрядок (getChars): " + new String(chars));

        // Додавання в кінець
        scanner.nextLine();  // Для споживання нового рядка після nextInt()
        System.out.println("Введіть текст для додавання в кінець: ");
        sb.append(scanner.nextLine());
        System.out.println("Після додавання в кінець: " + sb);

        // Додавання в середину
        System.out.println("Введіть текст і позицію для додавання в середину: ");
        sb.insert(scanner.nextInt(), scanner.nextLine());
        System.out.println("Після додавання в середину: " + sb);

        // Видалення
        System.out.println("Введіть межі для видалення: ");
        sb.delete(scanner.nextInt(), scanner.nextInt());
        System.out.println("Після видалення: " + sb);

        // Замінити
        scanner.nextLine();  // Для споживання нового рядка після nextInt()
        System.out.println("Введіть текст для заміни і межі заміни: ");
        sb.replace(scanner.nextInt(), scanner.nextInt(), scanner.nextLine());
        System.out.println("Після заміни: " + sb);

        scanner.close();
    }
}
