package com.imooc.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class TimeAspect {
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("进入切片，可以拿到拦截的方法及参数值");
        Object[] args = pjp.getArgs();
        Object object = pjp.proceed();
        return object;
    }

}
