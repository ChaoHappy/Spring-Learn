package com.spring.retry.example.reconciliation;

import com.spring.retry.example.reconciliation.service.ReconciliationService;
import com.spring.retry.example.reconciliation.service.impl.ReconciliationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-retry.xml"})
public class ReconciliationTest {

    @Autowired
    ReconciliationService reconciliationService;

    /**
     * 测试对账
     * 1、对账日期 2020-02-22 网络异常
     * 2、对账日期 2020-02-20 文件异常
     * 3、对账日期 2020-02-21 格式验证异常
     * 4、对账日期 2020-02-23 业务异常
     */
    @Test
    public void testReconciliation() throws InterruptedException {
        reconciliationService.validateTradeResult("2020-02-23");
//        reconciliationService.validateTradeResult("2020-02-22");
        Thread.sleep(50000);
        System.out.println("----------------------------");

    }
}
