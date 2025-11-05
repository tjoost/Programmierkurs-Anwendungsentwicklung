package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Praktikum04 {

    public static void main(String[] args) {
        aufgabe2cont();
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


        Sets.printDurchschnitt(a, b);

        Sets.printVereinigung(a, b);

        Sets.printDifferenz(a, b);
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


        Sets.printDurchschnitt(a, b);

        Sets.printVereinigung(a, b);

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

    public static <E> void printDurchschnitt(Set<? extends E> a, Set<? extends E> b) {
        var set = new HashSet<E>(a);
        set.retainAll(b);
        System.out.println("Durchschnitt von A und B");
        for (Object o : set) {
            System.out.println(o);
        }
    }

    public static <E> void printVereinigung(Set<? extends E> a, Set<? extends E> b) {
        var set = new HashSet<E>(a);
        set.addAll(b);
        System.out.println("Vereinigung von A und B");
        for (Object o : set) {
            System.out.println(o);
        }
    }

    public static <E> void printDifferenz(Set<? extends E> a, Set<? extends E> b) {
        var set = new HashSet<E>(a);
        set.removeAll(b);
        System.out.println("Differenz von A und B");
        for (Object o : set) {
            System.out.println(o);
        }
    }
}
