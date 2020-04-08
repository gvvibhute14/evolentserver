package com.evolent.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class EmployeerExceptionHandler extends ResponseEntityExceptionHandler {
  private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
  private String BAD_REQUEST = "BAD_REQUEST";

  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<ErrorResponse> handleConstraintViolation(
      ConstraintViolationException ex, WebRequest request) {
    List<String> details = ex.getConstraintViolations().parallelStream().map(e -> e.getMessage())
        .collect(Collectors.toList());

    ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AppException.class)
  public final ResponseEntity<ErrorResponse> handleAppException(ConstraintViolationException ex,
      WebRequest request) {
    List<String> details = ex.getConstraintViolations().parallelStream().map(e -> e.getMessage())
        .collect(Collectors.toList());

    ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<String> details = ex.getBindingResult().getFieldErrors().stream()
        .map(x -> x.getDefaultMessage()).collect(Collectors.toList()); 
    ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
