package com.java.todo.controller;

import com.java.todo.dto.TodoRequestDto;
import com.java.todo.dto.TodoResponseDto;
import com.java.todo.enumclass.Status;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface TodoController {
    public TodoResponseDto createTasks(@Valid @RequestBody TodoRequestDto Dto);
    public List<TodoResponseDto> viewAllTasks();
    public TodoResponseDto viewSpecificTasks(@PathVariable Long id);
    public ResponseEntity<TodoResponseDto> updateTask(@Valid @PathVariable Long id, @RequestBody TodoRequestDto Dto);
    public ResponseEntity<String> deleteTasksById(@PathVariable Long id);
    public ResponseEntity<TodoResponseDto> updateStatus(@PathVariable Long id, @PathVariable Status status);
    public ResponseEntity<String> connect();
}
