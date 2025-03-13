import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Equipment {
    private String name;
    private String issuedTo;
    private int quantity;
    private boolean isIssued;
    private String issueDate;

    // Конструктори
    public Equipment(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.isIssued = false;
        this.issuedTo = "";
        this.issueDate = "";
    }

    public Equipment(String name, int quantity, String issuedTo, String issueDate) {
        this.name = name;
        this.quantity = quantity;
        this.issuedTo = issuedTo;
        this.issueDate = issueDate;
        this.isIssued = true;
    }

    // Геттери
    public String getName() {
        return name;
    }

    public boolean isIssued() {
        return isIssued;
    }

    // Методи
    public void issue(String issuedTo, String issueDate) {
        this.issuedTo = issuedTo;
        this.issueDate = issueDate;
        this.isIssued = true;
    }

    public void returnEquipment() {
        this.issuedTo = "";
        this.issueDate = "";
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "Обладнання: " + name + ", Кількість: " + quantity +
                (isIssued ? ", Видано: " + issuedTo + ", Дата: " + issueDate : ", В наявності");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment("Мікроскоп", 2));
        equipmentList.add(new Equipment("Осцилограф", 1, "Іванов", "2025-03-10"));
        equipmentList.add(new Equipment("Термометр", 5));
        equipmentList.add(new Equipment("Аналізатор спектру", 1, "Петров", "2025-03-08"));
        equipmentList.add(new Equipment("Фотометр", 3));

        // Вивід всього обладнання
        System.out.println("Усе обладнання:");
        for (Equipment eq : equipmentList) {
            System.out.println(eq);
        }

        // Вивід наявного та виданого обладнання
        System.out.println("\nНаявне обладнання:");
        for (Equipment eq : equipmentList) {
            if (!eq.isIssued()) System.out.println(eq);
        }

        System.out.println("\nВидане обладнання:");
        for (Equipment eq : equipmentList) {
            if (eq.isIssued()) System.out.println(eq);
        }

        // Пошук обладнання за назвою
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть назву обладнання для пошуку: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (Equipment eq : equipmentList) {
            if (eq.getName().equalsIgnoreCase(searchName)) {
                System.out.println(eq);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Обладнання не знайдено.");
        }

        scanner.close();
    }
}
