package com.basejava.lesson_5.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("ERROR: Резюме с указанным uuid не существует в хранилище", uuid);
    }
}
