package org.example

import scala.annotation.tailrec

object Praktikum09 {
  def main(args: Array[String]): Unit = {
    val keinPalindrom = "Ausgezeichnet"
    val palindrom = "otto"
    val palindrom2 = "o"
    val palindrom3 = ""
    println(aufgabe2.istPalindromEndrekursiv(keinPalindrom))
    println(aufgabe2.istPalindromEndrekursiv(palindrom))
    println(aufgabe2.istPalindromEndrekursiv(palindrom2))
    println(aufgabe2.istPalindromEndrekursiv(palindrom3))

    println(aufgabe3.countElementsRek(List(1, 2, 3, 4, 5, 3, 3, 3), 3))

    println(aufgabe4.additionCurry(1, 2)(3, 4))

    println(aufgabe5.calculate(1, 2, _ + _))
    println(aufgabe5.calculate(5, 2, _ - _))

    def printPascal(triangle: List[List[Int]]): Unit = {
      val n = triangle.length
      triangle.foreach { row =>
        val spaces = " " * (n - row.length)
        val line = row.mkString(" ")
        println(spaces + line)
      }
    }
    printPascal(aufgabe6.pascalTriangle(5))
    printPascal(aufgabe6.pascalTriangleTailRec(5))
  }
}

object aufgabe6 {
  def pascalElement(row: Int, column: Int): Int = {
    if(row == 0 || column == 0 || row == column) return 1
    pascalElement(row - 1, column - 1) + pascalElement(row - 1, column)
  }

  def pascalRow(row: Int): List[Int] = {
    (0 to row).toList.map(pascalElement(row, _))
  }

  def pascalTriangle(n: Int): List[List[Int]] = {
    (0 until n).toList.map(row => pascalRow(row))
  }

  def pascalTriangleTailRec(n: Int): List[List[Int]] = {
    @tailrec
    def build(row: Int, acc: List[List[Int]]): List[List[Int]] = {
      if (row >= n) acc.reverse
      else build(row + 1, pascalRow(row) :: acc)
    }

    build(0, Nil)
  }
}

object aufgabe5 {
  def calculate(a: Int, b: Int, op: (Int, Int) => Int): Int = {
    op(a, b)
  }
}

object aufgabe4 {
  def additionCurry(a: Int, b: Int)(c: Int, d: Int): Int = {
    a + b + c + d
  }
}

object aufgabe3 {
  def countElementsRek(input: List[Int], element: Int): Int = {
    if(input.isEmpty) return 0
    if(input(0) == element)
      return 1 + countElementsRek(input.drop(1), element)
    else
      return countElementsRek(input.drop(1), element)
  }

  def countElementsEndrek(input: List[Int], element: Int): Int = {
    var count = 0;
    @tailrec
    def countEndRek(input: List[Int], anzahl: Int): Int = {
      if(input.isEmpty) return anzahl
      if(input(0) == element) return countEndRek(input.drop(1), anzahl + 1)
      countEndRek(input.drop(1), anzahl)
    }
    countEndRek(input, 0)
  }
}

object aufgabe2 {
  def istPalindrom(input: String): Boolean = {
    if(input.length > 1) {
      if(input.charAt(0) != input.charAt(input.length - 1)) {
        return false
      }
      return istPalindrom(input.substring(1, input.length - 1))
    }
    true
  }

  def istPalindromEndrekursiv(input: String): Boolean = {
    val start = 0
    val end = input.length - 1
    @tailrec
    def palindromRek(left: Int, right: Int): Boolean = {
      if(left >= right) return true
      if(input.charAt(left) == input.charAt(right)) return palindromRek(left + 1, right - 1)
      false
    }
    palindromRek(start, end)
  }
}