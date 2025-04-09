import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Exercise2 {
    private static final String FORBIDDEN_WORDS_FILE = "D:\\Labwork\\KPP\\LB_5\\src\\Files\\ForbiddenWords.txt";

    public static void main(String[] arguments) {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Введіть шлях до папки з текстовими документами: ");
        String folderPath = userInput.nextLine();

        List<String> restrictedWordsList = loadRestrictedWords(FORBIDDEN_WORDS_FILE);

        try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(Paths.get(folderPath), "*.txt")) {
            for (Path currentFile : fileStream) {
                analyzeAndProcessFile(currentFile, restrictedWordsList, userInput);
            }
        } catch (IOException exception) {
            System.err.println("Сталася помилка при обробці документів: " + exception.getMessage());
        } finally {
            userInput.close();
        }
    }

    private static List<String> loadRestrictedWords(String filePath) {
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String lineOfWords;
            while ((lineOfWords = fileReader.readLine()) != null) {
                wordsList.add(lineOfWords.trim().toLowerCase());
            }
        } catch (IOException exception) {
            System.err.println("Помилка при зчитуванні файлу із забороненими словами: " + exception.getMessage());
        }
        return wordsList;
    }

    private static void analyzeAndProcessFile(Path fileToProcess, List<String> restrictedWords, Scanner input)
            throws IOException {
        List<String> allFileLines = Files.readAllLines(fileToProcess);
        Map<String, Integer> foundRestricted = new HashMap<>();
        List<String> linesAfterCensor = new ArrayList<>();

        for (String line : allFileLines) {
            String lowerCaseLine = line.toLowerCase();
            for (String restrictedWord : restrictedWords) {
                int startIndex = lowerCaseLine.indexOf(restrictedWord);
                while (startIndex != -1) {
                    foundRestricted.merge(restrictedWord, 1, Integer::sum);
                    StringBuilder stars = new StringBuilder("*".repeat(restrictedWord.length()));
                    line = line.substring(0, startIndex) + stars +
                            line.substring(startIndex + restrictedWord.length());
                    lowerCaseLine = line.toLowerCase();
                    startIndex = lowerCaseLine.indexOf(restrictedWord, startIndex + stars.length());
                }
            }
            linesAfterCensor.add(line);
        }

        if (!foundRestricted.isEmpty()) {
            System.out.println("Документ: " + fileToProcess.getFileName());
            foundRestricted.forEach((word, count) -> System.out.println("  " + word + ": " + count + " разів"));

            System.out.print("Замінити виявлені заборонені слова в файлі " +
                    fileToProcess.getFileName() + " на символи '*' ? (так/ні): ");
            String answer = input.nextLine().toLowerCase();
            if (answer.equals("так")) {
                Files.write(fileToProcess, linesAfterCensor);
                System.out.println("Файл " + fileToProcess.getFileName() + " було відредаговано.");
            }
        }
    }
}