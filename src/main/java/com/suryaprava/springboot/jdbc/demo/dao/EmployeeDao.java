package com.suryaprava.springboot.jdbc.demo.dao;

import com.suryaprava.springboot.jdbc.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeDao {

    private final String SELECT = "SELECT * FROM EMPLOYEES";
    private final String WHERE = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public EmployeeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("EMPLOYEES");
    }

    public List<Employee> getEmployees() {
        return jdbcTemplate.query(SELECT, (ResultSet rs, int rowNum) -> {

            Employee employee = new Employee();

            employee.setEmployeeId(rs.getLong("EMPLOYEE_ID"));
            employee.setFirstName(rs.getString("FIRST_NAME"));
            employee.setLastName(rs.getString("LAST_NAME"));
            employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));

            return employee;
        });
    }

    public Employee getEmployee(long id) {
        return jdbcTemplate.queryForObject(WHERE, (ResultSet rs, int rowNum) -> {

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getLong("EMPLOYEE_ID"));
                employee.setFirstName(rs.getString("FIRST_NAME"));
                employee.setLastName(rs.getString("LAST_NAME"));
                employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                return employee;


        }, id);
    }

    public int adddEmployee(Employee employee) throws Exception {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("EMPLOYEE_ID", employee.getEmployeeId());
        parameters.put("FIRST_NAME", employee.getFirstName());
        parameters.put("LAST_NAME", employee.getLastName());
        parameters.put("PHONE_NUMBER", employee.getLastName());
        parameters.put("EMAIL", "MAIL");
        parameters.put("HIRE_DATE", Date.valueOf("2020-04-29"));
        parameters.put("JOB_ID", "ST_MAN");
        return simpleJdbcInsert.execute(parameters);
    }



}
