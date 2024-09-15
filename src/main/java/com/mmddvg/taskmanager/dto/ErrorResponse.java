package com.mmddvg.taskmanager.dto;

import com.mmddvg.taskmanager.exceptions.NotAuthorizedExecption;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
    public String message;
    private HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public ErrorResponse(NotAuthorizedExecption err){
        this.message = err.getMessage();
        this.status = HttpStatus.UNAUTHORIZED;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
