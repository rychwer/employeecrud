package com.exam.richer.EmployeeCrud.controller.exception;

import com.exam.richer.EmployeeCrud.domain.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddresApiNotAvailableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(AddresApiNotAvailableException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInternalErrorException(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        List<String> errors = Collections.singletonList("the param: " + name + " is required.");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new ErrorResponse(errors), headers, HttpStatus.BAD_REQUEST);
    }

}
