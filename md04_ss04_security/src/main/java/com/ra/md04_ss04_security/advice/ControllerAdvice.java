package com.ra.md04_ss04_security.advice;

import com.ra.md04_ss04_security.model.error.DataError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String, String>> handleNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new DataError<>(errors, HttpStatus.BAD_REQUEST.value()); //400
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError< String> handleNoSuchElementException(NoSuchElementException e) {
        return new DataError<>(e.getMessage(),404); //400
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError< String> handleResourceNotFoundException(NoResourceFoundException e) {
        return new DataError< String>(e.getMessage(), 404);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError< String> handleResourceNotFoundException(DataIntegrityViolationException e) {
        return new DataError< String>(e.getMessage(), 400);
    }


    // Xử lý lỗi 404
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public DataError<Map<String, String>> handleNotFoundError(NoHandlerFoundException ex, Model model) {
//        model.addAttribute("error", "404 - Page Not Found");
//        model.addAttribute("message", "The page you are looking for does not exist.");
//        return "error/404";
//    }

    // Xử lý lỗi 500
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public DataError<Map<String, String>> handleInternalServerError(Exception ex, Model model) {
//        model.addAttribute("error", "500 - Internal Server Error");
//        model.addAttribute("message", "An unexpected error occurred. Please try again later.");
//        return "error/500";
//    }
}
