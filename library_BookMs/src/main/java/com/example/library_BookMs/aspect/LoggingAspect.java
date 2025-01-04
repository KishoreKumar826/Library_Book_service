/**
 * LoggingAspect is an aspect that provides logging functionality before and after the execution
 * of methods in the BookController class.
 * 
 * This aspect uses Spring AOP annotations to define pointcuts and advice.
 * 
 * Annotations:
 * @Aspect - Indicates that this class is an aspect.
 * @Component - Indicates that this class is a Spring component.
 * 
 * Methods:
 * - logbefore(JoinPoint join): Logs a message before the execution of any method in the BookController class.
 * - logAfter(JoinPoint join): Logs a message after the execution of any method in the BookController class.
 * 
 * Pointcuts:
 * - execution(* com.example.library_BookMs.Controller.BookController.*(..)): Matches the execution of any method
 *   in the BookController class.
 * 
 * Advices:
 * - @Before: Executes the logbefore method before the matched method execution.
 * - @After: Executes the logAfter method after the matched method execution.
 * 
 * Example log messages:
 * - "------------------------------methodName is starting------------------------------"
 * - "------------------------------methodName is ended------------------------------"
 */
package com.example.library_BookMs.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
      @Before("execution(* com.example.library_BookMs.Controller.BookController.*(..))")
    public void logbefore(JoinPoint join){
        System.out.println("------------------------------"+join.getSignature().getName() +" is starting------------------------------");
    }

    @After("execution(* com.example.library_BookMs.Controller.BookController.*(..))")
    public void logAfter(JoinPoint join){
        System.out.println("------------------------------"+join.getSignature().getName() +" is ended------------------------------");
    }

}
