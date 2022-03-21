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

    public static String syntactAnalys(File encrypt, File example){
        String temp = fillCharsMaps(encrypt,example);
        Crypt crypt = new Crypt(temp,diffOfMaps());
        return crypt.decrypt();
    }

    private static int diffOfMaps(){
        char keyMaxOfEncryptMap = 0;
        char keyMaxOfExampleMap = 0;
        double tempEncryptFr = 0.0;
        double tempExampleFr = 0.0;

        for (Map.Entry<Character, Integer> encrypt: encryptTextChars.entrySet()) {
            if (((double)encrypt.getValue()/encryptTextLength)>tempEncryptFr){
                tempEncryptFr = (double)encrypt.getValue()/encryptTextLength;
                keyMaxOfEncryptMap = encrypt.getKey();
            }
        }
        for (Map.Entry<Character, Integer> example: exampleTextChars.entrySet()) {
            if (((double)example.getValue()/exampleTextLength)>tempExampleFr){
                tempExampleFr = (double)example.getValue()/exampleTextLength;
                keyMaxOfExampleMap = example.getKey();
            }
        }

        return Arrays.binarySearch(Crypt.ALPHABET,keyMaxOfEncryptMap) -
                Arrays.binarySearch(Crypt.ALPHABET,keyMaxOfExampleMap);
    }

    private static String fillCharsMaps(File encrypt, File example){
        StringBuilder result = new StringBuilder();
        try (FileReader encryptReader = new FileReader(encrypt);
            FileReader exampleReader = new FileReader(example)){
            while (encryptReader.ready()) {
                char ch = (char)(encryptReader.read());
                encryptTextChars.merge(ch,1, Integer::sum);
                encryptTextLength++;
                result.append(ch);
            }
            while (exampleReader.ready()) {
                char ch = (char)(exampleReader.read());
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
        return result.toString();
    }
}
