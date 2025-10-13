package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public class Praktikum03 {

    public static void main(String[] args) {
        aufgabe5();
    }

    private static void aufgabe5() {
        //Vorgabe:
        Integer[] data = {1, 2, 3};
        var lst = Arrays.asList(data);
        //lst.forEach(System.out::println);
        // Als lambda:
        lst.forEach(e -> System.out.println(e));
    }

    private static void aufgabe4printTermine(Termin[] termine, Predicate<Termin> kriterium) {
        for (Termin termin : termine) {
            if(kriterium.test(termin)) {
                System.out.println(termin);
            }
        }
    }

    private static void aufgabe3() {
        Termin[] termine = new Termin[]{
                new Termin("Einkauf", "Hamburg", LocalDateTime.of(2022, 12, 12, 12, 0)),
                new Termin("Test", "Berlin", LocalDateTime.of(2019, 12, 12, 12, 0)),
                new Termin("Einkauf", "Düsseldorf", LocalDateTime.of(2025, 12, 12, 12, 0)),
        };
        System.out.println("termine = " + Arrays.toString(termine));
        Arrays.sort(termine, (t1, t2) -> t1.zeit.compareTo(t2.zeit));
        System.out.println("Nach Zeit sortiert:");
        System.out.println("termine = " + Arrays.toString(termine));
        System.out.println("Aufgabe4 printTermine dessen Beschreibung 'Einkauf' ist:");
        aufgabe4printTermine(termine, t -> t.beschreibung.equalsIgnoreCase("Einkauf"));
    }

    private static void aufgabe2() {
        ArrayList<AtomicLongComperable> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            addNumber(list);

            boolean anotherNumber = addAnotherNumber();
            if(!anotherNumber) {
                break;
            }
        }

        AtomicLongComperable value = list.getFirst();
        System.out.print("Wie lautet die erste ZahL? ");
        int userInput = scanner.nextInt();
        if(value.compareTo(new AtomicLongComperable(userInput)) == 0) {
            System.out.println("Richtig!");
        } else {
            System.out.println("Leider Falsch: " + value.get() + " wäre richtig gewesen!");
        }
    }

    private static boolean addAnotherNumber() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Eine weitere Zahl (y/n)? ");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("n")) {
                return false;
            } else if(answer.equalsIgnoreCase("y")) {
                return true;
            } else {
                System.out.println("'" + answer + "' ist nicht 'y' oder 'n'!");
            }
        }
    }

    public static void addNumber(List<AtomicLongComperable> list) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Geben Sie eine Zahl ein: ");
            String input = scanner.next();
            try {
                int numberInput = Integer.parseInt(input);
                list.add(new AtomicLongComperable(numberInput));
                break;
            } catch (NumberFormatException e) {
                System.out.println(input + " ist keine Zahl!");
            }
        }
    }

    public static void aufgabe1() {
        List<AtomicLongComperable> lngLst = new ArrayList<>();
        lngLst.add(new AtomicLongComperable());
        lngLst.add(new AtomicLongComperable(99));
        lngLst.add(new AtomicLongComperable(42));
        System.out.println(findeMaximum(lngLst));
    }

    public static AtomicLongComperable findeMaximum(List<AtomicLongComperable> list) {
        AtomicLongComperable max = list.getFirst();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(max) > 0)
                max = list.get(i);
        }
        return max;
    }

}
/* -------------------- AUFGABE 1  -------------------- */
class AtomicLongComperable extends AtomicLong implements Comparable<AtomicLongComperable> {

    @Override
    public int compareTo(AtomicLongComperable that) {
        if (this.get() > that.get()) {
            return 1;
        } else if (this.get() < that.get()) {
            return -1;
        }
        return 0;
    }

    public AtomicLongComperable() {
        super();
    }

    public AtomicLongComperable(long initialValue) {
        super(initialValue);
    }
}

/* -------------------- AUFGABE 3  -------------------- */

class Termin {
    public String beschreibung;
    public String ort;
    public LocalDateTime zeit;

    public Termin(String was, String wo, LocalDateTime wann) {
        this.beschreibung = was;
        this.ort = wo;
        this.zeit = wann;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "beschreibung='" + beschreibung + '\'' +
                ", ort='" + ort + '\'' +
                ", zeit=" + zeit +
                '}';
    }
}