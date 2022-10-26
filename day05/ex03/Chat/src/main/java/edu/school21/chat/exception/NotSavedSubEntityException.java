package edu.school21.chat.exception;

public class NotSavedSubEntityException extends RuntimeException {

    public NotSavedSubEntityException() {
        System.err.println("NotSavedSubEntityException: Не удалось сохранить сообщение!");
    }

    @Override
    public void printStackTrace() {
        System.err.println("NotSavedSubEntityException: Не удалось сохранить сообщение!");
    }
}
