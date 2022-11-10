package com.inspire12.practice.api.scheduler;

import javax.annotation.PostConstruct;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class Scheduler {
    private ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        scheduler.initialize();
    }

    public void stopScheduler() {
        for (Map.Entry<String, ScheduledFuture<?>> a :scheduledFutureMap.entrySet()) {
            a.getValue().cancel(true);
        }
    }

    public void startScheduler(Runnable work, int seconds, String scheduleName) {
//        scheduler.initialize();
        ScheduledFuture<?> task = scheduler.schedule(work, new PeriodicTrigger(seconds, TimeUnit.SECONDS));
        scheduledFutureMap.put(scheduleName, task);
    }

    public void startScheduler(Runnable work, CronExpression cronExpression, String scheduleName) {
//        scheduler.initialize();
        ScheduledFuture<?> task = scheduler.schedule(work, new CronTrigger(cronExpression.toString()));
        scheduledFutureMap.put(scheduleName, task);
    }
}
