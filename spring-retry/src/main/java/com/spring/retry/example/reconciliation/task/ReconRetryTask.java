package com.spring.retry.example.reconciliation.task;

import com.spring.retry.example.reconciliation.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReconRetryTask extends AbstractRetryTask {

    private String reconDate;

    private ReconciliationService reconciliationService;

    public ReconRetryTask(String reconDate, ReconciliationService reconciliationService) {
        this.reconDate = reconDate;
        this.reconciliationService = reconciliationService;
    }

    @Override
    public void run() {
        System.out.println("重试对账方法");
        reconciliationService.validateTradeResult(reconDate);
    }

    public String getReconDate() {
        return reconDate;
    }

    public void setReconDate(String reconDate) {
        this.reconDate = reconDate;
    }

    public ReconciliationService getReconciliationService() {
        return reconciliationService;
    }

    public void setReconciliationService(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
