package Exercise.Utilities;

import java.util.ArrayList;
import java.util.List;

public class WordList {
    private List<String> words;

    public WordList() {
        this.words = new ArrayList<>();
    }

    public void addWord(String word) {
        if (!words.contains(word)) {
            words.add(word);
        }
    }

    public boolean removeWord(String word) {
        return words.remove(word);
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public List<String> getAllWords() {
        return new ArrayList<>(words);
    }
}