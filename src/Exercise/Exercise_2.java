package Exercise;

import Exercise.Utilities.WordList;
import Exercise.Services.DictionaryService;
import Exercise.UserInterface.ConsoleHandler;

public class Exercise_2 {
    public static void main(String[] args) {

        WordList dictionaryData = new WordList();

        DictionaryService dictionaryService = new DictionaryService(dictionaryData);

        ConsoleHandler consoleHandler = new ConsoleHandler(dictionaryService);

        consoleHandler.start();
    }
}