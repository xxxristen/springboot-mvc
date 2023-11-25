package com.example.demo.controllers;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Task;
import com.example.demo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks - returns response entity with HTTP status code of 200 (OK) and a list of tasks
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }
//    @GetMapping("/")
//    public ResponseEntity<List<Task>> getAllTasks() {
//        List <Task> taskList = taskService.getAllTask();
//        if(!taskList.isEmpty()) {
//            return ResponseEntity.ok(taskList);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.<Task>of());
//        }
//    }


    // Get all completed tasks - returns response entity with HTTP status code of 200 (OK) and a list of completed tasks
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.findAllCompletedTask());
    }
    // Get all incomplete tasks - returns response entity with HTTP status code of 200 (OK) and a list of incomplete tasks
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllInCompleteTask());
    }
    // Create task - returns response entity with HTTP status code of 200 (OK)
    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }
    // Update task - returns response entity with HTTP status code of 200 (OK)
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody Task task) {
        task.setId(id);
        Optional<Task> updatedTask = Optional.ofNullable(taskService.updateTask(task));
        if(!updatedTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(updatedTask.get());
        }

    }
    // Delete task by id - returns response entity with HTTP status code of 200 (OK) and a response body containing boolean value (true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        // TaskService states deleteTask(Task task).
        return ResponseEntity.ok(true);
        // return ResponseEntity.noContent().build(); // If the task is deleted successfully, returns a ResponseEntity object with a 204 No Content status code.
    }
}