package com.suryaprava.springbootdemo.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    
    private long id; 
    
    public EmployeeNotFoundException(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id; 
    }
}
