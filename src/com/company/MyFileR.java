package com.company;
import java.io.*;
import java.util.Scanner;

public class MyFileR {
    public static void main(String[] args) {
        try { // 1
            File file = new File("/tmp/readme.txt"); // 2
            Scanner obj = new Scanner(file); // 3
            while (obj.hasNextLine()) { // 4
                System.out.println(obj.nextLine()); // 5
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
}
    /*
    1.Обертка блока кода, где возможно появление исключения;
    2.Откроем файл;
    3.Создаем Scanner, имеющий методы для чтения - nextLine();
    4.Вызываем метод, который проверяет наличие следующей линии;
    5.Выводим прочитанную из файла линию;
    */