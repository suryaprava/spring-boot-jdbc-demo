package com.suryaprava.springboot.jdbc.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private long id;

    public EmployeeNotFoundException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
