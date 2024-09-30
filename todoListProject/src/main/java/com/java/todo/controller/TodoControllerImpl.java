package com.java.todo.controller;

import com.java.todo.dto.TodoRequestDto;
import com.java.todo.dto.TodoResponseDto;
import com.java.todo.enumclass.Status;
import com.java.todo.service.TodoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
public class TodoControllerImpl implements TodoController {

    @Autowired
    TodoServiceImpl todoservice;

    @PostMapping("/api/tasks")
    public TodoResponseDto createTasks(@Valid @RequestBody TodoRequestDto Dto) {
        return todoservice.createTasks(Dto);
    }

    @GetMapping("/api/tasks")
    public List<TodoResponseDto> viewAllTasks() {
        return todoservice.viewAllTasks();
    }

    @GetMapping("/api/tasks/{id}")
    public TodoResponseDto viewSpecificTasks(@PathVariable Long id) {
        return todoservice.viewSpecificTasks(id);
    }

    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<TodoResponseDto> updateTask(@Valid @PathVariable Long id, @RequestBody TodoRequestDto Dto) {
        return todoservice.updateTask(id, Dto);
    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<String> deleteTasksById(@PathVariable Long id) {
        return todoservice.deleteTasksById(id);
    }

    @PatchMapping("/api/tasks/{id}/{status}")
    public ResponseEntity<TodoResponseDto> updateStatus(@PathVariable Long id, @PathVariable Status status) {
        return todoservice.updateStatus(id, status);
    }

    @GetMapping("/connect")
    public ResponseEntity<String> connect() {
        String text = todoservice.connect();
        return ResponseEntity.ok(text);
    }

}



