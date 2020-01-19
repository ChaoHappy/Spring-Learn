package com.spring.retry.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.*;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-retry.xml"})
public class MyServiceImplTest {

    @Autowired
    MyService myService;

    @Autowired
    RetryListener retryListener;

    @Test
    public void test1() throws SQLException {
        myService.retryService("SQL");
    }

    @Test
    public void testRetryTemplate() throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate();
        //设置重试策略 最大重试次数2次（什么时候重试）,默认遇到Exception异常时重试。
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(2);
        retryTemplate.setRetryPolicy(retryPolicy);

        // 设置重试间隔时间 3S
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(3000l);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        // 注册监听器
        retryTemplate.registerListener(retryListener);

        //使用重试
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {

            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                myService.retryService("testRetryTemplate");
                return null;
            }
        }, new RecoveryCallback<Object>() {

            @Override
            public Object recover(RetryContext context) throws Exception {
                System.out.println("testRetryTemplate=========：recover");
                return null;
            }
        });
    }
}
