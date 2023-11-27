package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Constructor to handle when task with id not found
    public ResourceNotFoundException(Long id){
        super("Could not find task: "+id+".");
    }
}