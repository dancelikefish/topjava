package ru.javawebinar.topjava.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(int id) {
        super("Meal " + id + " doesn't exist", id);
    }
}
