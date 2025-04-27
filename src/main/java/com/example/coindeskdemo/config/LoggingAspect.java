package com.example.coindeskdemo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.coindeskdemo.controller..*)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logRequest(JoinPoint jp) {
        logger.info("Entering: " + jp.getSignature() + " args=" + java.util.Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut="controllerMethods()", returning="result")
    public void logResponse(JoinPoint jp, Object result) {
        logger.info("Exiting: " + jp.getSignature() + " result=" + result);
    }
}
