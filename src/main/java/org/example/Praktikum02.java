package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Praktikum02 {
  public static void main(String[] args) {}
}

/*------------------------------------ AUFGABE 1 ------------------------------------*/
class MyGenericClass<T> {
  private T value;
}
class A {}
class B extends A {}
class C extends A {}
class D extends A {}
class E {}
class F {}



class TestClass1 {
  public static void main(String[] args) {
    testCreate(new MyGenericClass<Object>());
  }
  public static void testCreate(MyGenericClass<? super C> zeiger) {
    System.out.println("yes");
  }
  /*------------------------------------ AUFGABE 3 ------------------------------------*/
  public static <T extends Nahrungsmittel> void haushaltsbuch(T produkt, int menge) {
    float preis = produkt.getPreis() * menge;
    System.out.println("Das Produkt " + produkt.getName() + " in der Menge " + menge + " hat den Gesamtpreis " + preis + " Euro.");
  }
}

/*------------------------------------ AUFGABE 2 ------------------------------------*/
abstract class Nahrungsmittel {
  private String name;
  private float preis;

  public Nahrungsmittel(String name, float preis) {
    this.name = name;
    this.preis = preis;
  }
  public String getName() {
    return this.name;
  }

  public Float getPreis(){
    return this.preis;
  }

  public String getProduktinfo() {
    return this.getName() + ": " + this.getPreis() + "Euro";
  }
}

class Wurst extends Nahrungsmittel{
  public Wurst(String name, float preis) {
    super(name, preis);
  }
}
class Brot extends Nahrungsmittel{
  public Brot(String name, float preis) {
    super(name, preis);
  }
}
class Käse extends Nahrungsmittel{
  public Käse(String name, float preis) {
    super(name, preis);
  }
}

class Einkaufswagen <T extends Nahrungsmittel> {
  private Nahrungsmittel[] produkte = new Nahrungsmittel[0];
  private int count = 0;

  public void hinzufuegen(T produkt) {
    if(count < 10) {
      produkte = Arrays.copyOf(produkte, produkte.length + 1);
      produkte[produkte.length - 1] = produkt;
    } else {
      System.out.println("Warenkorb ist voll");
    }
  }

  public <T extends Frischkäse> void hinzufuegen(T produkt) {
    if(count < 10) {
      produkte = Arrays.copyOf(produkte, produkte.length + 1);
      produkte[produkte.length - 1] = produkt;
    } else {
      System.out.println("Warenkorb ist voll");
    }
  }

  public void ausgeben(String wagen) {
    if (wagen == "wagen") {
      System.out.println("Im Einkaufswagen ist folgendes:");
    } else if (wagen == "regal") {
      System.out.println("Im Regal sind folgende Produkte:");
    } else { System.out.println("Unbekannter Wagen"); }
    for(Nahrungsmittel produkt : produkte) {
      produkt.getProduktinfo();
    }
  }
}

class TestClass2 {
  Nahrungsmittel levervurst = new Wurst("Levervurst", 1.20f);
  Nahrungsmittel väse = new Käse("Väse", 2.30f);
  Nahrungsmittel vollkornbrot = new Brot("Vollkornbrot", 3f);
  Nahrungsmittel bärchenvurst = new Wurst("Bärchenvurst", 1.30f);
  Einkaufswagen wagen = new Einkaufswagen<>();
  Einkaufswagen regal = new Einkaufswagen<>();

  /*------------------------------------ AUFGABE 4 ------------------------------------*/

  Einkaufswagen brotWagen = new Einkaufswagen<Brot>();
  Einkaufswagen käseWagen = new Einkaufswagen<Käse>();
  Einkaufswagen kühlWagen = new Einkaufswagen<Frischkäse>();

    public static void main(String[] args) {
    TestClass2 tc = new TestClass2();
    tc.testCreate();
  }

  public void testCreate () {
    regal.hinzufuegen(levervurst);
    regal.hinzufuegen(väse);
    regal.hinzufuegen(vollkornbrot);
    regal.hinzufuegen(bärchenvurst);
    regal.ausgeben("regal");
    System.out.println("Mit der jeweiligen Zahl kann das Produkt in den Einkaufswagen gelegt werden. Mit 5 kann der Inhalt des Einkaufswagen ausgegeben werden.");
    System.out.println("Mit 0 kann das Programm beendet werden");
    var laufend = true;
    Scanner sc = new Scanner(System.in);
    while (laufend) {
      var eingabe = sc.nextInt();
      if (eingabe == 5) {
        wagen.ausgeben("wagen");
      } else if (eingabe == 0) {
        laufend = false;
      } else if (eingabe == 1) {
        wagen.hinzufuegen(levervurst);
      } else if (eingabe == 2) {
        wagen.hinzufuegen(väse);
      } else if (eingabe == 3) {
        wagen.hinzufuegen(vollkornbrot);
      } else if (eingabe == 4) {
        wagen.hinzufuegen(bärchenvurst);
      } else {
        System.out.println("Ungültige Eingabe");
      }
    }
  }
}

/*------------------------------------ AUFGABE 4 ------------------------------------*/
class Frischkäse extends Käse{
  public Frischkäse(String name, float preis) {
    super(name, preis);
  }
}
class Superfrischkäse extends Käse{
  public Superfrischkäse(String name, float preis) {
    super(name, preis);
  }
}
