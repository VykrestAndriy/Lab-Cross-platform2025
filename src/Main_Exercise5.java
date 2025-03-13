import java.util.*;

// Винятки
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class ExceedWithdrawalLimitException extends Exception {
    public ExceedWithdrawalLimitException(String message) {
        super(message);
    }
}

class InvalidDepositException extends Exception {
    public InvalidDepositException(String message) {
        super(message);
    }
}

// Клас "АТМ банкомат"
class ATM {
    private Map<Integer, Integer> banknotes;
    private int maxWithdrawalAmount;
    private int maxBanknotesPerTransaction;

    public ATM(int maxWithdrawalAmount, int maxBanknotesPerTransaction) {
        this.maxWithdrawalAmount = maxWithdrawalAmount;
        this.maxBanknotesPerTransaction = maxBanknotesPerTransaction;
        this.banknotes = new TreeMap<>(Collections.reverseOrder());

        int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500};
        for (int denomination : denominations) {
            banknotes.put(denomination, 0);
        }
    }

    // Метод для поповнення банкомату
    public void depositCash(Map<Integer, Integer> deposit) throws InvalidDepositException {
        for (Map.Entry<Integer, Integer> entry : deposit.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();

            if (!banknotes.containsKey(denomination) || count < 0) {
                throw new InvalidDepositException("Некоректне поповнення: номінал " + denomination + " грн не підтримується або кількість від'ємна.");
            }
            banknotes.put(denomination, banknotes.get(denomination) + count);
        }
    }

    // Метод для визначення загальної суми в банкоматі
    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }

    // Метод для виведення доступних купюр
    public void printAvailableBanknotes() {
        System.out.println("\nНаявні купюри у банкоматі:");
        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + " грн: " + entry.getValue() + " шт.");
            }
        }
    }

    // Метод для зняття грошей
    public Map<Integer, Integer> withdrawCash(int amount) throws InsufficientFundsException, ExceedWithdrawalLimitException {
        if (amount > maxWithdrawalAmount) {
            throw new ExceedWithdrawalLimitException("Перевищено ліміт зняття коштів.");
        }
        if (amount > getTotalAmount()) {
            throw new InsufficientFundsException("Недостатньо коштів у банкоматі.");
        }

        Map<Integer, Integer> withdrawal = new HashMap<>();
        int remainingAmount = amount;
        int banknotesUsed = 0;

        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            int denomination = entry.getKey();
            int available = entry.getValue();
            int needed = remainingAmount / denomination;
            int toWithdraw = Math.min(available, needed);

            if (toWithdraw > 0) {
                withdrawal.put(denomination, toWithdraw);
                banknotes.put(denomination, available - toWithdraw);
                remainingAmount -= toWithdraw * denomination;
                banknotesUsed += toWithdraw;
            }

            if (banknotesUsed > maxBanknotesPerTransaction) {
                throw new ExceedWithdrawalLimitException("Перевищено ліміт кількості купюр.");
            }

            if (remainingAmount == 0) {
                break;
            }
        }

        if (remainingAmount > 0) {
            throw new InsufficientFundsException("Неможливо видати суму доступними номіналами.");
        }

        return withdrawal;
    }

    @Override
    public String toString() {
        return "ATM [Доступно: " + getTotalAmount() + " грн]";
    }
}

// Головний клас
public class Main_Exercise5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(10000, 40);

        try {
            // Завантаження грошей у банкомат
            atm.depositCash(Map.of(100, 10, 50, 20, 20, 30, 500, 5, 200, 10));
        } catch (InvalidDepositException e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }

        System.out.println("\nБанкомат запущено. Доступна сума: " + atm.getTotalAmount() + " грн");
        atm.printAvailableBanknotes();

        while (true) {
            System.out.print("\nЯкщо ви бажаєте зняти гроші, напишіть 'Зняти', якщо для поповнення — напишіть 'Поповнити': ");
            String action = scanner.next().trim().toLowerCase();

            if (action.equals("зняти")) {
                System.out.print("\nВведіть суму для зняття: ");
                int amount = scanner.nextInt();

                try {
                    Map<Integer, Integer> withdrawnCash = atm.withdrawCash(amount);
                    System.out.println("Отримано купюри:");
                    for (Map.Entry<Integer, Integer> entry : withdrawnCash.entrySet()) {
                        System.out.println(entry.getKey() + " грн: " + entry.getValue() + " шт.");
                    }
                } catch (InsufficientFundsException | ExceedWithdrawalLimitException e) {
                    System.out.println("Помилка: " + e.getMessage());
                }
            } else if (action.equals("поповнити")) {
                System.out.println("\nДоступні номінали: 1, 2, 5, 10, 20, 50, 100, 200, 500");
                System.out.print("Введіть номінал купюри: ");
                int depositDenomination = scanner.nextInt();
                System.out.print("Введіть кількість купюр: ");
                int depositCount = scanner.nextInt();

                try {
                    atm.depositCash(Map.of(depositDenomination, depositCount));
                    System.out.println("Поповнення успішне!");
                    atm.printAvailableBanknotes();
                } catch (InvalidDepositException e) {
                    System.out.println("Помилка: " + e.getMessage());
                }
            } else {
                System.out.println("Невірна команда. Спробуйте ще раз.");
            }
        }
    }
}
