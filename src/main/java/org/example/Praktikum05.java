package org.example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Praktikum05 {

    public static void main(String[] args) {
        aufgabe4();
    }

    private static void aufgabe4() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie eine Zahl ein: ");
        BigDecimal input = new BigDecimal(scanner.next());
        long facultaet = LongStream.rangeClosed(1, input.longValue()).parallel().reduce(1L, (a, b) -> a * b);
        System.out.println(facultaet);
    }

    private static void aufgabe3() {
        String lorem = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, seddiam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyamerat, sed diam voluptua. At vero eos et accusam et justo duo dolores et earebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsumdolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, seddiam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyamerat, sed diam voluptua. At vero eos et accusam et justo duo dolores et earebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsumdolor sit amet.";
        Arrays.stream(lorem.split(" ")).filter(s -> s.length() > 6).forEach(System.out::println);
    }

    public static List<Integer> generate(int n) {
        return Stream.iterate(new int[]{0, 1},
                x -> new int[]{x[1], x[0] + x[1]})
                .limit(n)
                .map(x -> x[0])
                .toList();
    }

    private static void aufgabe1() {
        int n = 10;

        System.out.println(fold(n, 1, (a, b) -> a * b));
        System.out.println(fold(n, 0, (a, b) -> a + b));
    }

    public static int fold(int n, int identity, IntBinaryOperator operator) {
        return IntStream.rangeClosed(1, n)
                .reduce(identity, operator);
    }

}
