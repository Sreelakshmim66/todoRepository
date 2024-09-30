package com.java.todo.dto;

import com.java.todo.enumclass.Priority;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TodoRequestDto {

        @NotNull
        private String title;
        @NotNull
        private String description;
        @NotNull
        private Date dueDate;
        @NotNull
        private Priority priority;


        public TodoRequestDto(){

        };

        public TodoRequestDto(String title, String description, Date dueDate, Priority priority) {

                this.title = title;
                this.description = description;
                this.dueDate = dueDate;
                this.priority = priority;

        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
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


}
