package com.example.backend.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
    // customizing timestamp serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private String message;
    private String status; // HTTP Status
    private int code; // HTTP Code
    private Object data;

    public ErrorResponse() {
        this.timestamp = new Date();
    }

    public ErrorResponse(String errorMessage, HttpStatus http) {
        timestamp = new Date();
        this.message = errorMessage;
        this.status = http.name();
        this.code = http.value();
    }

    public ErrorResponse(String errorMessage, HttpStatus http, Object data) {
        timestamp = new Date();
        this.message = errorMessage;
        this.status = http.name();
        this.code = http.value();
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public int getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
