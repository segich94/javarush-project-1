package ru.javarush.cryptanalyzer.uiconsole;

import ru.javarush.cryptanalyzer.cryptography.BruteForce;
import ru.javarush.cryptanalyzer.cryptography.Crypt;
import ru.javarush.cryptanalyzer.cryptography.SyntacticAnalysis;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Dialog {
    private static final String FILE_NAME = "Введите путь и имя файла(Например: C:\\Users\\user\\desktop\\1.txt)";
    public void startDialog(){
        System.out.println("""
                Привет, выберите что необходимо сделать:
                1: Зашифровать файл.
                2: Расшифровать файл с известным ключом
                3: Расшифровать подбором ключа методом BruteForce
                4: Расшифровать файл с помощью статистического анализа текста(Потребуется дополнительный файл с примером текста автора или похожим)
                Exit: для выхода из программы""");
        userChoice();
    }
    private void userChoice(){
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("Exit".toLowerCase(Locale.ROOT)))
            return;
        switch (Integer.parseInt(choice)){
            case 1 -> encryptFile();
            case 2 -> decryptFile();
            case 3 -> decryptBrute();
            case 4 -> decryptStatAnal();
            default -> startDialog();
        }
    }
    private void encryptFile(){
        String srcText = readFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ключ для шифрования(целое число)");
        int key = scanner.nextInt();
        Crypt crypt = new Crypt(srcText,key);
        writeFile(crypt.encrypt());
        System.out.println("Файл зашифрован");

    }

    private void decryptFile(){
        String srcText = readFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ключ для шифрования(целое число)");
        int key = scanner.nextInt();
        Crypt crypt = new Crypt(srcText,key);
        writeFile(crypt.decrypt());
        System.out.println("Файл расшифрован");
    }

    private void decryptBrute(){
        String srcText = readFile();
        writeFile(BruteForce.brute(srcText));
        System.out.println("Файл расшифрован - в файле несколько вариантов");

    }

    private void decryptStatAnal(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла для расшифровки");
        String file1 = scanner.nextLine();
        System.out.println("и файл с текстом автора или похожий");
        String file2 = scanner.nextLine();
        writeFile(SyntacticAnalysis.syntactAnalys(new File(file1),new File(file2)));
        System.out.println("Файл расшифрован");
    }

    private String readFile(){
        StringBuilder result = new StringBuilder();
        System.out.println("Исходный файл");
        System.out.println(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(name))){
            while (fileReader.ready())
                result.append(fileReader.readLine());
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден - " + name);
            readFile();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла - " + name);
            readFile();
        }
        return result.toString();
    }
    private void writeFile(String string){
        System.out.println("Файл для записи резульатата");
        System.out.println(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try (FileWriter fileWriter = new FileWriter(name)){
            fileWriter.write(string);
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
            writeFile(string);
        }
    }
}
