package com.group3.SIS.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private HttpStatus status;
    private int statusCode;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(HttpStatus status, String message) {
        this(status, message, null, null);
    }

    public ErrorResponse(HttpStatus status, String message, String path) {
        this(status, message, path, null);
    }

    public ErrorResponse(HttpStatus status, String message, String path, Map<String, String> errors) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.path = path;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}
