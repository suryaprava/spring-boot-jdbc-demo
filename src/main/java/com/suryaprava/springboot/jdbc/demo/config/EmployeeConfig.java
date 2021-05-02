package com.suryaprava.springboot.jdbc.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class EmployeeConfig {

    @Value("${employee.db.table:TABLE_NOT_FOUND}")
    private String table;

    @Value("${employee.db.driver:DRIVER_NOT_FOUND}")
    private String driver;

    @Value("${employee.db.url:NO_DB_FOUND}")
    private String url;

    @Value("${employee.db.user:NO_USER_FOUND}")
    private String user;

    @Value("${employee.db.password:NO_PASSWORD_FOUND}")
    private String password;

    @Bean
    public DataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    @Qualifier("table")
    public String getTable() {
        return table;
    }

}
