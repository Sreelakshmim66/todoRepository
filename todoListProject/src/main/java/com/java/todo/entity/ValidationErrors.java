package com.java.todo.entity;

public class ValidationErrors {

    private String errorField;
    private String errorMessage;

    public ValidationErrors(String errorField, String errorMessage) {
        this.errorField = errorField;
        this.errorMessage = errorMessage;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
