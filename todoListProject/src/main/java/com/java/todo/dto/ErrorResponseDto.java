package com.java.todo.dto;

import com.java.todo.entity.ValidationErrors;

import java.util.List;

public class ErrorResponseDto {
    private String errorField;
    private String errorMessage;
    private List<ValidationErrors> validationErrorList;

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
     public List<ValidationErrors> getValidationErrorList() {
        return validationErrorList;
     }
     public void setValidationErrorList(List<ValidationErrors> validationErrorList) {
        this.validationErrorList = validationErrorList;
     }
    public ErrorResponseDto() {
        super();
    }
    public ErrorResponseDto(String errorField, String errorMessage, List<ValidationErrors> validationErrorList) {
        super();
        this.errorField = errorField;
        this.errorMessage = errorMessage;
        this.validationErrorList = validationErrorList;
    }
}