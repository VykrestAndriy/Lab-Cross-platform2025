import java.util.ArrayList;
import java.util.List;

// Базовий клас "Тварина"
class Animal {
    protected String name;
    protected String species;
    protected String sound;
    protected boolean isPredator;

    public Animal(String name, String species, String sound, boolean isPredator) {
        this.name = name;
        this.species = species;
        this.sound = sound;
        this.isPredator = isPredator;
    }

    public boolean isPredator() {
        return isPredator;
    }

    public void makeSound() {
        System.out.println(name + " каже: " + sound);
    }

    @Override
    public String toString() {
        return species + " " + name + (isPredator ? " (Хижак)" : " (Травоїдний)");
    }
}

// Похідні класи
class Tiger extends Animal {
    public Tiger(String name) {
        super(name, "Тигр", "Рррр!", true);
    }
}

class Rabbit extends Animal {
    public Rabbit(String name) {
        super(name, "Кролик", "Пі-пі!", false);
    }
}

class Wolf extends Animal {
    public Wolf(String name) {
        super(name, "Вовк", "Аууу!", true);
    }
}

class Kangaroo extends Animal {
    public Kangaroo(String name) {
        super(name, "Кенгуру", "Хоп-хоп!", false);
    }
}

// Клас "Зоопарк"
class Zoo {
    private List<Animal> animals;

    public Zoo() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void printAllAnimals() {
        System.out.println("\nТварини у зоопарку:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public void countPredators() {
        int count = 0;
        for (Animal animal : animals) {
            if (animal.isPredator()) {
                count++;
            }
        }
        System.out.println("\nКількість хижаків у зоопарку: " + count);
    }

    public void calculateFoodNeeds() {
        int meat = 0;
        int plants = 0;

        for (Animal animal : animals) {
            if (animal.isPredator()) {
                meat += 5; // Припустимо, що хижаки їдять 5 кг м'яса на день
            } else {
                plants += 3; // Травоїдні їдять 3 кг рослинної їжі на день
            }
        }

        System.out.println("\nЩоденна потреба у кормі:");
        System.out.println("М'ясо: " + meat + " кг");
        System.out.println("Рослинна їжа: " + plants + " кг");
    }
}

// Головний клас
public class Exercise3 {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        // Додаємо тварин у зоопарк
        zoo.addAnimal(new Tiger("Шерхан"));
        zoo.addAnimal(new Rabbit("Білий"));
        zoo.addAnimal(new Wolf("Сірий"));
        zoo.addAnimal(new Kangaroo("Джамп"));
        zoo.addAnimal(new Wolf("Альфа"));
        zoo.addAnimal(new Rabbit("Вухань"));

        // Вивід всіх тварин
        zoo.printAllAnimals();

        // Визначення кількості хижаків
        zoo.countPredators();

        // Розрахунок корму
        zoo.calculateFoodNeeds();
    }
}
