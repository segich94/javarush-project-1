package ru.javarush.cryptanalyzer.cryptography;

import java.io.*;
import java.util.*;

public class BruteForce {
    private static List<String> LIBRARY = new ArrayList<>();
    private static List<Integer> successKey = new ArrayList<>();
    
    public static void createLibraryFromFile(File file){
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))){
            while (fileReader.ready()){
                LIBRARY.add(fileReader.readLine().trim());
            }
        } catch (FileNotFoundException e){
            System.out.println("Файл со словарём не найден - " + file);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла - " + file);
        }
    }

    public static String brute(String string){
        fillSuccessArray(string);
        Crypt crypt = new Crypt(string,0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int indexOfMax = successKey.indexOf(Collections.max(successKey));
            result.append(crypt.decrypt(indexOfMax));
            successKey.add(indexOfMax,-1);
            result.append("\n");
        }
        return result.toString();
    }

    private static void fillSuccessArray(String string){
        Crypt crypt = new Crypt(string, 0);
        for (int i = 0; i < Crypt.AlphabetLength-1; i++) {
            successKey.add(i,successCount(crypt.decrypt(i)));
        }
    }
    
    private static int successCount (String string){
        int countSuccess = 0;
        String[] element = string.split(" ");
        if (element.length > string.length()/10){
            for (String word:element) {
                if (LIBRARY.contains(word))
                    countSuccess++;
            }
        }
        return countSuccess;
    }

}