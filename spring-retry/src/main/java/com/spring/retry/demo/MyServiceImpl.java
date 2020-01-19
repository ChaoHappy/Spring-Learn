package com.spring.retry.demo;

import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@EnableRetry
@Service
public class MyServiceImpl implements MyService{

    public void retryService(String sql) throws SQLException {
        System.out.println("retryService");
        throw new SQLException();
    }

    public void recover(SQLException e, String sql) {
        System.out.println("recover");
    }
}
