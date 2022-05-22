package com.example.hw9.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {
    int nums = 0;
    @Value("${max-request}")
    int maxRequest;


    @Around("within(com.example.hw9.controller.MyController)")
    public Object beforeGetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (nums == maxRequest) {
            log.info("Превышен лимит");
            return null;
        }
        nums++;
        return joinPoint.proceed();
    }
}
