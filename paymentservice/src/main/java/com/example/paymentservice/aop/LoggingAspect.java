package com.example.paymentservice.aop;

import com.example.paymentservice.dto.UserDTO;
import com.example.paymentservice.interceptor.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Autowired
    RequestContext requestContext;

    @Around("execution(* com.example.paymentservice.controller.UserController.getUser(..))")
    public void aroundGetUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering {}", joinPoint.getSignature().getName());
        Object[] methodArgs = joinPoint.getArgs();
        log.info("Received request to get user {}", methodArgs[0]);
        Object res = joinPoint.proceed();

        if (res != null) {
            if (res instanceof ResponseEntity<?> responseEntity) {
                Object body = responseEntity.getBody();
                if (body instanceof UserDTO user) {
                    log.info("User retrieved successfully: email={}", ((UserDTO) body).getUserEmail());
                }
            }
        }
        log.info("Exiting {}", joinPoint.getSignature().getName());
    }

    public void aroundRegister(JoinPoint joinPoint) throws Throwable {

    }
}
