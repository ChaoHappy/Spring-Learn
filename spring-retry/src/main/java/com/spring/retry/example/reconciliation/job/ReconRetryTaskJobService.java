package com.spring.retry.example.reconciliation.job;

import com.spring.retry.example.reconciliation.service.impl.ReconciliationServiceImpl;
import com.spring.retry.example.reconciliation.task.AbstractRetryTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ReconRetryTaskJobService {

    @Scheduled(fixedDelay = 500L)
    public void takeRetrySchedule(){
        AbstractRetryTask task = ReconciliationServiceImpl.RETRY_TASK_QUEUE.poll();
        Thread thread = new Thread(task);
        thread.start();
    }

}
