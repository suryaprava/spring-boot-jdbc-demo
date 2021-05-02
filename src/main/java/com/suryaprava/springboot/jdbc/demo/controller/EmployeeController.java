package com.suryaprava.springboot.jdbc.demo.controller;

import com.suryaprava.springboot.jdbc.demo.model.Employee;
import com.suryaprava.springboot.jdbc.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> get() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@PathVariable int id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@RequestParam("id") long id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = "employee/add", method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted.", HttpStatus.OK);
    }

}
