package com.suryaprava.springbootdemo.controllers;

import com.suryaprava.springbootdemo.models.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private Map<Long, Employee> employeeCache = new HashMap<Long, Employee>();

    {
        employeeCache.put(Long.valueOf(123), new Employee(123, "Ashish", "Ranjan", "Satapathy", "123456789"));
        employeeCache.put(Long.valueOf(456), new Employee(456, "Snigdha", "Rani", "Sahoo", "123456789"));
    }

    @RequestMapping("/employees")
    @GetMapping
    public Map<Long, Employee> get() {
        return employeeCache;
    }

//    @GetMapping
//    @RequestMapping("{id}")
//    public Employee get (@PathVariable int id) {
//        return employeeCache.get(id);
//    }

    @GetMapping
    @RequestMapping("/employee")
    public Employee get(@RequestParam("id") long id) {
        System.out.println("Id passed = " + id);
        return employeeCache.get(id);
    }

    @RequestMapping(value = "employee/add", method = RequestMethod.POST)
    public Map<Long, Employee> add(@RequestBody Employee employee) {
        employeeCache.put(employee.getEmployeeId(), employee);
        return employeeCache;
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee) {
        return employeeCache.put(employee.getEmployeeId(), employee);
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public Map<Long, Employee> delete(@RequestParam("id") long id) {
      employeeCache.remove(id);
      return employeeCache;
    }

}
