package com.suryaprava.springboot.jdbc.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class EmployeeConfig {

    @Value("${employee.db.driver:DRIVER_NOT_FOUND}")
    private String driver;

    @Value("${employee.db.url:NO_DB_FOUND}")
    private String url;

    @Value("${employee.db.user:NO_USER_FOUND}")
    private String user;

    @Value("${employee.db.password:NO_PASSWORD_FOUND}")
    private String password;

    private DriverManagerDataSource dataSource;

    public String getDriver() {
        return driver;
    }

    @Bean
    public DataSource getDataSource() {

        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
