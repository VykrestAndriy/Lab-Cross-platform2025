package Exercise;

import Exercise.Entities.Client;
import Exercise.Services.CafeManagementService;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

public class Exercise_3 {
    public static void main(String[] args) {
        CafeManagementService cafeService = new CafeManagementService(3);
        Random random = new Random();

        Client c1 = new Client("Іван", "Петренко");
        Client c2 = new Client("Марія", "Сидоренко");
        Client c3 = new Client("Ольга", "Коваленко");
        Client c4 = new Client("Андрій", "Лисенко");
        Client c5 = new Client("Тетяна", "Шевчук");
        Client c6 = new Client("Сергій", "Бондаренко");

        cafeService.clientArrived(c1, LocalDateTime.now());
        cafeService.clientArrived(c2, LocalDateTime.now().plusMinutes(3));
        cafeService.clientArrived(c3, LocalDateTime.now().plusMinutes(7));

        cafeService.makeTableReservation(c4, LocalDateTime.of(2025, Month.APRIL, 18, 14, 0));
        cafeService.makeTableReservation(c5, LocalDateTime.of(2025, Month.APRIL, 18, 14, 20));

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusSeconds(10);

        while (LocalDateTime.now().isBefore(endTime)) {
            LocalDateTime currentTime = LocalDateTime.now();
            System.out.println("\nПоточний час симуляції: " + currentTime);
            cafeService.processReservations(currentTime);
            cafeService.displayWaitingQueue(currentTime);

            if (random.nextDouble() < 0.15) {
                cafeService.tableIsFree(currentTime);
            }

            if (random.nextDouble() < 0.2) {
                Client newClient = new Client("Гість " + (random.nextInt(100) + 100));
                cafeService.clientArrived(newClient, currentTime);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        LocalDateTime finalTime = LocalDateTime.now();
        System.out.println("\nСимуляція завершена о " + finalTime);
        cafeService.displayWaitingQueue(finalTime);
    }
}