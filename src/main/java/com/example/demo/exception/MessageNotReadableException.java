package com.example.demo.exception;

public class MessageNotReadableException extends RuntimeException{
    public MessageNotReadableException() {
        super("Data sent is not valid. Please try again.");
    }
}
