package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class MyFile {
    // выводит список всех файлов и директорий для `path` - ls
    // stdin: ls ../
    // stdout: dir1 dir2 hello_world.py .secret_file
    public static void listDirectory(String path) {
        try {
            File folder = new File(path);
            if(folder.exists()) {
                for (File file : folder.listFiles()) {
                    System.out.print(file.getName() + " ");
                }
                System.out.println();
            }
            else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }

    // выводит список файлов с расширением `.py` в `path` - ls_py
    public static void listPythonFiles(String path) {
        try {
            String[] myFiles = new String[]{};
            File folder = new File(path);
            if(folder.exists()) {
                myFiles = folder.list();
                for (String file : myFiles) {
                    if (file.endsWith(".py")) {
                        System.out.print(file + ' ');
                    }
                }
                System.out.println();
            }
            else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
    // выводит `true`, если `path` это директория, в других случаях `false` - id_dir
    public static void isDirectory(String path) {
        try {
            File folder = new File(path);
            if(folder.exists()) {
                System.out.println(folder.isDirectory());
            }
            else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
    // выводит `директория` или `файл` в зависимости от типа `path` - define
    public static void define(String path) {
        try {
            File folder = new File(path);
            if(folder.exists()) {
                boolean isDir = folder.isDirectory();
                boolean isFile = folder.isFile();
                if (isDir == true) { System.out.println("Directory"); }
                if (isFile == true) { System.out.println("File"); }
            }
            else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
    // выводит права для файла в формате `rwx` для текущего пользователя - readmod
    public static void printPermissions(String path) {
        try {
            String filePermissions = "";
            File folder = new File(path);
            if(folder.exists()) {
                if (folder.canRead()) filePermissions += "r";
                else filePermissions += "-";
                if (folder.canWrite()) filePermissions += "w";
                else filePermissions += "-";
                if (folder.canExecute()) filePermissions += "x";
                else filePermissions += "-";
                System.out.println(filePermissions);
            } else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
    // устанавливает права для файла `path` - setmod
    public static void setPermissions(String path, String permissions) {
        try {
            String[] filePermissions = permissions.split("");
            File folder = new File(path);
            if(folder.exists()) {
                if (filePermissions[0].contains("r")) folder.setReadable(true);
                else if (filePermissions[0].contains("-"))folder.setReadable(false);
                else System.out.println("its not read permission");
                if (filePermissions[1].contains("w")) folder.setWritable(true);
                else if(filePermissions[1].contains("-")) folder.setWritable(false);
                else System.out.println("its not write permission");
                if (filePermissions[2].contains("x")) folder.setExecutable(true);
                else if(filePermissions[2].contains("-")) folder.setExecutable(false);
                else System.out.println("its not execute permission");
            } else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
    // выводит контент файла - cat
    public static void printContent(String path) {
        try { // 1
            File folder = new File(path);
            if(folder.exists()) {
                Scanner obj = new Scanner(folder);
                while (obj.hasNextLine()) {
                    System.out.println(obj.nextLine());
                }
            } else { System.out.println("File not exist");
            }
        } catch (Exception e) {
            System.out.printf("%s", e);
            return;
        }
    }
    // добавляет строке `# Autogenerated line` в конец `path` - append
    public static void appendFooter(String path) {
        PrintWriter out = null;
        File folder = new File(path);
        if(folder.exists()) {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
                out.println("# Autogenerated line");
            } catch (IOException e) {
                System.err.println(e);
                return;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } else { System.out.println("File not exist");
        }
    }
    // создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`. `path` может быть директорией или файлом. При директории, копируется весь контент. - bc
    public static void createBackup(String path) throws IOException  {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        File folder = new File(path);
        String backPath = "/tmp/" + dtf.format(now) + ".backup";
        File backFolder = new File(backPath);
        if(folder.exists()) {
        try {
           if(folder.isFile()) {
               Files.copy(folder.toPath(), backFolder.toPath());
            }
            if(folder.isDirectory()){
                Path source = Paths.get(path);
                Path destination = Paths.get(backPath);

                List<Path> sources = Files.walk(source).collect(toList());
                List<Path> destinations = sources.stream()
                        .map(source::relativize)
                        .map(destination::resolve)
                        .collect(toList());
                for (int i = 0; i < sources.size(); i++) {
                    Files.copy(sources.get(i), destinations.get(i));
                }
            }
        } catch (IOException e) {
            System.err.println(e);
            return;
        }
        } else { System.out.println("File not exist");
        }
    }
    // выводит самое длинное слово в файле - greplong
    public static void printLongestWord(String path) {
        File folder = new File(path);
        if(folder.exists()) {
            String longestWord = "";
            String current = "";
            try {
                Scanner sc = new Scanner(folder);
                while (sc.hasNext()) {
                    current = sc.next();
                    if (current.length() > longestWord.length()) {
                        longestWord = current;
                    }
                }
                System.out.println(longestWord);
            } catch (Exception e) {
                System.err.println(e);
                return;
            }
        }else { System.out.println("File not exist");
        }
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
                "exit                    завершает работу программы");
    }
    // завершает работу программы - exit
    public static void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
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
                "exit                    завершает работу программы");
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.printf("> ");
            String line = scanner.nextLine();
            String[] subPath;
            subPath = line.split(" ");
            String myStr = line;
            if(line.length()==0) continue;
            else {
                if (line.contains("help")) help();
                if (line.contains("exit")) exit();
                if(subPath.length <= 1 && !line.contains("help") && !line.contains("exit")) System.out.println("Incorrect command");
                if (subPath.length > 1) {
                    if (line.contains("ls") && !line.contains("ls_py")) listDirectory(subPath[1]);
                    if (line.contains("ls_py")) listPythonFiles(subPath[1]);
                    if (line.contains("is_dir")) isDirectory(subPath[1]);
                    if (line.contains("define")) define(subPath[1]);
                    if (line.contains("readmod")) printPermissions(subPath[1]);
                    if (line.contains("setmod")) setPermissions(subPath[1], subPath[2]);
                    if (line.contains("cat")) printContent(subPath[1]);
                    if (line.contains("append")) appendFooter(subPath[1]);
                    if (line.contains("bc")) createBackup(subPath[1]);
                    if (line.contains("greplong")) printLongestWord(subPath[1]);
                }
            }
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
