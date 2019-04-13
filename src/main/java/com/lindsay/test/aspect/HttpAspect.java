package com.lindsay.test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Lindsay
 * @Date: 2018/12/21 14:27
 * @Description:
 */
@Aspect
@Component
public class HttpAspect {


    @Pointcut("execution(* com.lindsay.test.service.impl.UserServiceImpl.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        System.out.println("url=" + request.getRequestURI());

        //method
        System.out.println("method=" + request.getMethod());

        //ip
        System.out.println("ip=" + request.getRemoteAddr());

        //method
        System.out.println("class_method=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //param
        Object[] joinPointArgs = joinPoint.getArgs();
        for (Object arg : joinPointArgs) {
            System.out.println("args=" + arg);
        }

    }

    @After("log()")
    public void doAfter() {
        System.out.println("after");
    }

    @AfterReturning(returning = "obj", pointcut = "log()")
    public void doAfterReturnig(Object obj) {
        System.out.println("reponse=" + obj);
    }

}
