package com.inspire12.practice.api.config.logging.performance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TimeCheckerAspect {
    @Around("@annotation(com.inspire12.practice.api.config.logging.performance.TimeChecker)") // 파라미터 어노테이션 적용 1번째 *은 리턴타입을 나타내며 두 번째부터 시작하는 *(.., @User (*), ..)은 @User 애노테이션이 선언된 부분의 양옆의 다른 파라미터 0개 이상을 허용하겠다는 패턴
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        log.warn("start - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.warn("finished - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        return result;
    }
}
