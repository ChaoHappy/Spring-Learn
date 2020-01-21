package com.spring.retry.example.reconciliation.service;

import com.spring.retry.example.reconciliation.exception.FileException;
import com.spring.retry.example.reconciliation.exception.NetWorkException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLException;

/**
 * 对账接口
 */
public interface ReconciliationService {
    /**
     * 对账
     * @param reconDate 对账时间 YYYY-MM-DD
     * @return
     */
    @Retryable(
            value={NetWorkException.class, FileException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    Boolean validateTradeResult(String reconDate);

    @Recover
    Boolean recover(NetWorkException e,String reconDate);

    @Recover
    Boolean recover(FileException e,String reconDate);
}
