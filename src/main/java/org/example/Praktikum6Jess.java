package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Praktikum6Jess {
  public static void main(String[] args) throws IOException {
//    Aufgabe1P6();
//    Aufgabe2P6();
    new TestAufgabe4P6();
  }

  public static void Aufgabe1P6() throws IOException {
    var homePathString = System.getProperty("user.home");
    var homePath = Paths.get(homePathString);
    if (!Files.exists(homePath)) {
      System.out.println("hier ist was schiefgelaufen");
      Files.createDirectory(homePath);
    }
    if (Files.exists(homePath.resolve("java-text.tmp"))) {
      Files.delete(homePath.resolve("java-text.tmp"));
    }
    var file = Files.createFile(homePath.resolve("java-text.tmp"));
    var name = Files.getOwner(homePath).getName();
    Files.write(file, Arrays.asList(name, LocalDate.now().toString(), LocalTime.now().toString()));
  }

  public static void Aufgabe2P6() throws IOException {
    var homePathString = System.getProperty("user.home");
    var homePath = Paths.get(homePathString);
    try {
      Files.createDirectory(homePath);
    } catch (IOException e) {
      System.out.println("perfekt gelaufen");
    }

    List<Path> javaFiles = new ArrayList<>();

    try (Stream<Path> allPaths = Files.walk(homePath)) {
      javaFiles = allPaths.filter(path -> !Files.isDirectory(path) && path.toString().toLowerCase().endsWith(".java")).toList();
    } catch (IOException ioex) {
      System.out.println(ioex.getMessage());
    } //hier wird noch manchmal ne AccessDeniedEx geschmissen -> die wird ignoriert

    long size = javaFiles.stream().map(file -> {
      try {
        return Files.size(file);
      }
      catch (IOException e) {
        System.out.println("upsii");
        return 0L;
      }
    }).reduce(0L, Long::sum);

    System.out.println("all Files size up to: " + size + "Byte");
  }

  public static void Aufgabe3P6(String suchText, String patternFileName, Path startDir, Path excludedDir){
    var matcher = FileSystems.getDefault().getPathMatcher("glob:" + patternFileName);

    List<Path> allMatchingFiles = new ArrayList<>();
    try (Stream<Path> allPaths = Files.walk(startDir)) {
      allMatchingFiles = allPaths
                .filter(path ->
                                  !Files.isDirectory(path)
                                            && !path.startsWith(excludedDir)
                                            && matcher.matches(path.getFileName()))
                .toList();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }

    allMatchingFiles = allMatchingFiles.stream().filter(file -> {
      try (Stream<String> lines = Files.lines(file)){
        return lines.anyMatch(line -> line.contains(suchText));
      } catch (IOException e) {
        System.err.println("upsi: " + e.getMessage());
        return false;
      }
    }).toList();

    System.out.println("Dateien die die Anforderungen erfüllen: ");
    allMatchingFiles.forEach(System.out::println);
  }
}

class PersonP6 implements Serializable{
  String name;
  int alter;
  transient String passwort;
  PersonP6 knownPerson;

  public PersonP6(String name, int alter, String passwort){
    this.name = name;
    this.alter = alter;
    this.passwort = passwort;
  }

  public void setKnownPerson(PersonP6 knownPerson){
    this.knownPerson = knownPerson;
  }

}

class TestAufgabe4P6{
  public TestAufgabe4P6() throws IOException {
    PersonP6 p_1 = new PersonP6("Alice", 18, "a1!");
    PersonP6 p_2 = new PersonP6("Bob", 18, "a1!");
    PersonP6 p_3 = new PersonP6("Charles", 18, "a1!");
    PersonP6 p_4 = new PersonP6("Dunkin", 18, "a1!");
    p_1.setKnownPerson(p_4);
    p_2.setKnownPerson(p_1);
    p_3.setKnownPerson(p_2);
    p_4.setKnownPerson(p_3);

    var aktuellesArbeitsverzeichnis = Paths.get(System.getProperty("user.dir"));
    var file = aktuellesArbeitsverzeichnis.resolve("person.ser");
    if (Files.exists(file)) {
      Files.delete(file);
    }
    Files.createFile(file);

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
      oos.writeObject(p_1);
    }
    catch (IOException e) {
      System.out.println("upsi: " + e.getMessage());
    }

    PersonP6 p_1_d = null;
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
      p_1_d = (PersonP6) ois.readObject();
    }
    catch (IOException e) {
      System.out.println("upsi: " + e.getMessage());
    }
    catch (ClassNotFoundException e) {
      System.out.println("anderes upsi: " + e.getMessage());
    }
    PersonP6 p_4_d = p_1_d.knownPerson;
    PersonP6 p_3_d = p_4_d.knownPerson;
    PersonP6 p_2_d = p_3_d.knownPerson;
    List<PersonP6> peopleList = List.of(p_1_d, p_2_d, p_3_d, p_4_d);
    peopleList.forEach(person -> System.out.println(person.name + ", " + person.alter));
  }
}

