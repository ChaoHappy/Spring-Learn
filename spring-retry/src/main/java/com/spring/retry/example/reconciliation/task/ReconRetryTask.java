package com.spring.retry.example.reconciliation.task;

public class ReconRetryTask extends AbstractRetryTask {

    private String reconDate;

    @Override
    public void run() {
        System.out.println("执行ReconRetryTask run()方法");
    }




    public ReconRetryTask(String reconDate) {
        this.reconDate = reconDate;
    }

    public String getReconDate() {
        return reconDate;
    }

    public void setReconDate(String reconDate) {
        this.reconDate = reconDate;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
