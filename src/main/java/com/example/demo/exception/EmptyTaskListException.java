package com.example.demo.exception;

public class EmptyTaskListException extends Exception {
    // Constructor to handle when the task list is empty
    public EmptyTaskListException(String msg) {
        super(msg);
    }
}
