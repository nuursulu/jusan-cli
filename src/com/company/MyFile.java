package com.company;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
                    /*
                    for (File file : folder.listFiles()) {
                        System.out.print(file.getName() + " -> " + file.isDirectory() + " ");
                    }
                    */
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
                    /*
                    for (File file : folder.listFiles()) {
                        System.out.print(file.getName() + " -> " + file.isDirectory() + " ");
                    }
                    */
                boolean isDir = folder.isDirectory();
                boolean isFile = folder.isFile();
                if (isDir == true) { System.out.println(folder.getName() + " is Directory"); }
                if (isFile == true) { System.out.println(folder.getName() + " is File"); }
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
                else folder.setReadable(false);
                if (filePermissions[1].contains("w")) folder.setWritable(true);
                else folder.setWritable(false);
                if (filePermissions[2].contains("x")) folder.setExecutable(true);
                else folder.setExecutable(false);
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
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            out.println("# Autogenerated line");
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    // создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`. `path` может быть директорией или файлом. При директории, копируется весь контент. - bc
    public static void createBackup(String path) throws IOException  {
        File folder = new File(path);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String backPath = "/tmp/" + dtf.format(now) + ".backup";
        InputStream is = null;
        OutputStream os = null;
        try {
           if(folder.isFile()) {
                is = new FileInputStream(path);
                os = new FileOutputStream(backPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }
            if(folder.isDirectory()){

            }

        } finally {
            is.close();
            os.close();
        }
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
            if(subPath.length > 1) {
                if (line.contains("ls") && !line.contains("ls_py")) listDirectory(subPath[1]);
                if (line.contains("ls_py")) listPythonFiles(subPath[1]);
                if (line.contains("is_DIR")) isDirectory(subPath[1]);
                if (line.contains("define")) define(subPath[1]);
                if (line.contains("readmod")) printPermissions(subPath[1]);
                if (line.contains("setmod")) setPermissions(subPath[1], subPath[2]);
                if (line.contains("cat")) printContent(subPath[1]);
                if (line.contains("append")) appendFooter(subPath[1]);
                if (line.contains("bc")) createBackup(subPath[1]);
                if (line.contains("help")) help();
                if (line.contains("exit")) exit();
            }
            else {System.out.println("Path not exist");}
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
