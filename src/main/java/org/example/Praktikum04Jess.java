package org.example;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Praktikum04Jess {

}
/*------------Aufgabe 1------------*/
class Lotto {
  public static void main(String[] args) {
    Random random = new Random();
    Set<Integer> lottozahlen = new TreeSet<>(); // automatisch sortiert, keine Duplikate

    while (lottozahlen.size() < 6) {
      int zahl = random.nextInt(49) + 1; // Zahlen zwischen 1 und 49
      lottozahlen.add(zahl); // doppelte werden ignoriert
    }
    System.out.println("Gezogene Lottozahlen: " + lottozahlen);
  }
}
