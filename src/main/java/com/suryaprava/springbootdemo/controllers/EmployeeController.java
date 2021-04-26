package com.suryaprava.springbootdemo.controllers;

import com.suryaprava.springbootdemo.models.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private Map<Integer, Employee> employeeCache = new HashMap<Integer, Employee>();

    {
        employeeCache.put(123, new Employee(123, "Ashish", "Ranjan", "Satapathy", "123456789"));
        employeeCache.put(456, new Employee(456, "Snigdha", "Rani", "Sahoo", "123456789"));
    }

    @GetMapping
    public Map<Integer, Employee> get() {
        return employeeCache;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Employee get (@PathVariable int id) {
        return employeeCache.get(id);
    }
}
