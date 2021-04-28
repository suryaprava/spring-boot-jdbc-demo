package com.suryaprava.springbootdemo.controllers;

import com.suryaprava.springbootdemo.exceptions.DuplicateEmployeePresentException;
import com.suryaprava.springbootdemo.exceptions.EmployeeNotFoundException;
import com.suryaprava.springbootdemo.exceptions.NoEmployeeFoundException;
import com.suryaprava.springbootdemo.models.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    private Map<Long, Employee> employeeCache = new HashMap<Long, Employee>();

    {
        employeeCache.put(Long.valueOf(123), new Employee(123, "Ashish", "Ranjan", "Satapathy", "123456789"));
        employeeCache.put(Long.valueOf(456), new Employee(456, "Snigdha", "Rani", "Sahoo", "123456789"));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Map<Long, Employee> get() {
        if (employeeCache.isEmpty()) {
            throw new NoEmployeeFoundException();
        } else {
            return employeeCache;
        }
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee get(@PathVariable int id) {
        if (employeeCache.containsKey(Long.valueOf(id))) {
            return employeeCache.get(Long.valueOf(id));
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee get(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
            return employeeCache.get(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @RequestMapping(value = "employee/add", method = RequestMethod.POST)
    public Employee add(@RequestBody Employee employee) {
        if (employeeCache.containsKey(employee.getEmployeeId())) {
            throw new DuplicateEmployeePresentException(employee.getEmployeeId());
        } else {
            return employeeCache.put(employee.getEmployeeId(), employee);
        }
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee) {
        if (employeeCache.containsKey(employee.getEmployeeId())) {
            return employeeCache.put(employee.getEmployeeId(), employee);
        } else {
            throw new EmployeeNotFoundException(employee.getEmployeeId());
        }
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public Employee delete(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
            return employeeCache.remove(id);

        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

}
