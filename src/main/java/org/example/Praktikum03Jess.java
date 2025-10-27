package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;

public class Praktikum03Jess {}

/* -------------------- AUFGABE 1  -------------------- */
class AtomicLongComparable extends AtomicLong implements Comparable<AtomicLongComparable>{

  public AtomicLongComparable(long value) {
    super(value);
  }
  public AtomicLongComparable () {
    super();
  }

  @Override
  public int compareTo(AtomicLongComparable o) {
    return Long.compare(this.get(), o.get());
  }
}

class TestClass {
  public static void main( String[] args ) {
    List<AtomicLongComparable> lngLst = new ArrayList<AtomicLongComparable>();
    lngLst.add( new AtomicLongComparable() );
    lngLst.add( new AtomicLongComparable( 99 ) );
    lngLst.add( new AtomicLongComparable( 42 ) );
    System.out.println( findeMaximum ( lngLst ) );
  }

  public static <T extends Comparable<T>> String findeMaximum(List<? extends T> list) {
    var maximum = list.getFirst();
    for (var i = 0; i < list.size(); i++) {
      var size = list.get(i).compareTo(maximum);
      if (size > 0) {maximum = list.get(i);}
    }
    return maximum.toString();
  }
}

/* -------------------- AUFGABE 2  -------------------- */

class Aufgabe2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    var eingabeAktiv = true;
    List<Double> list = new ArrayList<>();
    /*eingabe*/
    while (eingabeAktiv) {
      list.add(getZahl(sc));
      eingabeAktiv = getActiv(sc);
    }
    /*Ratespiel*/
    var zahl = list.get(0);
    System.out.println("Was war die erste Zahl?");
    for (int i = 1; i < list.size(); i++) {
      var guess = getUserInputGuess(sc, zahl);
      if (guess) {
        System.out.println("Richtig! Die Nächste: ");
      } else {
        System.out.println("Leider falsch: " + zahl + "wäre richtig gewesen.");
        break;
      }
      zahl = list.get(i);
    }
  }

  public static double getZahl(Scanner sc) {
    while (true) {
      System.out.println("Bitte geben Sie eine Zahl ein: ");
      try {
        var eingabe = sc.next();
        return Double.parseDouble(eingabe);
      }
      catch (NumberFormatException e) {
        System.out.println("Das ist keine Zahl!");
      }
    }
  }

  public static boolean getActiv(Scanner sc) {
    while (true) {
      System.out.println("Wollen Sie weitere Zahlen eingeben? (y/n)");
      try {
        var input = sc.next();
        if (input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N")) {
          switch (input) {
            case "y", "Y" -> {
              return true;
            }
            case "n", "N" -> {
              return false;
            }
          }
        } else {
          throw new IllegalArgumentException();
        }
      }
      catch (IllegalArgumentException e) {
        System.out.println("Das ist keine gültige Eingabe!");
      }
    }
  }

  public static boolean getUserInputGuess(Scanner sc, double zahl) {
    var guess = sc.nextDouble();
    return (guess == zahl);
  }
}

/* -------------------- AUFGABE 3  -------------------- */

class TerminJess {
  public String beschreibung;
  public String ort;
  public LocalDate zeit;

  public TerminJess(String was, String wo, LocalDate wann) {
    beschreibung = was;
    ort = wo;
    zeit = wann;
  }
}

class TestClass3 {
  public static void main(String args[]) {
  TerminJess[] termine = new TerminJess[]{new TerminJess("Birthday", "Zuhause", LocalDate.of(2025, 10, 13)),
                                          new TerminJess("Feiern", "Oma", LocalDate.of(2025, 12, 24)),
                                          new TerminJess("Einkauf", "Oma", LocalDate.of(2025, 12, 12))
  };
  Arrays.sort(termine, (termin1, termin2) -> termin1.zeit.compareTo(termin2.zeit));
    System.out.println(Arrays.toString(termine));
    aufgabe4();
  }

  public static void aufgabe4() {
    TerminJess[] termine = new TerminJess[]{new TerminJess("Birthday", "Zuhause", LocalDate.of(2025, 10, 13)),
                                          new TerminJess("Feiern", "Oma", LocalDate.of(2025, 12, 24)),
                                          new TerminJess("Einkauf", "Oma", LocalDate.of(2025, 12, 12))
    };
    printTermine(termine, (termin) -> termin.zeit.equals(LocalDate.of(2025,12, 24)));
  }

  public static void printTermine(TerminJess[] termine, Predicate<TerminJess> function) {
    for (var terminJess : termine) {
      if (function.test(terminJess)) {
        System.out.println(terminJess);
      }
    }
  }
}

/*----------Aufgabe 5--------*/

class TestClass5{
  
}