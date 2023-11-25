package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String id){
        super("Could not find task: "+id+".");
    }
}