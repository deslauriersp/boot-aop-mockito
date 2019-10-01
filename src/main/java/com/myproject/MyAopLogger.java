package com.myproject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAopLogger {

    MyLogWriter myLogWriter;

    @Autowired
    public MyAopLogger(MyLogWriter myLogWriter) {
        this.myLogWriter = myLogWriter;
    }

    @Before(value = "execution(* com.myproject.MyService.*(..))")
    public void before(JoinPoint joinPoint) {
        log("before");
    }

    @AfterReturning(value = "execution(* com.myproject.MyService.*(..))", returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        log("after");
    }

    @AfterThrowing(value = "execution(* com.myproject.MyService.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log("afterThrowing");
    }

    private void log(String message) {
        myLogWriter.writeLog(message);
    }

}
