package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Praktikum5Jess {
  public static void main(String[] args) {
    /*--Aufgabe 1--*/
//    fold(5, (a, b) -> a * b);
//    fold(5, Integer::sum);

    /*--Aufgabe 2--*/
//    var list = generate(15);
//    System.out.println("Fibonacci numbers: ");
//    list.forEach(x -> System.out.print(x + ", "));

    /*--Aufgabe 3--*/
//    String lorem = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, seddiam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyamerat, sed diam voluptua. At vero eos et accusam et justo duo dolores et earebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsumdolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, seddiam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyamerat, sed diam voluptua. At vero eos et accusam et justo duo dolores et earebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsumdolor sit amet.";
//    getAllWordsLongerThan6(lorem);

    /*--Aufgabe 4--*/
    getFakultaet();

  }

  /*----Aufgabe 1----*/
  public static void fold(int n, BinaryOperator<Integer> operator) {
    var neutral = 0;
    if (operator.apply(1, 2) == 2) {neutral = 1;}
    var result = IntStream.range(1, n+1).reduce(neutral, operator::apply);
    System.out.println("Result: " + result);
  }

  /*----Aufgabe 2----*/
  public static List<Integer> generate(int n) {
    return Stream.iterate(new int[]{1,1}, a -> new int[]{a[1], a[1]+a[0]}).limit(n).map(x -> x[0]).toList();
  }

  /*----Aufgabe 3----*/
  public static void getAllWordsLongerThan6(String text) {
    Stream.of(text.split(" "))
          .map(word -> {
            if(word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == ',') {
              word = word.substring(0, word.length() -1);
            }
            return word;
          })
          .filter(word -> word.length() > 6)
          .forEach(System.out::println);
  }

  /*----Aufgabe 4----*/
  public static void getFakultaet(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Gib hier die Zahl ein (maximal 64) von der abwärts alle multipliziert werden bis 1: ");
    BigDecimal eingabe = BigDecimal.valueOf(sc.nextDouble());
    sc.close();

    LocalTime time = LocalTime.now();
    long ausgabe;
    if (eingabe.compareTo(BigDecimal.valueOf(20)) > 0) {
      ausgabe = LongStream.range(1, eingabe.longValue()).parallel().reduce(1, (x, y) -> x*y);
    } else {
      ausgabe = LongStream.range(1, eingabe.longValue()).reduce(1, (x, y) -> x*y);
    }
    LocalTime time2 = LocalTime.now();
    System.out.println("Fakultät = " + ausgabe);
    System.out.println(Duration.between(time, time2));
  }
}
