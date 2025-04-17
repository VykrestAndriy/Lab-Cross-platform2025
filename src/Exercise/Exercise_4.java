package Exercise;

import Exercise.Models.DisplayDevice;
import Exercise.Services.DeviceCatalogService;
import Exercise.Utilities.InputHelper;

import java.util.ArrayList;
import java.util.List;

public class Exercise_4 {
    public static void main(String[] args) {
        List<DisplayDevice> devices = new ArrayList<>();
        devices.add(new DisplayDevice("Samsung QN90A", 2023, 55000, 55, "Південна Корея"));
        devices.add(new DisplayDevice("LG OLED C2", 2022, 60000, 65, "Південна Корея"));
        devices.add(new DisplayDevice("Sony Bravia XR A80J", 2021, 45000, 55, "Японія"));

        DeviceCatalogService catalogService = new DeviceCatalogService(devices);
        InputHelper inputHelper = new InputHelper();

        System.out.println("--- Каталог телевізорів ---");
        catalogService.displayAllDevices();

        System.out.println("\n--- Телевізори з діагоналлю 55 дюймів ---");
        catalogService.displayDevicesByDiagonal(55);

        System.out.println("\n--- Телевізори виробництва Південної Кореї ---");
        catalogService.displayDevicesByManufacturer("Південна Корея");

        System.out.println("\n--- Пошук телевізорів за роком, діагоналлю та максимальною ціною ---");
        catalogService.findDevicesByYearDiagonalAndMaxPrice(inputHelper);

        System.out.println("\n--- Телевізори дорожчі за 40000 грн ---");
        catalogService.displayDevicesMoreExpensiveThan(40000);

        System.out.println("\n--- Телевізори, відсортовані за ціною (зростання) ---");
        catalogService.displayDevicesSortedByPrice(true);

        System.out.println("\n--- Телевізори, відсортовані за діагоналлю (спадання) ---");
        catalogService.displayDevicesSortedByDiagonal(false);

        System.out.println("\n--- Телевізори, згруповані за виробником ---");
        catalogService.displayDevicesGroupedByManufacturer();

        int topN = 2; // Змінено на 2, оскільки всього 3 телевізори
        System.out.println("\n--- Топ " + topN + " найдорожчих телевізорів ---");
        catalogService.displayTopNExpensiveDevices(topN);

        int smallestN = 1; // Змінено на 1
        System.out.println("\n--- Топ " + smallestN + " телевізорів з найменшою діагоналлю ---");
        catalogService.displayTopNSmallestDiagonalDevices(smallestN);

        int searchDiagonal = 55;
        DisplayDevice lastExpensiveWithDiagonal = catalogService.findLastMostExpensiveDeviceWithDiagonal(searchDiagonal);
        System.out.println("\n--- Останній найдорожчий телевізор з діагоналлю " + searchDiagonal + " дюймів ---");
        if (lastExpensiveWithDiagonal != null) {
            System.out.println(lastExpensiveWithDiagonal);
        } else {
            System.out.println("Не знайдено телевізорів з діагоналлю " + searchDiagonal + " дюймів.");
        }

        inputHelper.closeScanner();
    }
}