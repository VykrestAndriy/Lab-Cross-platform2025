import java.util.*;

interface LibraryItem {
    String getTitle();
}

class Book implements LibraryItem {
    String title, author, genre;
    int pages;
    Book(String t, String a, String g, int p) { title = t; author = a; genre = g; pages = p; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String toString() { return "Книга: " + title + ", Автор: " + author + ", Жанр: " + genre + ", Сторінок: " + pages; }
}

class Newspaper implements LibraryItem {
    String title, date;
    List<String> headlines;
    Newspaper(String t, String d, List<String> h) { title = t; date = d; headlines = h; }
    public String getTitle() { return title; }
    public String toString() { return "Газета: " + title + ", Дата: " + date + ", Заголовки: " + headlines; }
}

class Almanac implements LibraryItem {
    String title;
    List<Book> books;
    Almanac(String t, List<Book> b) { title = t; books = b; }
    public String getTitle() { return title; }
    public String toString() { return "Альманах: " + title + ", Книги: " + books; }
}

class Library {
    List<LibraryItem> catalog = new ArrayList<>();

    void add(LibraryItem item) { catalog.add(item); }
    void addRandom() {
        int type = new Random().nextInt(3);
        switch (type) {
            case 0 -> add(new Book("Рандомна книга", "Автор", "Жанр", 100));
            case 1 -> add(new Newspaper("Рандомна газета", "2025-03-25", Arrays.asList("Новини")));
            case 2 -> add(new Almanac("Рандомний альманах", Arrays.asList(new Book("Гамлет", "В. Шекспір", "Трагедія", 200))));
        }
    }
    void remove(String title) { catalog.removeIf(item -> item.getTitle().equalsIgnoreCase(title)); }
    void display() { catalog.forEach(System.out::println); }
    LibraryItem searchByTitle(String title) {
        return catalog.stream().filter(i -> i.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }
    List<Book> searchByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (LibraryItem item : catalog) {
            if (item instanceof Book && ((Book) item).getAuthor().equalsIgnoreCase(author)) {
                booksByAuthor.add((Book) item);
            }
        }
        return booksByAuthor;
    }
}

public class Exercise_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.add(new Book("1984", "Дж. Орвелл", "Дистопія", 328));
        library.add(new Newspaper("День", "2025-03-25", Arrays.asList("Новини", "Спорт")));
        library.add(new Almanac("Літературний світ", Arrays.asList(new Book("Гамлет", "В. Шекспір", "Трагедія", 200))));

        while (true) {
            System.out.println("\nМеню: ");
            System.out.println("1. Показати каталог");
            System.out.println("2. Додати випадковий об'єкт");
            System.out.println("3. Шукати за назвою");
            System.out.println("4. Шукати книги за автором");
            System.out.println("5. Видалити за назвою");
            System.out.println("6. Вийти");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> library.display();
                case 2 -> {
                    library.addRandom();
                    System.out.println("Додано випадковий об'єкт.");
                }
                case 3 -> {
                    System.out.print("Введіть назву для пошуку: ");
                    String searchTitle = scanner.nextLine();
                    LibraryItem foundItem = library.searchByTitle(searchTitle);
                    System.out.println(foundItem != null ? "Знайдено: " + foundItem : "Нічого не знайдено.");
                }
                case 4 -> {
                    System.out.print("Введіть автора для пошуку книг: ");
                    String searchAuthor = scanner.nextLine();
                    List<Book> booksByAuthor = library.searchByAuthor(searchAuthor);
                    if (!booksByAuthor.isEmpty()) {
                        booksByAuthor.forEach(System.out::println);
                    } else {
                        System.out.println("Книг цього автора не знайдено.");
                    }
                }
                case 5 -> {
                    System.out.print("Введіть назву для видалення: ");
                    String removeTitle = scanner.nextLine();
                    library.remove(removeTitle);
                    System.out.println("Якщо елемент існував, його видалено.");
                }
                case 6 -> {
                    System.out.println("Програма завершена.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }
    }
}