package com.suryaprava.springboot.jdbc.demo.controller;

import com.suryaprava.springboot.jdbc.demo.exception.DuplicateEmployeePresentException;
import com.suryaprava.springboot.jdbc.demo.exception.EmployeeNotFoundException;
import com.suryaprava.springboot.jdbc.demo.exception.NoEmployeeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController {
    @ExceptionHandler(NoEmployeeFoundException.class)
    public ResponseEntity<Object> noEmployeeFound(NoEmployeeFoundException employee) {
        return new ResponseEntity<>("No employee found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFound(EmployeeNotFoundException employee) {
        return new ResponseEntity<>("Employee id " + employee.getId() + " not found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmployeePresentException.class)
    public ResponseEntity<Object> duplicateEmployee(DuplicateEmployeePresentException employee) {
        return new ResponseEntity<>("Duplicate employee id " + employee.getId() + " present!", HttpStatus.CONFLICT);
    }
}
