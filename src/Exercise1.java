    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    public class Exercise1 {
        public static void main(String[] arguments) {
            Scanner inputScanner = new Scanner(System.in);

            System.out.print("Введіть повний шлях до першого файлу: ");
            String firstFilePath = inputScanner.nextLine();

            System.out.print("Введіть повний шлях до другого файлу: ");
            String secondFilePath = inputScanner.nextLine();

            try {
                List<String> firstFileContent = readAllLines(firstFilePath);
                List<String> secondFileContent = readAllLines(secondFilePath);

                compareFileContent(firstFileContent, secondFileContent);

            } catch (IOException exception) {
                System.err.println("Виникла помилка при читанні файлу: " + exception.getMessage());
            } finally {
                inputScanner.close();
            }
        }

        private static List<String> readAllLines(String pathToFile) throws IOException {
            List<String> allLines = new ArrayList<>();
            try (BufferedReader bufferReader = new BufferedReader(new FileReader(pathToFile))) {
                String currentLine;
                while ((currentLine = bufferReader.readLine()) != null) {
                    allLines.add(currentLine);
                }
            }
            return allLines;
        }

        private static void compareFileContent(List<String> firstFileLines, List<String> secondFileLines) {
            int minimumSize = Math.min(firstFileLines.size(), secondFileLines.size());
            boolean areEqual = true;

            for (int index = 0; index < minimumSize; index++) {
                if (!firstFileLines.get(index).equals(secondFileLines.get(index))) {
                    System.out.println("Різниця знайдена на рядку № " + (index + 1) + ":");
                    System.out.println("Файл №1: " + firstFileLines.get(index));
                    System.out.println("Файл №2: " + secondFileLines.get(index));
                    areEqual = false;
                }
            }

            if (firstFileLines.size() > minimumSize) {
                System.out.println("Перший файл містить додаткові рядки:");
                for (int index = minimumSize; index < firstFileLines.size(); index++) {
                    System.out.println("Файл №1, рядок № " + (index + 1) + ": " + firstFileLines.get(index));
                    areEqual = false;
                }
            } else if (secondFileLines.size() > minimumSize) {
                System.out.println("Другий файл містить додаткові рядки:");
                for (int index = minimumSize; index < secondFileLines.size(); index++) {
                    System.out.println("Файл №2, рядок № " + (index + 1) + ": " + secondFileLines.get(index));
                    areEqual = false;
                }
            }

            if (areEqual) {
                System.out.println("Вміст файлів повністю ідентичний.");
            }
        }
    }