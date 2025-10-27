package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Praktikum04 {

    public static void main(String[] args) {
        aufgabe3();
    }

    private static void aufgabe3() {
        List<List<Integer>> datenmatrix = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3)),
                new ArrayList<>(Arrays.asList(2, 2, 2, 1, 1)),
                new ArrayList<>(Arrays.asList(4, 5, 6, 5, 4))
        ));


        for (int i = 0; i < datenmatrix.size(); i++) {
            List<Integer> daten = datenmatrix.get(i);
            var frequencyMap = daten.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Integer::intValue,
                                    Collectors.counting()
                            )
                    );
            System.out.println("Häufigkeiten Merkmal " + i + ":");
            frequencyMap.forEach((k, v) -> System.out.println(k + ": " + v));
        }
    }

    private static void aufgabe2cont() {
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
        SetsNumber.printDurchschnitt(a, b);

        a = new HashSet<>(Arrays.asList(1.0, 2.0));
        b = new HashSet<>(Arrays.asList(1,2));
        SetsNumber.printVereinigung(a, b);

        a = new HashSet<>(Arrays.asList(1.0, 2.0));
        b = new HashSet<>(Arrays.asList(1,2));
        SetsNumber.printDifferenz(a, b);
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

class SetsNumber {

    public static void printDurchschnitt(Set<? extends Number> a, Set<? extends Number> b) {
        Set<Number> copyA = new HashSet<>(a);
        copyA.retainAll(b);
        System.out.println("Durchschnitt von A und B");
        for (Object o : copyA) {
            System.out.println(o);
        }
    }

    public static void printVereinigung(Set<? extends Number> a, Set<? extends Number> b) {
        Set<Number> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("Vereinigung von A und B");
        for (Object o : union) {
            System.out.println(o);
        }
    }

    public static void printDifferenz(Set<? extends Number> a, Set<? extends Number> b) {
        Set<Number> diff = new HashSet<>(a);
        diff.removeAll(b);
        System.out.println("Differenz von A und B");
        for (Object o : diff) {
            System.out.println(o);
        }
    }
}