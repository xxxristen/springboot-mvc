package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // this is the primary key which will be auto generated
    private Long id;

    @NotBlank(message="Task cannot be blank.")
    private String task;

    @NotNull(message = "Completed cannot be null.")
    private Boolean completed;

    // Default constructor
    public Task() {

    }

    // Parameterised constructor
    public Task(String task, Boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public Boolean isCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}