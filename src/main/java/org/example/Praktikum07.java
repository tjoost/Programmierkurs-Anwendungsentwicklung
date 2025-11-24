package org.example;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Praktikum07 {

    public static void main(String[] args) {
        aufgabe3();
    }

    /* FIXME: Das hier ist kaputt */
    private static void aufgabe3() {
        int size = 100;
        var stadt = new Stadt(size);
        for (int i = 0; i < size; i++) {
            new Thread(new StadtPerson(stadt, i)).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
        stadt.printCounter();
    }

    private static void aufgabe2() {
        int size = 10;
        var tisch = new Tisch(size);
        for (int i = 0; i < size; i++) {
            new Thread(new Philosoph(tisch, i)).start();
        }
    }

    private static void aufgabe1() {
        Sanduhr.main(null);
    }

}
/* Aufgabe 3 */
class Stadt {
    @Getter
    private final int anzahlPersonen;
    private final AtomicBoolean[] kenntWitz;
    private final AtomicBoolean[] erzaehltWeiter;

    public Stadt(int anzahlPersonen) {
        this.anzahlPersonen = anzahlPersonen;
        this.kenntWitz = new AtomicBoolean[anzahlPersonen];
        Arrays.fill(kenntWitz, new AtomicBoolean(false));
        this.erzaehltWeiter = new AtomicBoolean[anzahlPersonen];
        Arrays.fill(erzaehltWeiter, new AtomicBoolean(true));
        this.kenntWitz[0].set(true);
    }

    public synchronized boolean witzErzaehlen(int id, int partner) {
        if(!kenntWitz[id].get() || !erzaehltWeiter[id].get()) return false;
        if(kenntWitz[partner].get()) {
            erzaehltWeiter[id].set(false);
            erzaehltWeiter[partner].set(false);
            return false;
        } else {
            kenntWitz[partner].set(true);
            return true;
        }
    }

    void printCounter() {
        int counter = 0;
        for (AtomicBoolean b : kenntWitz)
            if(b.get()) counter++;
        System.out.println("counter = " + counter);
    }

}

class StadtPerson implements Runnable {

    private final Stadt stadt;
    private final int id;
    private boolean stop = false;

    public StadtPerson(Stadt stadt, int id) {
        this.stadt = stadt;
        this.id = id;
    }

    @Override
    public void run() {
        if(stop) return;
        int partner = getRandomPartner();

        if(!stadt.witzErzaehlen(id, partner)) {
            System.out.println(id + " erzählt nicht mehr weiter");
            stop = true;
        }
    }

    private int getRandomPartner() {
        int randomPartner;
        do {
            randomPartner = new Random().nextInt(stadt.getAnzahlPersonen());
        } while (randomPartner == id);
        return randomPartner;
    }
}
/* Aufgabe 2 */
class Philosoph implements Runnable {
    private final Tisch tisch;
    private final int id;

    public Philosoph(Tisch tisch, int id) {
        this.tisch = tisch;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                denken();

                tisch.nimmGabeln(id);

                speisen();

                tisch.legGabelnAb(id);
            }
        } catch (InterruptedException ignored) {}
    }

    private void denken() {
        System.out.println("Philosoph " + id + " denkt");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    private void speisen() {
        System.out.println("Philosoph " + id + " speist");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

}

class Tisch {
    private enum ZUSTAND {DENKT, HUNGERT, SPEIST}
    @Getter
    private final ZUSTAND[] zustaende;

    private final int anzahlPhilosophen;

    public Tisch(int anzahlPhilosophen) {
        this.anzahlPhilosophen = anzahlPhilosophen;
        this.zustaende = new ZUSTAND[anzahlPhilosophen];
        for (int i = 0; i < anzahlPhilosophen; i++) {
            zustaende[i] = ZUSTAND.DENKT;
        }
    }

    public synchronized void nimmGabeln(int id) throws InterruptedException {
        if(zustaende[id] != ZUSTAND.DENKT)
            return;
        zustaende[id] = ZUSTAND.HUNGERT;

        while (!kannSpeisen(id))
            wait();

        zustaende[id] = ZUSTAND.SPEIST;
    }

    public synchronized void legGabelnAb(int id) {
        zustaende[id] = ZUSTAND.DENKT;

        pruefeNachbarn(rechts(id));
        pruefeNachbarn(links(id));
    }

    private boolean kannSpeisen(int id) {
        return zustaende[id] == ZUSTAND.HUNGERT &&
                zustaende[links(id)] != ZUSTAND.SPEIST &&
                zustaende[rechts(id)] != ZUSTAND.SPEIST;
    }

    private void pruefeNachbarn(int id) {
        if(kannSpeisen(id)) {
            try {
                nimmGabeln(id);
            } catch (InterruptedException ignored) {}
            notifyAll();
        }
    }

    private int links(int id) {
        return (id + anzahlPhilosophen - 1) % anzahlPhilosophen;
    }

    private int rechts(int id) {
        return (id + 1) % anzahlPhilosophen;
    }
}
/* Aufgabe 1 */
class Sanduhr {

    public static void main(String[] args) {
        Sanduhr su = new Sanduhr();
        new Thread(() -> {
            while(true) {
                System.out.print(".");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }).start();
        su.dauertLange();
        System.exit(0);
    }

    private void dauertLange() {
        System.out.println("Achtung! Das dauert jetzt ein bisschen");
        try {
            Thread.sleep((long)(Math.random() * 5000 + 5000));
        } catch (InterruptedException ignored) {}
        System.out.println("Endlich! Es ist vorbei.");
    }
}