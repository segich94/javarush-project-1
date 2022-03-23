package ru.javarush.cryptanalyzer.cryptography;

import java.io.*;
import java.util.*;

public class BruteForce {
    private final static List<String> LIBRARY = createLibraryFromFile(new File("src/java/ru/javarush/cryptanalyzer/cryptography/mostPopularRus.txt"));
    private final static List<Integer> successKey = new ArrayList<>();

    public static List<String> createLibraryFromFile(File file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            while (fileReader.ready()) {
                result.add(fileReader.readLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл со словарём не найден - " + file);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла - " + file);
        }
        return result;
    }

    public static String brute(String string) {
        fillSuccessArray(string);
        int mostSuccessKey = 5;
        Crypt crypt = new Crypt(string, 0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mostSuccessKey; i++) {
            int indexOfMax = successKey.indexOf(Collections.max(successKey));
            result.append("Key - " + indexOfMax + ":");
            result.append(crypt.decrypt(indexOfMax));
            successKey.add(indexOfMax, -1);
            result.append("\n");
            result.append("\n");
        }
        return result.toString();
    }

    private static void fillSuccessArray(String string) {
        Crypt crypt = new Crypt(string, 0);
        for (int i = 0; i < Crypt.AlphabetLength - 1; i++) {
            successKey.add(i, successCount(crypt.decrypt(i)));
        }
    }

    private static int successCount(String string) {
        int averageFreqOfSpace = 10;
        int countSuccess = 0;
        String[] element = string.split(" ");
        if (element.length > string.length() / averageFreqOfSpace) {
            for (String word : element) {
                if (LIBRARY.contains(word))
                    countSuccess++;
            }
        }
        return countSuccess;
    }

}
