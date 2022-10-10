package sample.lib.transaction;

import java.util.concurrent.Callable;

public interface TransactionService {
    void doInNewTransaction(Runnable runnable);

    <T> T doInNewTransaction(Callable<T> callable);

    <T> T doInNewTransactionWithRetryingForUpdate(Callable<T> callable);

    <T> T doInNewTransactionWithRetryingForUpdate(Callable<T> callable, long backoffMilliseconds, int retryingCount);

    <T> T doInNewTransactionWithRetryingForInsert(Callable<T> callable);

    <T> T doInNewTransactionWithRetryingForInsert(Callable<T> callable, long backoffMilliseconds, int retryingCount);
}
