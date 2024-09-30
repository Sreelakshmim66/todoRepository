package com.java.todo.dto;

import com.java.todo.enumclass.Priority;
import com.java.todo.enumclass.Status;

import java.util.Date;

public class TodoResponseDto {


    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private Priority priority;
    private Status status;

    public TodoResponseDto(){
    };

    public TodoResponseDto(Long id, String title, String description,Date dueDate, Priority priority,Status status)  {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
