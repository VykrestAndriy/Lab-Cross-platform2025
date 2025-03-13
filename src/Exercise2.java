import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Клас "Людина"
class Person {
    private String firstName;
    private String lastName;
    private String birthDate;

    public Person(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Дата народження: " + birthDate + ")";
    }
}

// Перерахування "Періодичність"
enum Frequency {
    WEEKLY, MONTHLY, YEARLY;
}

// Клас "Стаття"
class Article {
    private Person author;
    private String title;
    private double rating;

    public Article(Person author, String title, double rating) {
        this.author = author;
        this.title = title;
        this.rating = rating;
    }

    public String getAuthorName() {
        return author.getFullName();
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Стаття: " + title + ", Автор: " + author + ", Рейтинг: " + rating;
    }
}

// Клас "Журнал"
class Magazine {
    private String name;
    private Frequency frequency;
    private String releaseDate;
    private int circulation;
    private List<Article> articles;

    public Magazine(String name, Frequency frequency, String releaseDate, int circulation) {
        this.name = name;
        this.frequency = frequency;
        this.releaseDate = releaseDate;
        this.circulation = circulation;
        this.articles = new ArrayList<>();
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void printArticles() {
        System.out.println("\nСтатті у журналі \"" + name + "\":");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    public void printArticlesByRating(double minRating) {
        System.out.println("\nСтатті з рейтингом більше " + minRating + ":");
        for (Article article : articles) {
            if (article.getRating() >= minRating) {
                System.out.println(article);
            }
        }
    }
}

// Головний клас
public class Exercise2 {
    public static void main(String[] args) {
        Person author1 = new Person("Іван", "Петров", "1985-06-12");
        Person author2 = new Person("Марія", "Сидоренко", "1990-09-25");

        Article article1 = new Article(author1, "Розвиток AI", 4.8);
        Article article2 = new Article(author2, "Нові тренди в IT", 4.5);
        Article article3 = new Article(author1, "Інновації у медицині", 4.9);

        Magazine magazine = new Magazine("Наука та Технології", Frequency.MONTHLY, "2025-03-10", 5000);
        magazine.addArticle(article1);
        magazine.addArticle(article2);
        magazine.addArticle(article3);

        magazine.printArticles();

        // Фільтрація статей за рейтингом
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть мінімальний рейтинг для пошуку статей: ");
        double minRating = scanner.nextDouble();
        magazine.printArticlesByRating(minRating);

        scanner.close();
    }
}
