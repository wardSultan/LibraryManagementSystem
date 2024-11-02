package com.example.LibraryManagementSystem.Common.Exceptions;


public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
     super(message);
    }
}