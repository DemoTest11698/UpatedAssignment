package com.videostream.videoapp.exception;

public class DuplicateUUIDException extends RuntimeException {
    public DuplicateUUIDException(String message) {
        super(message);
    }
}