package com.java.todo.globalexceptionhandler;

import com.java.todo.dto.ErrorResponseDto;
import com.java.todo.entity.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex)
    {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionMethod(MethodArgumentNotValidException ex){

        List<ValidationErrors> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new ValidationErrors(((FieldError) error).getField(),
                        error.getDefaultMessage()))
                .toList();


        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorField("Validation Error");
        errorResponseDto.setErrorMessage("Validation failed for one more fields");
        errorResponseDto.setValidationErrorList(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);


    }

}
