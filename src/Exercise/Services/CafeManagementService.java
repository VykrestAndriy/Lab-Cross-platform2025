package Exercise.Services;

import Exercise.Entities.Client;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class CafeManagementService {
    private int totalTables;
    private List<Client> occupiedTables;
    private Queue<Client> waitingQueue;
    private List<Reservation> reservations;

    public CafeManagementService(int totalTables) {
        this.totalTables = totalTables;
        this.occupiedTables = new ArrayList<>(totalTables);
        this.waitingQueue = new LinkedList<>();
        this.reservations = new ArrayList<>();
    }

    public void clientArrived(Client client, LocalDateTime arrivalTime) {
        client.setArrivalTime(arrivalTime);
        if (occupiedTables.size() < totalTables) {
            occupiedTables.add(client);
            System.out.println("Клієнт " + client + " зайняв столик.");
        } else {
            waitingQueue.offer(client);
            System.out.println("Клієнт " + client + " став у чергу.");
        }
    }

    public void makeTableReservation(Client client, LocalDateTime reservationTime) {
        reservations.add(new Reservation(client, reservationTime));
        System.out.println("Клієнту " + client + " зроблено резервацію на " + reservationTime);
    }

    public void processReservations(LocalDateTime currentTime) {
        List<Reservation> arrivedReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationTime().isBefore(currentTime.plusMinutes(1)) &&
                    reservation.getReservationTime().isAfter(currentTime.minusMinutes(1))) {
                arrivedReservations.add(reservation);
            }
        }

        for (Reservation arrivedReservation : arrivedReservations) {
            Client client = arrivedReservation.getClient();
            if (occupiedTables.size() < totalTables) {
                occupiedTables.add(client);
                System.out.println("Резервація: клієнт " + client + " зайняв столик.");
                reservations.remove(arrivedReservation);
            } else {
                waitingQueue.offer(client);
                System.out.println("Резервація: клієнт " + client + " став у чергу.");
                reservations.remove(arrivedReservation);
            }
        }
    }

    public void tableIsFree(LocalDateTime currentTime) {
        if (!occupiedTables.isEmpty()) {
            Client freedClient = occupiedTables.remove(0);
            System.out.println("Столик звільнився. Клієнт " + freedClient + " пішов.");
            if (!waitingQueue.isEmpty()) {
                Client nextClient = waitingQueue.poll();
                occupiedTables.add(nextClient);
                System.out.println("З черги зайшов клієнт " + nextClient + ".");
            }
        } else {
            System.out.println("Усі столики вже вільні.");
        }
    }

    public void displayWaitingQueue(LocalDateTime currentTime) {
        if (waitingQueue.isEmpty()) {
            System.out.println("Черга очікування порожня.");
        } else {
            System.out.println("Черга очікування (" + waitingQueue.size() + " клієнтів):");
            int i = 1;
            for (Client client : waitingQueue) {
                System.out.println(i + ". " + client + " (час прибуття: " + client.getArrivalTime() + ")");
                i++;
            }
        }
        System.out.println("Зайняті столики (" + occupiedTables.size() + "/" + totalTables + "): " + occupiedTables);
    }

    private static class Reservation {
        private Client client;
        private LocalDateTime reservationTime;

        public Reservation(Client client, LocalDateTime reservationTime) {
            this.client = client;
            this.reservationTime = reservationTime;
        }

        public Client getClient() {
            return client;
        }

        public LocalDateTime getReservationTime() {
            return reservationTime;
        }
    }
}