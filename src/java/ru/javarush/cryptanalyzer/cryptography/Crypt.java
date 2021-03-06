package ru.javarush.cryptanalyzer.cryptography;

import java.util.Arrays;

public class Crypt {
    private static Crypt crypt;
    private final String data;
    private final int cryptShift;
    public static final char[] ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б',
            'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    public static final int AlphabetLength = ALPHABET.length;

    private Crypt(String data, int cryptShift) {
        this.data = data;
        this.cryptShift = cryptShift;
    }

    public static Crypt getInstance(String data, int cryptShift) {
        if (crypt == null) {
            crypt = new Crypt(data, cryptShift);
        }
        return crypt;
    }

    public String encrypt() {
        return decrypt(cryptShift);
    }

    public String decrypt() {
        return decrypt(-cryptShift);
    }

    public String decrypt(int shift) {
        char[] result = new char[data.length()];
        int i = 0;
        for (char ch : data.toCharArray()) {
            result[i] = cryptChar(ch, shift);
            i++;
        }
        return String.valueOf(result);
    }

    public char cryptChar(char ch, int shift) {
        int key = shift;
        if (key < 0)
            key = (shift % AlphabetLength) + AlphabetLength;
        int searchResult = Arrays.binarySearch(ALPHABET, ch);
        if (searchResult <= -1) {
            return ch;
        } else
            return ALPHABET[(searchResult + key) % ALPHABET.length];
    }
}
