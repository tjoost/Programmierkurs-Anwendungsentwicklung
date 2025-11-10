package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Praktikum06 {

    public static void main(String[] args) {
        aufgabe4();
    }
    /* ---------- AUFGABE 4 ---------- */
    private static void aufgabe4() {
        aufgabe4write();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Object readObj = ois.readObject();
            Object readObj2 = ois.readObject();
            System.out.println("readObj = " + readObj);
            System.out.println("readObj2 = " + readObj2);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void aufgabe4write() {
        Person p_0 = new Person("Person0", "passwort0", 18, null);
        Person p_1 = new Person("Person1", "passwort1", 19, p_0);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            oos.writeObject(p_0);
            oos.writeObject(p_1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class Person implements Serializable {
        private String name, passwort;
        private int alter;

        private Person bekanntePerson;

        public Person(String name, String passwort, int alter, Person bekanntePerson) {
            this.name = name;
            this.passwort = passwort;
            this.alter = alter;
            this.bekanntePerson = bekanntePerson;
        }

        @Serial
        private void writeObject(java.io.ObjectOutputStream out) throws IOException {
            out.writeUTF(name);
            out.writeInt(alter);
            out.writeObject(bekanntePerson);
        }

        @Serial
        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
            name = in.readUTF();
            alter = in.readInt();
            bekanntePerson = (Person) in.readObject();
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", passwort='" + passwort + '\'' +
                    ", alter=" + alter +
                    ", bekanntePerson=" + bekanntePerson +
                    '}';
        }
    }
    /* ---------- AUFGABE 3 ---------- */
    private static void aufgabe3() {
        List<File> dateien = aufgabe3rek("Praktikum", ".*\\.java", ".", "test");
        dateien.forEach(System.out::println);
    }

    private static List<File> aufgabe3rek(String suchText, String regex, String startVerzeichnis, String excludeDir) {
        Path path = Paths.get(startVerzeichnis);
        if(!Files.exists(path)) return new ArrayList<>();
        File directory = path.toFile();
        if(directory.listFiles() == null) return new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);

        List<File> files = new ArrayList<>();
        for(File file : directory.listFiles()) {
            if(file.isDirectory() && !file.getName().equals(excludeDir)) {
                files.addAll(aufgabe3rek(suchText, regex, file.getAbsolutePath(), excludeDir));
            } else if(file.isFile() && pattern.matcher(file.getName()).matches() && file.getName().contains(suchText)) {
                files.add(file);
            }
        }
        return files;
    }
    /* ---------- AUFGABE 2 ---------- */
    private static void aufgabe2() {
        Path currentDirectory = Paths.get(".");
        List<File> javaFiles = getAllJavaFiles(currentDirectory);
        long size = javaFiles.stream().mapToLong(File::length).sum();
        System.out.println("Größe aller Java-Dateien: " + size + " Byte");
    }

    private static List<File> getAllJavaFiles(Path path) {
        if(Files.isDirectory(path)) {
            File directory = path.toFile();
            File[] files = directory.listFiles();
            List<File> fileList = new ArrayList<>();
            if(files != null) {
                for (File file : files) {
                    fileList.addAll(getAllJavaFiles(file.toPath()));
                }
            }
            return fileList;
        } else if(path.toString().toLowerCase().endsWith(".java")) {
            System.out.println(path);
            return new ArrayList<>(List.of(path.toFile()));
        } else {
            return new ArrayList<>();
        }
    }
    /* ---------- AUFGABE 1 ---------- */
    private static void aufgabe1() {
        String username = System.getProperty("user.name");
        String datumUhrzeit = LocalDateTime.now().toString();
        var txtList = Arrays.asList(username, datumUhrzeit);
        try  {
            Files.write(Paths.get("java-text.tmp"), txtList, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
