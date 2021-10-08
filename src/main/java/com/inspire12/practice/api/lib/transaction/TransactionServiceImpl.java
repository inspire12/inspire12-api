package com.inspire12.practice.api.lib.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final long DEFAULT_BACKOFF_MILLISECONDS = 500;
    private static final int DEFAULT_RETRYING_COUNT = 3;
    private String name;
    private PlatformTransactionManager transactionManager;

    @Override
    public void doInNewTransaction(Runnable runnable) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setName("doInNewTransaction" + name);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus txStatus = transactionManager.getTransaction(definition);
        try {
            runnable.run();
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            log.info("Error in do new transaction " + name , e);
            transactionManager.rollback(txStatus);
        }
    }

    @Override
    public <T> T doInNewTransaction(Callable<T> callable) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setName("doInNewTransaction" + name);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus txStatus = transactionManager.getTransaction(definition);
        T result = null;
        try {
            result = callable.call();
        } catch (Exception e) {
            log.info("Error in do new transaction " + name, e);
            transactionManager.rollback(txStatus);
        }
        transactionManager.commit(txStatus);
        return result;
    }

    @Override
    public <T> T doInNewTransactionWithRetryingForUpdate(Callable<T> callable) {
        return doInNewTransactionWithRetryingForUpdate(callable, DEFAULT_BACKOFF_MILLISECONDS, DEFAULT_RETRYING_COUNT);
    }

    @Override
    public <T> T doInNewTransactionWithRetryingForUpdate(Callable<T> callable, long backoffMilliseconds, int retryingCount) {
        int tryingCount = 0;
        while (tryingCount < retryingCount) {
            DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
            definition.setName("doInNewTransactionWithRetryingForUpdate" + name);
            definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

            TransactionStatus txStatus = transactionManager.getTransaction(definition);
            try {
                T result = callable.call();
                transactionManager.commit(txStatus);
                return result;
            } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException | CannotAcquireLockException le) {
                tryingCount++;
                log.debug("Retrying Update: {} {}" ,tryingCount, le.getMessage());
                try {
                    transactionManager.rollback(txStatus);
                } catch (IllegalTransactionStateException itse) {
                    log.debug("ITransactionSE occurred " + name);
                }
                try {
                    TimeUnit.MICROSECONDS.sleep(backoffMilliseconds);
                } catch (InterruptedException e) {
                    log.debug("Transaction sleep error " + name, e);
                }
            } catch (Exception e) {
                log.error("Error in retrying update " + name, e);
                transactionManager.rollback(txStatus);
                break;
            }
        }
        throw new IllegalStateException("Retrying for update over " + name);
    }

    @Override
    public <T> T doInNewTransactionWithRetryingForInsert(Callable<T> callable) {
        return doInNewTransactionWithRetryingForInsert(callable, DEFAULT_BACKOFF_MILLISECONDS, DEFAULT_RETRYING_COUNT);
    }

    @Override
    public <T> T doInNewTransactionWithRetryingForInsert(Callable<T> callable, long backoffMilliseconds, int retryingCount) {
        int tryingCount = 0;
        while (tryingCount < retryingCount) {
            DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
            definition.setName("doInNewTransactionWithRetryingForUpdate" + name);
            definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

            TransactionStatus txStatus = transactionManager.getTransaction(definition);
            try {
                T result = callable.call();
                transactionManager.commit(txStatus);
                return result;
            } catch (DataIntegrityViolationException le) {
                tryingCount++;
                log.debug("Retrying Update: {} {}" ,tryingCount, le.getMessage());
                try {
                    transactionManager.rollback(txStatus);
                } catch (IllegalTransactionStateException itse) {
                    log.debug("ITransactionSE occurred " + name);
                }
                try {
                    TimeUnit.MICROSECONDS.sleep(backoffMilliseconds);
                } catch (InterruptedException e) {
                    log.debug("Transaction sleep error " + name, e);
                }
            } catch (Exception e) {
                log.error("Error in retrying update " + name, e);
                transactionManager.rollback(txStatus);
                break;
            }
        }
        throw new IllegalStateException("Retrying for update over " + name);
    }
}
