package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/3/3 09:24
 * @Description:
 */
@Component
@Aspect
public class ArithmeticAspect {

    @Pointcut("execution(* com.zm.service..*(..))")
    public void pointcut(){

    }

    @Before("execution(* com.zm.service..*(..))")
    public void before(JoinPoint jp){
        System.out.println("ArithmeticAspect..before()..");
    }


    @After("execution(* com.zm.service..*(..))")
    public void after(JoinPoint jp){
        System.out.println("参数为："+Arrays.toString(jp.getArgs()));
        System.out.println("ArithmeticAspect..after()..");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("ArithmeticAspect..afterReturning()..");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("ArithmeticAspect..afterThrowing()..");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pjp){
        try{
            pjp.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
    }

}
