package com.mmddvg.taskmanager.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mmddvg.taskmanager.dto.ErrorResponse;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException arg){
        var errors = new HashMap<String,String>();

        arg.getBindingResult().getAllErrors().forEach(err -> {
            var fieldName = ((FieldError) err).getField();
            errors.put(fieldName,err.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotAuthorizedExecption.class)
    public ResponseEntity<ErrorResponse> handleNotAuthorizedError(NotAuthorizedExecption arg){
        ErrorResponse err = new ErrorResponse(arg);

        return new ResponseEntity<>(err,err.getStatus());

    }
}
