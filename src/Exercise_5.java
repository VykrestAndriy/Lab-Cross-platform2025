import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть рядок з посиланнями на сайти: ");
        String urls = scanner.nextLine();
        String result = removeRuUrls(urls);
        System.out.println("Доступні посилання: " + result);
        scanner.close();
    }

    public static String removeRuUrls(String urls) {
        String regex = "\\bhttps?://[A-Za-z0-9.-]+\\.ru\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(urls);
        return matcher.replaceAll("");  // Видалення посилань з доменом .ru
    }
}
