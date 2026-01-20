package com.kinya.kinya_backend.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resourceName) {
        super(resourceName + " Not Found");
    }
}
