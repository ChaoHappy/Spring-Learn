package com.spring.retry.example.reconciliation.job;

import com.spring.retry.example.reconciliation.service.impl.ReconciliationServiceImpl;
import com.spring.retry.example.reconciliation.task.AbstractRetryTask;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@EnableScheduling
@Component
public class ReconRetryTaskJobService {


    @Scheduled(fixedDelay = 20*1000L)
    public void takeRetrySchedule(){
        System.out.println("触发定时任务，当前等待任务还剩 "+ReconciliationServiceImpl.RETRY_TASK_QUEUE.size()+" 个。");
        AbstractRetryTask task = ReconciliationServiceImpl.RETRY_TASK_QUEUE.poll();
        System.out.println("取出，当前队列中还剩 "+ReconciliationServiceImpl.RETRY_TASK_QUEUE.size()+" 个等待任务。");
        Thread thread = new Thread(task);
        thread.start();
    }
}
