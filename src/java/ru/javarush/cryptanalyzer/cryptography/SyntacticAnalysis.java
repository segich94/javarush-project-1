package ru.javarush.cryptanalyzer.cryptography;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SyntacticAnalysis {
    private static Map<Character, Integer> encryptTextChars = new HashMap<>();
    private static Map<Character, Integer> exampleTextChars = new HashMap<>();
    private static int encryptTextLength = 0;
    private static int exampleTextLength = 0;


    public static int diffOfMaps(){
        char keyMaxOfEncryptMap = 0;
        char keyMaxOfExampleMap = 0;
        double tempEncryptFr = 0.0;
        double tempExampleFr = 0.0;

        for (Map.Entry<Character, Integer> encrypt: encryptTextChars.entrySet()) {
            if ((encrypt.getValue()/encryptTextLength)>tempEncryptFr){
                tempEncryptFr = encrypt.getValue()/encryptTextLength;
                keyMaxOfEncryptMap = encrypt.getKey();
            }
        }
        for (Map.Entry<Character, Integer> example: exampleTextChars.entrySet()) {
            if ((example.getValue()/exampleTextLength)>tempEncryptFr){
                tempExampleFr = example.getValue()/exampleTextLength;
                keyMaxOfExampleMap = example.getKey();
            }
        }

        return Arrays.binarySearch(Crypt.ALPHABET,keyMaxOfEncryptMap) -
                Arrays.binarySearch(Crypt.ALPHABET,keyMaxOfExampleMap);
    }

    public static void fillCharsMaps(File encrypt, File example){
        try (FileReader encryptReader = new FileReader(encrypt);
            FileReader exampleReader = new FileReader(example)){
            while (encryptReader.ready()) {
                char ch = (char)(encryptReader.read());
                encryptTextChars.merge(ch,1, Integer::sum);
                encryptTextLength++;
            }
            while (exampleReader.ready()) {
                char ch = (char)(encryptReader.read());
                exampleTextChars.merge(ch,1, Integer::sum);
                exampleTextLength++;
            }

        } catch (FileNotFoundException e){
            System.out.println("Файлы не найдены - " + encrypt + " " + example);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файлов -  - " + encrypt + " " + example);
            e.printStackTrace();
        }
    }
}
