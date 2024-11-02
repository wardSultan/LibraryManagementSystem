package com.example.LibraryManagementSystem.Common.Exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with ID " + id + " not found.");
    }
}