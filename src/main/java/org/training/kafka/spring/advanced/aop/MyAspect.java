package org.training.kafka.spring.advanced.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {


    @Pointcut("execution(* a.b.c.MyAnotherPackageBean.*(String)) && args(xyz)")
    public void intercept(String xyz) {
    }

    @Before("intercept(xyz)")
    public void beforeMethod(JoinPoint joinPointParam,
                             String xyz) {
        System.out.println("Before çalıştı : "
                           + xyz
                           + " joined : "
                           + joinPointParam.toLongString());
    }

    @After("intercept(xyz)")
    public void afterMethod(JoinPoint joinPointParam,
                            String xyz) {
        System.out.println("After çalıştı : "
                           + xyz
                           + " joined : "
                           + joinPointParam.toLongString());
    }

    @AfterReturning(value = "intercept(xyz)", returning = "abc")
    public void afterMethod(JoinPoint joinPointParam,
                            String xyz,
                            String abc) {
        System.out.println("After Returning çalıştı : "
                           + xyz
                           + " returned : "
                           + abc
                           + " joined : "
                           + joinPointParam.toLongString());
    }

    @AfterThrowing(value = "intercept(xyz)", throwing = "abc")
    public void afterMethod(JoinPoint joinPointParam,
                            String xyz,
                            Exception abc) {
        System.out.println("After Returning çalıştı : "
                           + xyz
                           + " exception : "
                           + abc
                           + " joined : "
                           + joinPointParam.toLongString());
    }

    // @Around("execution(* a.b.c.MyAnotherPackageBean.*(String))")
    public Object afterMethod(ProceedingJoinPoint joinPointParam) {
        try {
            Object[] argsLoc  = joinPointParam.getArgs();
            System.out.println("Giren parametre : " + argsLoc[0]);
            Object[] argsLoc2 = new Object[1];
            argsLoc2[0] = "ayşe";
            Object proceedLoc = joinPointParam.proceed(argsLoc2);
            if (proceedLoc instanceof String) {
                String stringLoc = (String) proceedLoc;
                return stringLoc + " intercepted";
            }
            return "test";
        } catch (Throwable exp) {
            return null;
        }

    }

    @Around("execution(* a.b.c.MyAnotherPackageBean.*(String))")
    public Object afterMethodDelta(ProceedingJoinPoint joinPointParam) {
        try {
            long delta = System.nanoTime();
            Object proceedLoc = joinPointParam.proceed();
            System.out.println("Delta : " + (System.nanoTime() - delta));
            return proceedLoc;
        } catch (Throwable exp) {
            return null;
        }

    }


}
