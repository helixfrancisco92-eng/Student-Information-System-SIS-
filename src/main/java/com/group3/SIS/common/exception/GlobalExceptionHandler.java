package com.group3.SIS.common.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        NoSuchCustomerExistsException.class,
        ResourceNotFoundException.class,
        NoHandlerFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler({
        BadRequestException.class,
        IllegalArgumentException.class,
        MethodArgumentTypeMismatchException.class,
        HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, cleanMessage(ex), request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, "You do not have permission to access this resource.", request);
    }

    @ExceptionHandler({
        ConflictException.class,
        DataIntegrityViolationException.class
    })
    public ResponseEntity<ErrorResponse> handleConflict(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, cleanMessage(ex), request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {
        String message = "Method " + ex.getMethod() + " is not supported for this endpoint.";
        return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, message, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse body = new ErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Validation failed.",
            request.getRequestURI(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        ErrorResponse body = new ErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Validation failed.",
            request.getRequestURI(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex, HttpServletRequest request) {
        return buildResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An unexpected error occurred.",
            request
        );
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request) {
        ErrorResponse body = new ErrorResponse(status, message, request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    private String cleanMessage(Exception ex) {
        if (ex instanceof DataIntegrityViolationException) {
            return "Database constraint violation. Check duplicate values, missing required fields, or invalid references.";
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return "Request body is missing or contains invalid JSON.";
        }
        if (ex instanceof MethodArgumentTypeMismatchException mismatchException) {
            return "Invalid value for parameter: " + mismatchException.getName();
        }
        return ex.getMessage() != null ? ex.getMessage() : "Bad request.";
    }
}
