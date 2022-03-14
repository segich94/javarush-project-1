package ru.javarush.cryptanalyzer.cryptography;

import java.util.Arrays;

public class Crypt {
    private String data;
    private int cryptShift;
    private static final char[] ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б',
            'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};


    public Crypt(String data, int cryptShift) {
        this.data = data;
        this.cryptShift = cryptShift;
    }

    public void encrypt(){
        for (char ch:this.data.toCharArray()) {
            System.out.print(cryptChar(ch,cryptShift));
        }
    }
    public void decrypt(){
        for (char ch:this.data.toCharArray()) {
            System.out.print(cryptChar(ch,cryptShift * (-1)));
        }
    }

    public char cryptChar(char ch, int shift){
        int searchResult = Arrays.binarySearch(ALPHABET,ch);
        if (searchResult == -1)
            return ch;
        else return  ALPHABET[(searchResult+shift) % ALPHABET.length];
    }
}
