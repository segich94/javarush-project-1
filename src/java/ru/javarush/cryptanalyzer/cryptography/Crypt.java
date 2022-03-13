package ru.javarush.cryptanalyzer.cryptography;

import java.util.Arrays;

public class Crypt {
    private static int shift = 0;
    private static final char[] ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б',
            'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};

    public static void setShift(int shift) {
        Crypt.shift = shift;
    }

    public void encrypt(){

    }
    public void decrypt(){}

    public char cryptChar(char ch){
        int searchResult = Arrays.binarySearch(ALPHABET,ch);
        if (searchResult == -1)
            return ch;
        else return (char) ((searchResult+shift) % ALPHABET.length);
    }
}
