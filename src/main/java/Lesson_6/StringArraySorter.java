package Lesson_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringArraySorter {
    public static void main(String[] args) {
        List<String> wordArray = new ArrayList<>();
        try {
            for (String file : Files.readAllLines(Paths.get("src\\main\\java\\Lesson_6\\words.txt"))) { // Читаем файл
                for (String word : file.split("\\s+")) { // Преобразуем файл в массив из слов
                    wordArray.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Слова в файле: " + wordArray); // Выводим неотсортированный массив слов
        wordArray.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Слова, отсортированные по алфавиту: " + wordArray); // Выводим отсортированный по алфавиту массив слов
        int maxCount = 0; // Определяем переменную для подсчета повторений слова в массиве
        String mostFrequentWord = ""; // Определяем переменную, которая будет содержать самое повторяющееся слово
        HashMap<String, Integer> countingWords = new HashMap<>();
        for (String word : wordArray) { // Вводим цикл для подсчета повторений слов в массиве
            if (!countingWords.containsKey(word)) {
                countingWords.put(word, 0);
            }
            countingWords.put(word, countingWords.get(word) + 1);
        }
        for (String word : countingWords.keySet()) { // Выводим слова и то, как часто они встречаются в консоль
            System.out.println("Cлово \"" + word + "\" встречается " + countingWords.get(word) + " раз");
            if (maxCount < countingWords.get(word)) { // Цикл для определения самого часто повторяющегося слова
                maxCount = countingWords.get(word);
                mostFrequentWord = word;
            }
        }
        System.out.println("Самое часто повторяющееся слово: \"" + mostFrequentWord + "\" повторяется " + maxCount + " раз");

    }
}
