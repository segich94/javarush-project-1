package ru.javarush.cryptanalyzer;

import ru.javarush.cryptanalyzer.cryptography.Crypt;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String ish = "Мама мыла раму!";
        Crypt crypt = new Crypt(ish,3);
        crypt.encrypt();

        String ish2 = "Пгпг'пюог'угпц,";
        Crypt crypt1 = new Crypt(ish2,3);
        crypt1.decrypt();
    }
}
