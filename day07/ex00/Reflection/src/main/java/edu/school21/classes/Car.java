package edu.school21.classes;

public class Car {
    private String name;
    private String color;
    private int maxSpeed;

    public Car() {
        name = "Default name";
        color = "Default color";
        maxSpeed = 0;
    }

    public Car(String name, String color, int maxSpeed) {
        this.name = name;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public int increaseMaxSpeed(int speed) {
        maxSpeed += speed;
        return maxSpeed;
    }

    public void printMethod(String s, Integer i, Double d, Boolean b, Long l) {
        System.out.println(s);
        System.out.println(i);
        System.out.println(d);
        System.out.println(b);
        System.out.println(l);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
