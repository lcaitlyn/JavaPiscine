package edu.school21.classes;

import java.lang.String;

public class User {
    private String firstName;
    private String lastName;
    private int height;

    public User() {
        this.firstName = "Default first name";
        this.lastName = "Default last name";
        this.height = 0;
    }

    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    public int grow(int value) {
        this.height += value;
        return height;
    }

    public int grow2(int value, double d, float f, String s) {
        this.height += value;
        return height;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", height=" + height +
                '}';
    }
}