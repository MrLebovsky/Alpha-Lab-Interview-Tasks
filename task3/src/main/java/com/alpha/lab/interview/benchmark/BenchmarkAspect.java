package com.alpha.lab.interview.benchmark;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Aspect
@Component
public class BenchmarkAspect {
    private static final Logger LOGGER = Logger.getLogger(BenchmarkAspect.class.getName());

    @Around(value = "@annotation(com.alpha.lab.interview.benchmark.Benchmark)")
    public Object processMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object methodResult;
        Instant before = Instant.now();
        methodResult = joinPoint.proceed();
        String executionLog = "Время выполнения метода "
                .concat(joinPoint.getSignature().toShortString())
                .concat(": ")
                .concat(String.valueOf(Duration.between(before, Instant.now()).toMillis()))
                .concat(" мс");
        LOGGER.info(executionLog);

        return methodResult;
    }
}
