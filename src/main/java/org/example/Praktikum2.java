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
class Frischkaese extends Kaese {
    public Frischkaese(String name, float preis) {
        super(name, preis);
    }
}
class Superfrischkaese extends Frischkaese {

    public Superfrischkaese(String name, float preis) {
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
        /*var einkaufswagen = new Einkaufswagen<Nahrungsmittel>();

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
        }*/
        var brotWagen = new Einkaufswagen<Brot>();
        var kaeseWagen = new Einkaufswagen<Kaese>();
        var frischkaeseWagen = new Einkaufswagen<Frischkaese>();

        var frischkaese = new Frischkaese("Frischkäse", 1.5F);
        var superfrischkaese = new Superfrischkaese("Superfrischkäse", 2.5F);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Was möchtest du tun?");
            System.out.println("1. Produkt hinzufügen");
            System.out.println("2. Einkaufswagen ausgeben");
            System.out.println("3. Beenden");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    boolean adding = true;
                    while (adding) {
                        System.out.println("Zu welchem Einkaufswagen möchtest du ein Produkt hinzufügen?");
                        System.out.println("1. Brot-Wagen");
                        System.out.println("2. Käse-Wagen");
                        System.out.println("3. Frischkäse-Wagen");
                        System.out.println("0. Zurück");
                        int wagen = scanner.nextInt();
                        switch (wagen) {
                            case 1:
                                brotWagen.hinzufuegen(brot);
                                System.out.println("Brot hinzugefügt.");
                                break;
                            case 2:
                                kaeseWagen.hinzufuegen(kaese);
                                System.out.println("Käse hinzugefügt.");
                                break;
                            case 3:
                                System.out.println("Welchen Frischkäse möchtest du hinzufügen?");
                                System.out.println("1. Normaler Frischkäse");
                                System.out.println("2. Superfrischkäse");
                                int fkOption = scanner.nextInt();
                                if (fkOption == 1) {
                                    frischkaeseWagen.hinzufuegen(frischkaese);
                                    System.out.println("Frischkäse hinzugefügt.");
                                } else if (fkOption == 2) {
                                    frischkaeseWagen.hinzufuegen(superfrischkaese);
                                    System.out.println("Superfrischkäse hinzugefügt.");
                                } else {
                                    System.out.println("Ungültige Eingabe.");
                                }
                                break;
                            case 0:
                                adding = false;
                                break;
                            default:
                                System.out.println("Ungültige Eingabe.");
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Welchen Einkaufswagen möchtest du sehen?");
                    System.out.println("1. Brot-Wagen");
                    System.out.println("2. Käse-Wagen");
                    System.out.println("3. Frischkäse-Wagen");
                    System.out.println("0. Zurück");
                    int ausgabe = scanner.nextInt();
                    switch (ausgabe) {
                        case 1:
                            brotWagen.ausgeben();
                            break;
                        case 2:
                            kaeseWagen.ausgeben();
                            break;
                        case 3:
                            frischkaeseWagen.ausgeben();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Ungültige Eingabe.");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Programm beendet.");
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }

    public static <T extends Nahrungsmittel> void haushaltsbuch(T produkt, int menge) {
        double gesamtPreis = produkt.getPreis() * menge;
        System.out.println("Das Produkt " + produkt.getName() + " in der Menge "
                + menge + " hat den Preis " + gesamtPreis + " Euro.");
    }
}