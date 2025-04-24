import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class TestMaven {

    public static void main(String[] args) {
        System.out.println("--- Перевірка Lists ---");
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi");
        System.out.println("Початковий список фруктів:");
        fruits.forEach(System.out::println);

        List<String> reverseFruits = Lists.reverse(fruits);
        System.out.println("\nПеревернутий список фруктів:");
        reverseFruits.forEach(System.out::println);

        System.out.println("\n--- Перевірка Multimap ---");
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("key", "firstValue");
        map.put("key", "secondValue");
        System.out.println("Multimap:");
        System.out.println(map);

        System.out.println("\n--- Перевірка імпортів ---");
        System.out.println("Імпорти:");
        System.out.println("import com.google.common.collect.ArrayListMultimap;");
        System.out.println("import com.google.common.collect.Lists;");
        System.out.println("import com.google.common.collect.Multimap;");
        System.out.println("Ці імпорти не є частиною Java Core, вони надаються бібліотекою Guava.");

        Properties prop = new Properties();
        String fileName = "config.properties";

        try (InputStream input = TestMaven.class.getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                System.out.println("Помилка: Не знайдено файл " + fileName);
                return;
            }

            prop.load(input);

            System.out.println("\n--- Перевірка properties ---");
            System.out.println("props.local.hello: " + prop.getProperty("props.local.hello"));
            System.out.println("props.mvn.hello: " + prop.getProperty("props.mvn.hello"));

        } catch (IOException ex) {
            System.err.println("Помилка при читанні файлу config.properties: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}