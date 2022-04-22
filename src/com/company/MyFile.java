package com.company;
import java.io.*;
import java.util.Scanner;

public class MyFile {
    //String[] myFiles;
    // выводит список всех файлов и директорий для `path` - ls
    // stdin: ls ../
    // stdout: dir1 dir2 hello_world.py .secret_file
    public static void listDirectory(String path) {
        try {
            if (path.contains("ls")) {
                String[] subPath;
                subPath = path.split(" ");
                File folder = new File(subPath[1]);

                for (File file : folder.listFiles()) {
                    System.out.print(file.getName() + " ");
                }
                System.out.println();
            } if(!path.contains("help") & !path.contains("exit") & !path.contains("") ) {
                System.out.println("Incorrect command");
            }

        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }

    // выводит список файлов с расширением `.py` в `path` - ls_py
    public static void listPythonFiles(String path) {
    }
    // выводит `true`, если `path` это директория, в других случаях `false` - id_dir
    public static void isDirectory(String path) {

    }
    // выводит `директория` или `файл` в зависимости от типа `path` - define
    public static void define(String path) {

    }
    // выводит права для файла в формате `rwx` для текущего пользователя - readmod
    public static void printPermissions(String path) {

    }
    // устанавливает права для файла `path` - setmod
    public static void setPermissions(String path, String permissions) {

    }
    // выводит контент файла - cat
    public static void printContent(String path) {

    }
    // добавляет строке `# Autogenerated line` в конец `path` - append
    public static void appendFooter(String path) {

    }
    // создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`. `path` может быть директорией или файлом. При директории, копируется весь контент. - bc
    public static void createBackup(String path) {

    }
    // выводит самое длинное слово в файле - greplong
    public static void printLongestWord(String path) {

    }
    // выводит список команд и их описание - help
    public static void help() {
        System.out.println("MyFS 1.0 команды:\n" +
                "ls <path>               выводит список всех файлов и директорий для `path`\n" +
                "ls_py <path>            выводит список файлов с расширением `.py` в `path`\n" +
                "is_dir <path>           выводит `true`, если `path` это директория, в других случаях `false`\n" +
                "define <path>           выводит `директория` или `файл` в зависимости от типа `path`\n" +
                "readmod <path>          выводит права для файла в формате `rwx` для текущего пользователя\n" +
                "setmod <path> <perm>    устанавливает права для файла `path`\n" +
                "cat <path>              выводит контент файла\n" +
                "append <path>           добавляет строку `# Autogenerated line` в конец `path`\n" +
                "bc <path>               создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`\n" +
                "greplong <path>         выводит самое длинное слово в файле\n" +
                "help                    выводит список команд и их описание\n" +
                "exit                    завершает работу программы\n");
    }
    // завершает работу программы - exit
    public static void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    public static void main(String[] args) {
        System.out.println("MyFS 1.0 команды:\n" +
                "ls <path>               выводит список всех файлов и директорий для `path`\n" +
                "ls_py <path>            выводит список файлов с расширением `.py` в `path`\n" +
                "is_dir <path>           выводит `true`, если `path` это директория, в других случаях `false`\n" +
                "define <path>           выводит `директория` или `файл` в зависимости от типа `path`\n" +
                "readmod <path>          выводит права для файла в формате `rwx` для текущего пользователя\n" +
                "setmod <path> <perm>    устанавливает права для файла `path`\n" +
                "cat <path>              выводит контент файла\n" +
                "append <path>           добавляет строку `# Autogenerated line` в конец `path`\n" +
                "bc <path>               создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`\n" +
                "greplong <path>         выводит самое длинное слово в файле\n" +
                "help                    выводит список команд и их описание\n" +
                "exit                    завершает работу программы\n");
        while(true){
            Scanner scanner = new Scanner(System.in); // 2
            System.out.printf("> ");
            String line = scanner.nextLine(); // 4
            //if (line!="") {
                listDirectory(line);
                if (line.contains("exit")) { exit(); }
                if (line.contains("help")) { help(); }
           // }

        }
    }

}

/*
* $ java Main
> ls ../
dir1 dir2 hello_world.py .secret_file
> is_dir ../dir1
true
> readmod ./dir2
rw-
> ls_py ../ hello_world.py
> cat ../hello_world.py
print("hello cruel world")
> exit
Goodbye
$*/
