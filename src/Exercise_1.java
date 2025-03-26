import java.io.*;
import java.util.*;

interface EquipmentOperations extends Serializable {
    void saveToFile(String filename, List<Equipment> equipmentList) throws IOException;
    List<Equipment> loadFromFile(String filename) throws IOException, ClassNotFoundException;
}

class Equipment implements EquipmentOperations, Comparable<Equipment> {
    private static final long serialVersionUID = 1L;
    private String name;
    private String issuedTo;
    private int quantity;
    private boolean isIssued;
    private String issueDate;

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

    public String getName() {
        return name;
    }

    public boolean isIssued() {
        return isIssued;
    }

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
    public int compareTo(Equipment other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public void saveToFile(String filename, List<Equipment> equipmentList) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(equipmentList);
        }
    }

    @Override
    public List<Equipment> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Equipment>) ois.readObject();
        }
    }

    @Override
    public String toString() {
        return "Обладнання: " + name + ", Кількість: " + quantity +
                (isIssued ? ", Видано: " + issuedTo + ", Дата: " + issueDate : ", В наявності");
    }
}

public class Exercise_1 {
    public static void main(String[] args) {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment("Мікроскоп", 2));
        equipmentList.add(new Equipment("Осцилограф", 1, "Іванов", "2025-03-10"));
        equipmentList.add(new Equipment("Термометр", 5));
        equipmentList.add(new Equipment("Аналізатор спектру", 1, "Петров", "2025-03-08"));
        equipmentList.add(new Equipment("Фотометр", 3));

        // Сортування списку
        Collections.sort(equipmentList);

        System.out.println("Усе обладнання:");
        for (Equipment eq : equipmentList) {
            System.out.println(eq);
        }

        System.out.println("\nНаявне обладнання:");
        for (Equipment eq : equipmentList) {
            if (!eq.isIssued()) System.out.println(eq);
        }

        System.out.println("\nВидане обладнання:");
        for (Equipment eq : equipmentList) {
            if (eq.isIssued()) System.out.println(eq);
        }

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

        // Запис у файл
        String filename = "equipment.dat";
        try {
            equipmentList.get(0).saveToFile(filename, equipmentList);
            System.out.println("\nДані збережено у файл.");
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }

        // Завантаження з файлу
        List<Equipment> loadedList;
        try {
            loadedList = equipmentList.get(0).loadFromFile(filename);
            System.out.println("\nДані завантажено з файлу:");
            for (Equipment eq : loadedList) {
                System.out.println(eq);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }

        scanner.close();
    }
}
