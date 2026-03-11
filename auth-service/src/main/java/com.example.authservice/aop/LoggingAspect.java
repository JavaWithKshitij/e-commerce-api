package com.example.authservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.example..service..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method Start: {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        log.info("Method End: {}", joinPoint.getSignature());

        return result;
    }
}