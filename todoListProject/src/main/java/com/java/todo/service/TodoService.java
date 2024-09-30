package com.java.todo.service;

import com.java.todo.dto.TodoRequestDto;
import com.java.todo.dto.TodoResponseDto;
import com.java.todo.enumclass.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface TodoService {
    public TodoResponseDto createTasks(TodoRequestDto Dto);
    public List<TodoResponseDto> viewAllTasks();
    public TodoResponseDto viewSpecificTasks(Long id);
    public ResponseEntity<String> deleteTasksById(Long id);
    public ResponseEntity<TodoResponseDto> updateTask(Long id, TodoRequestDto Dto);
    public ResponseEntity<TodoResponseDto> updateStatus(Long id, Status status);
    public String connect();
}
