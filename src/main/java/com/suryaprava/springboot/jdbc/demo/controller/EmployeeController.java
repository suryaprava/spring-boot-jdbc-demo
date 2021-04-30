package com.suryaprava.springboot.jdbc.demo.controller;

import com.suryaprava.springboot.jdbc.demo.dao.EmployeeDao;
import com.suryaprava.springboot.jdbc.demo.model.Employee;
import com.suryaprava.springboot.jdbc.demo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private final EmployeeDao employeeDao;
    private Map<Long, Employee> employeeCache = new HashMap<Long, Employee>();

    {
        employeeCache.put(Long.valueOf(123), new Employee(123, "Ashish", "Ranjan", "Satapathy", "123456789"));
        employeeCache.put(Long.valueOf(456), new Employee(456, "Snigdha", "Rani", "Sahoo", "123456789"));
    }

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
    public int create(@RequestBody Employee employee) throws Exception {
        return employeeDao.adddEmployee(employee);
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        if (employeeCache.containsKey(employee.getEmployeeId())) {
            return new ResponseEntity<Employee>(employeeCache.put(employee.getEmployeeId(), employee), HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException(employee.getEmployeeId());
        }
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
            return new ResponseEntity<Employee>(employeeCache.remove(id), HttpStatus.OK);

        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

}
