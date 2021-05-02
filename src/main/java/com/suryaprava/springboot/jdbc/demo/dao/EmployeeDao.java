package com.suryaprava.springboot.jdbc.demo.dao;

import com.suryaprava.springboot.jdbc.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    @Autowired
    @Qualifier("table")
    private String TABLE;

    @Autowired
    public EmployeeDao(DataSource dataSource, @Qualifier("table") String table) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(table);
    }

    public List<Employee> getEmployees() {
        final String GET_EMPLOYEES = "SELECT * FROM " + TABLE;

        return jdbcTemplate.query(GET_EMPLOYEES, (ResultSet rs, int rowNum) -> {

            Employee employee = new Employee();

            employee.setEmployeeId(rs.getLong("EMP_ID"));
            employee.setName(rs.getString("EMP_NAME"));
            employee.setDepartment(rs.getString("EMP_DEPT"));

            return employee;
        });
    }

    public Employee getEmployee(long id) {
        final String GET_EMPLOYEE_BY_ID = "SELECT * FROM " + TABLE + " WHERE EMP_ID = ?";

        return jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID, (ResultSet rs, int rowNum) -> {

            Employee employee = new Employee();

            employee.setEmployeeId(rs.getLong("EMP_ID"));
            employee.setName(rs.getString("EMP_NAME"));
            employee.setDepartment(rs.getString("EMP_DEPT"));

            return employee;

        }, id);
    }

    public void addEmployee(Employee employee) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("EMP_ID", employee.getEmployeeId());
        parameters.put("EMP_NAME", employee.getName());
        parameters.put("EMP_DEPT", employee.getDepartment());

        simpleJdbcInsert.execute(parameters);
    }

    public void updateEmployee(Employee employee) {
        final String UPDATE_EMPLOYEE = "UPDATE " + TABLE + " SET EMP_NAME = ?, EMP_DEPT = ? WHERE EMP_ID = ?";
        jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getName(), employee.getDepartment(), employee.getEmployeeId());
    }

    public void deleteEmployee(long id) {
        final String DELETE_EMPLOYEE = "DELETE FROM " + TABLE + " WHERE EMP_ID = ?";
        jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }

}
