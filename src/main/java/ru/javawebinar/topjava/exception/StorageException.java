package ru.javawebinar.topjava.exception;

public class StorageException extends RuntimeException {
    private final int id;

    public StorageException(String message, int id) {
        super(message);
        this.id = id;
    }
}
