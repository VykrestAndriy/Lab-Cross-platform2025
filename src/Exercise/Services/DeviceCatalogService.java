package Exercise.Services;

import Exercise.Models.DisplayDevice;
import Exercise.Utilities.InputHelper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeviceCatalogService {
    private List<DisplayDevice> devices;

    public DeviceCatalogService(List<DisplayDevice> devices) {
        this.devices = devices;
    }

    public void displayAllDevices() {
        devices.forEach(System.out::println);
    }

    public void displayDevicesByDiagonal(int diagonal) {
        devices.stream()
                .filter(d -> d.getDiagonal() == diagonal)
                .forEach(System.out::println);
    }

    public void displayDevicesByManufacturer(String manufacturer) {
        devices.stream()
                .filter(d -> d.getManufacturer().equalsIgnoreCase(manufacturer))
                .forEach(System.out::println);
    }

    public void findDevicesByYearDiagonalAndMaxPrice(InputHelper inputHelper) {
        System.out.print("Введіть рік: ");
        int year = inputHelper.getIntInput();
        System.out.print("Введіть діагональ: ");
        int diagonal = inputHelper.getIntInput();
        System.out.print("Введіть максимальну ціну: ");
        double maxPrice = inputHelper.getDoubleInput();

        devices.stream()
                .filter(d -> d.getYear() == year && d.getDiagonal() == diagonal && d.getPrice() <= maxPrice)
                .forEach(System.out::println);
    }

    public void displayDevicesMoreExpensiveThan(double price) {
        devices.stream()
                .filter(d -> d.getPrice() > price)
                .forEach(System.out::println);
    }

    public void displayDevicesSortedByPrice(boolean ascending) {
        Comparator<DisplayDevice> priceComparator = Comparator.comparingDouble(DisplayDevice::getPrice);
        if (!ascending) {
            priceComparator = priceComparator.reversed();
        }
        devices.stream()
                .sorted(priceComparator)
                .forEach(System.out::println);
    }

    public void displayDevicesSortedByDiagonal(boolean ascending) {
        Comparator<DisplayDevice> diagonalComparator = Comparator.comparingInt(DisplayDevice::getDiagonal);
        if (!ascending) {
            diagonalComparator = diagonalComparator.reversed();
        }
        devices.stream()
                .sorted(diagonalComparator)
                .forEach(System.out::println);
    }

    public void displayDevicesGroupedByManufacturer() {
        Map<String, List<DisplayDevice>> grouped = devices.stream()
                .collect(Collectors.groupingBy(DisplayDevice::getManufacturer));
        grouped.forEach((manufacturer, deviceList) -> {
            System.out.println("Виробник: " + manufacturer);
            deviceList.forEach(device -> System.out.println("  - " + device));
        });
    }

    public void displayTopNExpensiveDevices(int n) {
        devices.stream()
                .sorted(Comparator.comparingDouble(DisplayDevice::getPrice).reversed())
                .limit(n)
                .forEach(System.out::println);
    }

    public void displayTopNSmallestDiagonalDevices(int n) {
        devices.stream()
                .sorted(Comparator.comparingInt(DisplayDevice::getDiagonal))
                .limit(n)
                .forEach(System.out::println);
    }

    public DisplayDevice findLastMostExpensiveDeviceWithDiagonal(int diagonal) {
        return devices.stream()
                .filter(d -> d.getDiagonal() == diagonal)
                .max(Comparator.comparingDouble(DisplayDevice::getPrice))
                .orElse(null);
    }
}