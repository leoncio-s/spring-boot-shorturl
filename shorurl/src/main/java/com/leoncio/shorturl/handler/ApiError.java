package com.leoncio.shorturl.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;


    ApiError(){
        this.timestamp = LocalDateTime.now();
    }

    ApiError(String message){
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    ApiError(HttpStatus status, String message){
        this.status = status; this.message = message; 
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


    public String getTimestamp() {
        return timestamp.toString();
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
