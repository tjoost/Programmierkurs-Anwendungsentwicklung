package org.example;

import java.util.Scanner;

public class Praktikum2 {

    public static void main(String[] args) {

    }

    public static void aufgabe1() {
        var test = new Testclass();
        test.testCreate(new MyGenericClass<A>());
        //test.testCreate(new MyGenericClass<B>()); // geht nicht
        test.testCreate(new MyGenericClass<C>());
    }

}
/* ----------------------------- AUFGABE 1 ----------------------------- */
class MyGenericClass<T> {}

class Testclass {
    public void testCreate(MyGenericClass<? super C> parameter) {

    }
}

class A {}
class B extends A {}
class C extends A {}

/* ----------------------------- AUFGABE 2 ----------------------------- */
abstract class Nahrungsmittel {
    private String name;
    private float preis;

    public Nahrungsmittel(String name, float preis) {
        this.name = name;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public float getPreis() {
        return preis;
    }
}
class Wurst extends Nahrungsmittel {
    public Wurst(String name, float preis) {
        super(name, preis);
    }
}
class Brot extends Nahrungsmittel {
    public Brot(String name, float preis) {
        super(name, preis);
    }
}
class Kaese extends Nahrungsmittel {
    public Kaese(String name, float preis) {
        super(name, preis);
    }
}
class Einkaufswagen<T extends Nahrungsmittel> {
    private Nahrungsmittel[] produkte;

    public Einkaufswagen() {
        this.produkte = new Nahrungsmittel[10];
    }

    public void hinzufuegen(T produkt) {
        for (int i = 0; i < produkte.length; i++) {
            if(produkte[i] == null) {
                produkte[i] = produkt;
                return;
            }
        }
        throw new RuntimeException("Es können nicht mehr als 10 Produkte hinzugefügt werden");
    }

    public void ausgeben() {
        System.out.println("Einkaufswagen:");
        for (Nahrungsmittel n : produkte) {
            if(n == null) continue;
            System.out.println(n.getName());
        }
    }

    //TODO: Aufgabe 3?
    /*public static void haushaltsbuch(T produkt, X menge) {

    }*/
}

class Testklasse2 {
    public static void main(String[] args) {
        var brot = new Brot("Brot", 1.23F);
        var kaese = new Kaese("Käse", 5);
        var wurst = new Wurst("Wurst", 1.5F);
        var einkaufswagen = new Einkaufswagen<Nahrungsmittel>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Was möchtest du tun?\n1. Produkt hinzufügen\n2. Warenkorb ausgeben\n3. Beenden\n");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    boolean adding = true;
                    while (adding) {
                        System.out.println("Welches Produkt möchtest du hinzufügen?\n1. Käse\n2. Brot\n3. Wurst\n0. Zurück");
                        int produkt = scanner.nextInt();
                        switch (produkt) {
                            case 1:
                                einkaufswagen.hinzufuegen(kaese);
                                break;
                            case 2:
                                einkaufswagen.hinzufuegen(brot);
                                break;
                            case 3:
                                einkaufswagen.hinzufuegen(wurst);
                                break;
                            case 0:
                                adding = false; // verlässt das innere Menü
                                break;
                            default:
                                System.out.println("Ungültige Eingabe.");
                                break;
                        }
                    }
                    break;
                case 2:
                    einkaufswagen.ausgeben();
                    break;
                case 3:
                    return;
            }
        }
    }
}