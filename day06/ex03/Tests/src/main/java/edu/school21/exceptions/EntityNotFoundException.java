package edu.school21.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        System.err.println("User not found!");
    }
}
