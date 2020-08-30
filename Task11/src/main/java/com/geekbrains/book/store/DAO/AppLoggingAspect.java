package com.geekbrains.book.store.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@AllArgsConstructor
@Getter
public class AppLoggingAspect {
    private Map<String, Map<String, Integer>> statistic;

    @After("execution(public * com.geekbrains.book.store.services..*(..))") // pointcut expression
    public void statistic(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getDeclaringTypeName().split("\\.")[5];
        String method = joinPoint.getSignature().getName();
        Map <String, Integer> methodMap = statistic.getOrDefault(name, new HashMap<>());
        methodMap.put(method, methodMap.getOrDefault(method, 0) + 1);
        statistic.put(name, methodMap);
    }
}
