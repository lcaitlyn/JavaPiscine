package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException{
    IllegalNumberException() {
        System.err.println("IllegalNumberException!");
    }
}
