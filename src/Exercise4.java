import java.util.ArrayList;
import java.util.List;

// Базовий клас "Музичний інструмент"
class MusicalInstrument {
    protected String name;
    protected String description;
    protected String history;

    public MusicalInstrument(String name, String description, String history) {
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public void sound() {
        System.out.println(name + " видає звук.");
    }

    public void show() {
        System.out.println("Інструмент: " + name);
    }

    public void desc() {
        System.out.println("Опис: " + description);
    }

    public void history() {
        System.out.println("Історія: " + history);
    }
}

// Похідні класи
class Violin extends MusicalInstrument {
    public Violin() {
        super("Скрипка", "Струнний музичний інструмент, який грається смичком.",
                "З'явилася у XVI столітті в Італії.");
    }

    @Override
    public void sound() {
        System.out.println(name + " видає мелодійний звук.");
    }
}

class Trombone extends MusicalInstrument {
    public Trombone() {
        super("Тромбон", "Духовий мідний інструмент з висувною кулісою.",
                "Зародився у XV столітті, використовується в оркестрах.");
    }

    @Override
    public void sound() {
        System.out.println(name + " видає потужний звук.");
    }
}

class Ukulele extends MusicalInstrument {
    public Ukulele() {
        super("Укулеле", "Невелика гітара з чотирма струнами.",
                "Походить з Гаваїв, створена в XIX столітті.");
    }

    @Override
    public void sound() {
        System.out.println(name + " видає легкий і веселий звук.");
    }
}

class Cello extends MusicalInstrument {
    public Cello() {
        super("Віолончель", "Струнний інструмент, більший за скрипку.",
                "З'явилася в Європі в XVI столітті.");
    }

    @Override
    public void sound() {
        System.out.println(name + " видає глибокий і насичений звук.");
    }
}

// Клас "Оркестр"
class Orchestra {
    private List<MusicalInstrument> instruments;

    public Orchestra() {
        this.instruments = new ArrayList<>();
    }

    public void addInstrument(MusicalInstrument instrument) {
        instruments.add(instrument);
    }

    public void playAll() {
        System.out.println("\nГрає оркестр:");
        for (MusicalInstrument instrument : instruments) {
            instrument.sound();
        }
    }

    public void showAll() {
        System.out.println("\nІнструменти в оркестрі:");
        for (MusicalInstrument instrument : instruments) {
            instrument.show();
            instrument.desc();
            instrument.history();
            System.out.println();
        }
    }
}

// Головний клас
public class Exercise4 {
    public static void main(String[] args) {
        Orchestra orchestra = new Orchestra();

        // Додаємо інструменти
        orchestra.addInstrument(new Violin());
        orchestra.addInstrument(new Trombone());
        orchestra.addInstrument(new Ukulele());
        orchestra.addInstrument(new Cello());

        // Вивід інформації про інструменти
        orchestra.showAll();

        // Гра оркестру
        orchestra.playAll();
    }
}
