package com.suryaprava.springboot.jdbc.demo.service;

import com.suryaprava.springboot.jdbc.demo.dao.EmployeeDao;
import com.suryaprava.springboot.jdbc.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    public Employee getEmployee(long id) {
        return employeeDao.getEmployee(id);
    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }
}
