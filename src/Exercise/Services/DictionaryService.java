package Exercise.Services;

import Exercise.Utilities.WordList;

public class DictionaryService {
    private WordList wordList;

    public DictionaryService(WordList wordList) {
        this.wordList = wordList;
    }

    public void add(String word) {
        wordList.addWord(word.trim().toLowerCase());
    }

    public boolean remove(String word) {
        return wordList.removeWord(word.trim().toLowerCase());
    }

    public boolean check(String word) {
        return wordList.containsWord(word.trim().toLowerCase());
    }

    public void listAllWords() {
        if (wordList.getAllWords().isEmpty()) {
            System.out.println("Словник порожній.");
        } else {
            System.out.println("Усі слова в словнику:");
            for (String word : wordList.getAllWords()) {
                System.out.println("- " + word);
            }
        }
    }
}