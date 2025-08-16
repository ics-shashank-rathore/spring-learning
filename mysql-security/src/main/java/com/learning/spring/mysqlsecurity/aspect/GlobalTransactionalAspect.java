package com.learning.spring.mysqlsecurity.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * advice -> Action like before, after
 * Pointcuts -> expression where class or on  method
 */
@Aspect
@Component
public class GlobalTransactionalAspect {

    private final PlatformTransactionManager transactionManager;

    public GlobalTransactionalAspect(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Around("execution(* com.learning.spring.mysqlsecurity.service..*(..)) || execution(* com.learning.spring.mysqlzoo.services..*(..))")
    public Object transactionalHandling(ProceedingJoinPoint joinPoint) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(status -> {
            try {
                return joinPoint.proceed();
            } catch (RuntimeException e) {
                status.setRollbackOnly();
                throw e;
            } catch (Throwable ex) {
                status.setRollbackOnly();
                throw new RuntimeException(ex);
            }
        });
    }

    @Before("withinDesignator()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("📌 Before controller method: " + joinPoint.getSignature());
    }

    @After("execution(* com.learning.spring.mysqlzoo.controller.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("📌 After controller method: " + joinPoint.getSignature());
    }

    @AfterReturning("execution(* com.learning.spring.mysqlzoo.controller.*.*(..))")
    public void afterReturningAdvice(JoinPoint joinPoint) {
        System.out.println("✅ After returning from: " + joinPoint.getSignature());
    }

    @AfterThrowing("execution(* com.learning.spring.mysqlzoo.controller.*.*(..))")
    public void afterThrowingAdvice(JoinPoint joinPoint) {
        System.out.println("❌ Exception thrown in: " + joinPoint.getSignature());
    }
    
    @Pointcut("execution(* com.learning.spring.mysqlzoo.controller.*.*(..))")
    public void executeDesignator() {}
    
    /**
     * For class
     */
    @Pointcut("within(com.learning.spring.mysqlzoo.controller.UserController)")
    public void withinDesignator() {}
    
    /**
     * Work on class having @Service annotation
     */
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void withinAnnotationDesignator() {}
    
    /**
     * Work on function
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void annotationDesignator() {}
}
