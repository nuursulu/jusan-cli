package com.company;
import java.io.*;
import java.util.Scanner;

public class MyFileWriter {
    public static void main(String[] args) {
        try {
            FileWriter file = new FileWriter("todo.txt", true); // 1
            Scanner scanner = new Scanner(System.in); // 2
            System.out.printf("> ");
            while (true) { // 3
                String line = scanner.nextLine(); // 4
                if (line.equals("Выход")) { // 5
                    file.close(); // 6
                    return;
                }
                file.write(line + "\n"); // 7
                System.out.printf("Добавлено\n> ");
            }
        } catch (Exception e) { // 8
            System.out.printf("%s", e);
            return;
        }
    }
}
    /*
        1.Создание объекта FileWriter, первый параметр - название файла, второй параметр - добавлять ли линии к уже существующим файлы или начать сначала;
        Создавая экземпляр данного класса, мы создаем новый поток, через который можно записывать данные.
        2.Создание объекта типа Scanner для чтения данных от пользователя;
        3.Цикл для чтения данных от пользователя;
        4.Чтение линии до \n;
        5.Условие выхода из программы;
        6.Закрытие потока;
        7.Запись данных в файл;
        8.Обработка исключений;
    */
