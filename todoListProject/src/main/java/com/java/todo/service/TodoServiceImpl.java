package com.java.todo.service;

import com.java.todo.dto.TodoRequestDto;
import com.java.todo.dto.TodoResponseDto;
import com.java.todo.entity.TodoListEntity;
import com.java.todo.enumclass.Priority;
import com.java.todo.enumclass.Status;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.java.todo.repository.TodoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TodoServiceImpl implements TodoService {

   @Autowired
   private TodoRepository todorepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public TodoResponseDto createTasks(TodoRequestDto Dto){

        TodoListEntity todoEntity = new TodoListEntity();

        todoEntity.setTitle(Dto.getTitle());
        todoEntity.setDescription(Dto.getDescription());
        todoEntity.setDueDate(Dto.getDueDate());
        todoEntity.setPriority(Dto.getPriority());

        todoEntity = todorepository.save(todoEntity);

        TodoResponseDto savedDto = new TodoResponseDto();

        savedDto.setId(todoEntity.getId());
        savedDto.setTitle(todoEntity.getTitle());
        savedDto.setDescription(todoEntity.getDescription());
        savedDto.setDueDate(todoEntity.getDueDate());
        savedDto.setPriority(todoEntity.getPriority());
        savedDto.setStatus(Status.PENDING);

       return savedDto;
    }

    public List<TodoResponseDto> viewAllTasks(){
       List<TodoListEntity> listOfAllTasks = todorepository.findAll();
      return listOfAllTasks.stream().map(this::collectDto).collect(Collectors.toList());
    }

    private TodoResponseDto collectDto(TodoListEntity todolist){
        TodoResponseDto viewDto = new TodoResponseDto();
        viewDto.setId(todolist.getId());
        viewDto.setTitle(todolist.getTitle());
        viewDto.setDescription(todolist.getDescription());
        viewDto.setDueDate(todolist.getDueDate());
        viewDto.setPriority(todolist.getPriority());
        viewDto.setStatus(todolist.getStatus());

        return viewDto;

    }

    public TodoResponseDto viewSpecificTasks(@PathVariable Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
       TodoListEntity SpecificTasks = todorepository.findById(id).orElseThrow(() -> new NoSuchElementException("Entity not found"));

       TodoResponseDto todoViewdto = new TodoResponseDto();


       todoViewdto.setId(SpecificTasks.getId());
       todoViewdto.setTitle(SpecificTasks.getTitle());
       todoViewdto.setDescription(SpecificTasks.getDescription());
       todoViewdto.setDueDate(SpecificTasks.getDueDate());
       todoViewdto.setPriority(SpecificTasks.getPriority());
       todoViewdto.setStatus(SpecificTasks.getStatus());

       return todoViewdto;
    }

    public ResponseEntity<TodoResponseDto> updateTask(Long id, TodoRequestDto Dto){
        TodoListEntity listUpdateTask = todorepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id " + id));

        TodoResponseDto todoResponseDtoUpdateTask = new TodoResponseDto();

        todoResponseDtoUpdateTask.setId(id);
        todoResponseDtoUpdateTask.setTitle(Dto.getTitle());
        todoResponseDtoUpdateTask.setDescription(Dto.getDescription());
        todoResponseDtoUpdateTask.setDueDate(Dto.getDueDate());
        todoResponseDtoUpdateTask.setPriority(Dto.getPriority());

        TodoListEntity todoList = new TodoListEntity();

        todoList.setId(todoResponseDtoUpdateTask.getId());
        todoList.setTitle(todoResponseDtoUpdateTask.getTitle());
        todoList.setDescription(todoResponseDtoUpdateTask.getDescription());
        todoList.setDueDate(todoResponseDtoUpdateTask.getDueDate());
        todoList.setPriority(todoResponseDtoUpdateTask.getPriority());
        todoList.setStatus(todoResponseDtoUpdateTask.getStatus());

        todorepository.save(todoList);

        return new ResponseEntity<TodoResponseDto>(todoResponseDtoUpdateTask,HttpStatus.OK);

    }
    public ResponseEntity<String> deleteTasksById(Long id){

            boolean exist = todorepository.existsById(id);
            if(!exist){
                throw new NoSuchElementException("Entity not found with id " + id);
            }
            todorepository.deleteById(id);
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    public ResponseEntity<TodoResponseDto> updateStatus(Long id, Status status){
        Optional<TodoListEntity> todoListUpdatedStatus = todorepository.findById(id);

        if(todoListUpdatedStatus.isPresent()) {

            TodoListEntity todoList = todoListUpdatedStatus.get();

            todoList.setStatus(status);

            todorepository.save(todoList);

            TodoResponseDto todoResponseDtoStatus = new TodoResponseDto();

            todoResponseDtoStatus.setId(todoList.getId());
            todoResponseDtoStatus.setTitle(todoList.getTitle());
            todoResponseDtoStatus.setDescription(todoList.getDescription());
            todoResponseDtoStatus.setDueDate(todoList.getDueDate());
            todoResponseDtoStatus.setPriority(todoList.getPriority());
            todoResponseDtoStatus.setStatus(status);

            return new ResponseEntity<>(todoResponseDtoStatus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public String connect() {
        String text = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/v1/connect")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return text;
    }

}
