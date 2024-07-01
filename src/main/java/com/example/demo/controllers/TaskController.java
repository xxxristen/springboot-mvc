package com.example.demo.controllers;

import com.example.demo.exception.EmptyTaskListException;
import com.example.demo.exception.MessageNotReadableException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Task;
import com.example.demo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks - returns response entity with HTTP status code of 200 (OK) and a list of tasks

    // Original - GFG
//    @GetMapping("/")
//    public ResponseEntity<List<Task>> getAllTasks() {
//        return ResponseEntity.ok(taskService.getAllTask());
//    }
    // My version - Msg to be returned if list is empty.
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() throws EmptyTaskListException {
        List <Task> taskList = taskService.getAllTask();
        if(taskList.isEmpty()) {
            throw new EmptyTaskListException("No task available.");
        }
        return ResponseEntity.ok(taskList);
    }

    // Get all completed tasks - returns response entity with HTTP status code of 200 (OK) and a list of completed tasks
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() throws EmptyTaskListException {
        List <Task> taskList = taskService.findAllCompletedTask();
        if(taskList.isEmpty()) {
            throw new EmptyTaskListException("No task available.");
        }
        return ResponseEntity.ok(taskList);
    }
    // Get all incomplete tasks - returns response entity with HTTP status code of 200 (OK) and a list of incomplete tasks
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() throws EmptyTaskListException {
        List <Task> taskList = taskService.findAllInCompleteTask();
        if(taskList.isEmpty()) {
            throw new EmptyTaskListException("No task available.");
        }
        return ResponseEntity.ok(taskList);
    }
    // Create task - returns response entity with HTTP status code of 200 (OK)
    @PostMapping("/")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
            return new ResponseEntity<>(taskService.createNewTask(task), HttpStatus.CREATED);
    }
    // Update task - returns response entity with HTTP status code of 200 (OK)
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@Valid @PathVariable Long id, @Valid @RequestBody Task task) {
        // If task cannot be found by id, throw ResourceNotFoundException
        if(taskService.findTaskById(id)==null) {
            throw new ResourceNotFoundException(id);
        }
        else {
            task.setId(id);
            return ResponseEntity.ok(taskService.updateTask(task));
        }
    }

    // Delete task by id - returns response entity with HTTP status code of 200 (OK) and a response body containing boolean value (true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@Valid @PathVariable Long id) {
        // If task cannot be found by id, throw ResourceNotFoundException
        if (taskService.findTaskById(id)==null) {
            throw new ResourceNotFoundException(id);
        }
        else {
        taskService.deleteTask(id);
        // TaskService states deleteTask(Task task).
        // Because the method returns <Boolean> so keeping the return ResponseEntity.ok(true);
        return ResponseEntity.ok(true);}
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@Valid @PathVariable Long id) {
        // If task cannot be found by id, throw ResourceNotFoundException
        if (taskService.findTaskById(id)==null) {
            throw new ResourceNotFoundException(id);
        }
        else {
            return ResponseEntity.ok(taskService.findTaskById(id));
        }
    }
}