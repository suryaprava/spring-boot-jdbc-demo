package com.suryaprava.springboot.jdbc.demo.controller;

import com.suryaprava.springboot.jdbc.demo.dao.EmployeeDao;
import com.suryaprava.springboot.jdbc.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> get() {
        return new ResponseEntity<>(employeeDao.getEmployees(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@PathVariable int id) {
        return new ResponseEntity<Employee>(employeeDao.getEmployee(Long.valueOf(id)), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@RequestParam("id") long id) {
        return new ResponseEntity<Employee>(employeeDao.getEmployee(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = "employee/add", method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) throws Exception {
        employeeDao.adddEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Employee employee) {
        return new ResponseEntity<String>("Service not available.", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id) {
        return new ResponseEntity<String>("Service not available.", HttpStatus.BAD_REQUEST);
    }

}
