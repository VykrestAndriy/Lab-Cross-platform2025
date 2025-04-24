import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.List;

public class TestMaven {
    public static void main(String[] args) {
        // 4.2 Перевірка роботи спеціального утилітного класу Lists
        System.out.println("--- Перевірка Lists ---");
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi");
        System.out.println("Початковий список фруктів:");
        fruits.forEach(System.out::println);

        List<String> reverseFruits = Lists.reverse(fruits);
        System.out.println("\nПеревернутий список фруктів:");
        reverseFruits.forEach(System.out::println);

        // 4.3 Перевірка роботи спеціальної Map - Multimap
        System.out.println("\n--- Перевірка Multimap ---");
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("key", "firstValue");
        map.put("key", "secondValue");
        System.out.println("Multimap:");
        System.out.println(map);

        // 4.4 Перевірка import-ів (ці імпорти від Guava)
        System.out.println("\n--- Перевірка імпортів ---");
        System.out.println("Імпорти:");
        System.out.println("import com.google.common.collect.ArrayListMultimap;");
        System.out.println("import com.google.common.collect.Lists;");
        System.out.println("import com.google.common.collect.Multimap;");
        System.out.println("Ці імпорти не є частиною Java Core, вони надаються бібліотекою Guava.");

        // 4.5 Запуск та перевірка роботи
        System.out.println("\n--- Запуск та перевірка ---");
        System.out.println("Програма успішно запустилася та вивела результати використання класів Lists та Multimap з бібліотеки Guava.");
    }
}