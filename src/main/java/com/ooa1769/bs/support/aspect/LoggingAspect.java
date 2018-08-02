package com.ooa1769.bs.support.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("within(com.ooa1769.bs.web..*) || within(com.ooa1769.bs.book.support.*)")
    public void loggingPointcut() {
    }

    @Around("loggingPointcut()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        final String methodName = pjp.getSignature().getName();
        if (!isUtilMethod(methodName)) {
            log.debug("{}(): {}", methodName, pjp.getArgs());
        }

        Object result = pjp.proceed();

        if (!isUtilMethod(methodName)) {
            log.debug("{}(): result={}", methodName, result);
        }
        return result;
    }

    private boolean isUtilMethod(String name) {
        return name.startsWith("get") || name.startsWith("set") || name.equals("toString") || name.equals("equals")
                || name.equals("hashCode");
    }
}