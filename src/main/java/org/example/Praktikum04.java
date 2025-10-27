package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Praktikum04 {

    public static void main(String[] args) {
        aufgabe3();
    }

    private static void aufgabe3() {

    }

    private static void aufgabe2cont() {
        // Ausgabe: nichts, inkompatible typen
        var a = new HashSet<>(Arrays.asList(1.0, 2.0));
        var b = new HashSet<>(Arrays.asList(1,2));

        System.out.println("Menge A");
        for (Object o : a) {
            System.out.println(o);
        }
        System.out.println("Menge B");
        for (Object o : b) {
            System.out.println(o);
        }


        a = new HashSet<>(Arrays.asList(1.0, 2.0));
        b = new HashSet<>(Arrays.asList(1,2));
        //Sets.printDurchschnitt(a, b);

        a = new HashSet<>(Arrays.asList(1.0, 2.0));
        b = new HashSet<>(Arrays.asList(1,2));
        //Sets.printVereinigung(a, b);

        a = new HashSet<>(Arrays.asList(1.0, 2.0));
        b = new HashSet<>(Arrays.asList(1,2));
        //Sets.printDifferenz(a, b);
    }

    private static void aufgabe2() {
        var a = new HashSet<>(Arrays.asList("a", "b", "c"));
        var b = new HashSet<>(Arrays.asList("b", "c", "d"));

        System.out.println("Menge A");
        for (Object o : a) {
            System.out.println(o);
        }
        System.out.println("Menge B");
        for (Object o : b) {
            System.out.println(o);
        }


        a = new HashSet<>(Arrays.asList("a", "b", "c"));
        b = new HashSet<>(Arrays.asList("b", "c", "d"));
        Sets.printDurchschnitt(a, b);

        a = new HashSet<>(Arrays.asList("a", "b", "c"));
        b = new HashSet<>(Arrays.asList("b", "c", "d"));
        Sets.printVereinigung(a, b);

        a = new HashSet<>(Arrays.asList("a", "b", "c"));
        b = new HashSet<>(Arrays.asList("b", "c", "d"));
        Sets.printDifferenz(a, b);
    }

    private static void aufgabe1() {
        var lotto = new HashSet<>();
        while(lotto.size() < 6)
            lotto.add(1 + new Random().nextInt(49));

        var array = lotto.toArray();
        Arrays.sort(array);
        System.out.println("Lottozahlen: " + Arrays.toString(array));
    }

}

class Sets {

    public static <E> void printDurchschnitt(Set<E> a, Set<E> b) {
        a.retainAll(b);
        System.out.println("Durchschnitt von A und B");
        for (Object o : a) {
            System.out.println(o);
        }
    }

    public static <E> void printVereinigung(Set<E> a, Set<E> b) {
        a.addAll(b);
        System.out.println("Vereinigung von A und B");
        for (Object o : a) {
            System.out.println(o);
        }
    }

    public static <E> void printDifferenz(Set<E> a, Set<E> b) {
        a.removeAll(b);
        System.out.println("Differenz von A und B");
        for (Object o : a) {
            System.out.println(o);
        }
    }
}