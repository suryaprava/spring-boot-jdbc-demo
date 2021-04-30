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

    private final String TABLE = "EMP";
    private final String SELECT = "SELECT * FROM " + TABLE;
    private final String WHERE = "SELECT * FROM " + TABLE + " WHERE EMP_ID = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public EmployeeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("EMP");
    }

    public List<Employee> getEmployees() {
        return jdbcTemplate.query(SELECT, (ResultSet rs, int rowNum) -> {

            Employee employee = new Employee();

            employee.setEmployeeId(rs.getLong("EMP_ID"));
            employee.setName(rs.getString("EMP_NAME"));
            employee.setDepartment(rs.getString("EMP_DEPT"));

            return employee;
        });
    }

    public Employee getEmployee(long id) {
        return jdbcTemplate.queryForObject(WHERE, (ResultSet rs, int rowNum) -> {

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

}
