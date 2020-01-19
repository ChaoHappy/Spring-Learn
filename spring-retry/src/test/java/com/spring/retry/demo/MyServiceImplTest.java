package com.spring.retry.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-retry.xml"})
public class MyServiceImplTest {

    @Autowired
    MyService myService;

    @Test
    public void test1() throws SQLException {
        myService.retryService("SQL");
    }
}
