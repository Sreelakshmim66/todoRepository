package com.java.todo.repository;

import com.java.todo.entity.TodoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;




public interface TodoRepository extends JpaRepository<TodoListEntity, Long>{

}

