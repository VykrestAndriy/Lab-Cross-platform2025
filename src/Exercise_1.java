import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введіть 'далі' для вводу рядка або 'вихід' для виходу: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("далі")) {
                System.out.print("Введіть рядок тексту: ");
                String text = scanner.nextLine();

                List<String> forbiddenWordsList = new ArrayList<>();
                System.out.println("Введіть заборонені слова (для завершення введіть 'стоп'):");
                while (true) {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("стоп")) {
                        break;
                    }
                    forbiddenWordsList.add(input);
                }
                String[] forbiddenWords = forbiddenWordsList.toArray(new String[0]);

                int wordCount = countWords(text);
                int sentenceCount = countSentences(text);
                System.out.println("Кількість слів: " + wordCount);
                System.out.println("Кількість речень: " + sentenceCount);

                String longestSentence = findLongestSentence(text);
                String duplicatedText = duplicateLongestSentence(text, longestSentence);
                System.out.println("Текст з дубльованим реченням: " + duplicatedText);

                String censoredText = censorForbiddenWords(duplicatedText, forbiddenWords);
                System.out.println("Текст з цензурою: " + censoredText);
            }

            else if (userInput.equalsIgnoreCase("вихід")) {
                break;
            }

            else {
                System.out.println("Некоректне введення. Будь ласка, введіть 'далі' або 'вихід'.");
            }
        }

        scanner.close();
    }

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.split("[\\s.,!?]+");
        return words.length;
    }

    public static int countSentences(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] sentences = text.split("[.!?]+");
        return sentences.length;
    }

    public static String findLongestSentence(String text) {
        String[] sentences = text.split("[.!?]+");
        String longestSentence = "";
        for (String sentence : sentences) {
            if (sentence.trim().length() > longestSentence.trim().length()) {
                longestSentence = sentence;
            }
        }
        return longestSentence.trim();
    }

    public static String duplicateLongestSentence(String text, String longestSentence) {
        return text.replace(longestSentence, longestSentence + " " + longestSentence);
    }

    public static String censorForbiddenWords(String text, String[] forbiddenWords) {
        String[] words = text.split("[\\s.,!?]+");
        StringBuilder censoredText = new StringBuilder();

        for (String word : words) {
            boolean isForbidden = false;
            for (String forbiddenWord : forbiddenWords) {
                if (word.equals(forbiddenWord)) {
                    censoredText.append("*".repeat(word.length())).append(" ");
                    isForbidden = true;
                    break;
                }
            }
            if (!isForbidden) {
                censoredText.append(word).append(" ");
            }
        }
        return censoredText.toString().trim();
    }
}