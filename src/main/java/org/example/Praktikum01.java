package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Praktikum01 {

    public static void main(String[] args) {
        new Test();
//        RefinedPerson p1 = new RefinedPerson("John", "New York", "Main Street", "12345", "Johnny");
//        Person p2 = new Person("John", "New York", "Main Street", "12345");
//        RefinedPerson p3 = new RefinedPerson("John", "New York", "Main Street", "12345", "Johnny");
//        System.out.println(p1.equals(p2));
//        System.out.println(p1.equals(p3));
    }

}

@AllArgsConstructor
class Rectangle implements Geometry {
    public int breite;
    public int hoehe;

    @Override
    public String toString() {
        return "Rectangle{" +
                "hash=" + hashCode() +
                ", breite=" + breite +
                ", hoehe=" + hoehe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle that = (Rectangle) o;

        return this.breite == that.breite && this.hoehe == that.hoehe;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hoehe;
        result = prime * result + breite;
        return result;
    }

    @Override
    public double berechneUmfang() {
        return 2 * (hoehe + breite);
    }

    @Override
    public double berechneFlaeche() {
        return hoehe * breite;
    }

}

class Text {

    public Text() {
        Rectangle r1 = new Rectangle(5, 5);
        Rectangle r2 = new Rectangle(5, 5);
        System.out.println(r1.equals(r2));
        System.out.println(r1.toString());
        System.out.println(r2.toString());
    }

}
/*------------------------------------ AUFGABE 2 ------------------------------------*/
@AllArgsConstructor
class Circle implements Geometry {

    public int radius;

    @Override
    public String toString() {
        return "Circle{" +
                "hash=" + hashCode() +
                ", radius=" + radius +
                '}';
    }

    @Override
    public double berechneUmfang() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double berechneFlaeche() {
        return Math.PI * radius * radius;
    }

}

interface Geometry {
    double berechneUmfang();
    double berechneFlaeche();
}

class Test {

    public Test() {
        Geometry[] array = new Geometry[]{
                new Rectangle(1, 2),
                new Rectangle(3,4),
                new Rectangle(5,6),
                new Rectangle(7,8),
                new Circle(1),
                new Circle(2),
                new Circle(3),
                new Circle(4),
        };
        for (Geometry g : array) {
            System.out.println(g.toString());

            System.out.println("g.berechneUmfang() = " + g.berechneUmfang());
            System.out.println("g.berechneFlaeche() = " + g.berechneFlaeche());
            System.out.println("---------------------------------------");
        }
    }
}
/*------------------------------------ AUFGABE 3 ------------------------------------*/
@Getter
@Setter
class Person {
    private final String name, city;
    private String street, zipcode;

    public Person(String name, String city, String street, String zipcode) {
        Objects.requireNonNull(name, "parameter 'name' must not be null");
        Objects.requireNonNull(city, "parameter 'city' must not be null");
        
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        
        Person person = (Person) o;
        return name.equals(person.name) &&
                city.equals(person.city) &&
                street.equals(person.street) &&
                zipcode.equals(person.zipcode);
    }
}
/*------------------------------------ AUFGABE 4 ------------------------------------*/
@Getter
@Setter
class RefinedPerson extends Person {

    private String nickName;

    public RefinedPerson(String name, String city, String street, String zipcode, String nickName) {
        super(name, city, street, zipcode);
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        RefinedPerson person = (RefinedPerson) o;
        return nickName.equals(person.nickName) && super.equals(o);
    }
}