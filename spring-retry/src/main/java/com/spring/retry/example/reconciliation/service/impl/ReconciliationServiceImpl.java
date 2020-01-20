package com.spring.retry.example.reconciliation.service.impl;

import com.spring.retry.example.reconciliation.exception.BizException;
import com.spring.retry.example.reconciliation.exception.NetWorkException;
import com.spring.retry.example.reconciliation.exception.ValidateException;
import com.spring.retry.example.reconciliation.service.ReconciliationService;
import com.spring.retry.example.reconciliation.task.AbstractRetryTask;
import com.spring.retry.example.reconciliation.task.ReconRetryTask;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

@EnableRetry
@Service
public class ReconciliationServiceImpl implements ReconciliationService {

    /**
     * 重试任务队列，执行失败的任务将会加入到此队列中，等待被重试。
     * 这是一个优先队列，泛型是 {@link AbstractRetryTask}。
     * 这个类实现了 {@link Comparable#compareTo(Object)} 方法，比较方式是下次执行时间，
     */
    public static final PriorityBlockingQueue<AbstractRetryTask> RETRY_TASK_QUEUE = new PriorityBlockingQueue<AbstractRetryTask>();

    /**
     * 对账
     * @param reconDate 对账时间 YYYY-MM-DD
     * @return
     */
    @Override
    public void validateTradeResult(String reconDate) {
        System.out.println("准备对账："+reconDate);
        //1、获取对账文件数据
        List<Map<String, String>> recons = listRecons(reconDate);
        //2、校验对账文件格式
        validateRecons(recons);
        //3、发起对账
        validateTradeResult(recons);
        System.out.println("对账结束："+reconDate);
    }


    @Override
    public void recover(NetWorkException e, String reconDate) {
        ReconRetryTask task = new ReconRetryTask(reconDate);
        RETRY_TASK_QUEUE.add(task);
        System.out.println("recover");
    }


    /**
     * 对账
     * @param recons 对账数据
     */
    private void validateTradeResult(List<Map<String, String>> recons){
        // 调用外部系统对账接口
        if(Objects.equals(recons.get(0).get("date"),"2020-02-22")){
            throw new NetWorkException("网络超时");
        }else if (Objects.equals(recons.get(0).get("date"),"2020-02-23")){
            throw new BizException("账不平——业务异常");
        }
    }

    /**
     * 获取对账文件数据
     * @param reconDate 对账时间 YYYY-MM-DD
     * @return
     */
    private List<Map<String,String>> listRecons(String reconDate){
        //1、验证对账日期
        validateReconDate(reconDate);
        //2、读取对账文件
        return readReconFile(reconDate);
    }

    /**
     * 读取对账文件
     * @param reconDate 对账时间 YYYY-MM-DD
     * @return
     */
    private List<Map<String,String>> readReconFile(String reconDate){
        if(Objects.equals(reconDate,"2020-02-20")){
            throw new NetWorkException("读取对账文件失败");
        }
        List<Map<String,String>> recons= new ArrayList<Map<String,String>>();
        Map<String,String> map = new HashMap<String,String>();
        map.put("date","reconDate");
        recons.add(map);
        return recons;
    }

    /**
     * 验证对账日期
     * @param reconDate 对账时间 YYYY-MM-DD
     */
    private void validateReconDate(String reconDate){
        if(reconDate.length()!=10){
            throw new ValidateException("日期格式错误");
        }
    }

    /**
     * 校验对账文件
     * @param recons 对账数据
     */
    private void validateRecons(List<Map<String,String>> recons){
        if(Objects.equals(recons.get(0).get("date"),"2020-02-21")){
            throw new ValidateException("对账文件验证失败");
        }
    }
}
