package com.example.task.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LogInputAspect {

    @Around("execution(* com.example.task.services.*.*(..))" +
            "execution(* com.example.task.repositories.*.*(..))")
    public Object logMethodParams(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();

        log.info("\n Class Name: {}\n Method Name: {}\n Input Params: {}", className, methodName, Arrays.toString(params));

        return joinPoint.proceed();
    }

}
