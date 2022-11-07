package edu.school21.chat.exception;

public class NotSavedSubEntityException extends RuntimeException {
    @Override
    public void printStackTrace() {
        System.err.println("NotSavedSubEntityException: Не удалось сохранить сообщение!");
    }
}
