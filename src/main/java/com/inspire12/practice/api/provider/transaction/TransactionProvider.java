package com.inspire12.practice.api.provider.transaction;

import java.util.concurrent.Callable;

public interface TransactionProvider {
    void doInNewTransaction(Runnable runnable);

    <T> T doInNewTransaction(Callable<T> callable);

    <T> T doInNewTransactionWithRetryingForUpdate(Callable<T> callable, long backoffMilliseconds, int retryingCount);
}
