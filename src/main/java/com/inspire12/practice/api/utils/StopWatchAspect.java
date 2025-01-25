package com.inspire12.practice.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Slf4j
@Component
@Aspect
public class StopWatchAspect {
    @Around("@annotation(com.inspire12.practice.api.utils.TimeCheck)")
    public Object stopWatch(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        String className = joinPoint.getSignature().getName();
        StopWatch stopWatch = new StopWatch(className);
        try {
            stopWatch.start();
            proceed = joinPoint.proceed();
        } finally {
            stopWatch.stop();
//            log.info("{} elapsed time :: {}", className, stopWatch.getTotalTimeMillis());
            log.info("{}", stopWatch.shortSummary());
        }
        return proceed;
    }
}
