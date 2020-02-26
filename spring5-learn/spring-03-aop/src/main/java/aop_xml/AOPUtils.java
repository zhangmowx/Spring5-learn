package aop_xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 09:47
 * @Description:
 */
public class AOPUtils {

    public void Beforeinfo(JoinPoint jp){
        System.out.println("AOP Beforeinfo()...");
    }

    public void afterReturning(Object ret){
        System.out.println(ret.toString());
        System.out.println("AOP afterReturning()...");
    }

    public void afterThrowing(Exception ex){
        System.out.println("异常为："+ex.getMessage());
        System.out.println("AOP afterThrowing()...");
    }

    public void afterInfo(){
        System.out.println("AOP afterInfo()...");
    }

    public Object aroundInfo(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        System.out.println("before");
        try{
            retVal = pjp.proceed();
            System.out.println("after return");
        }catch (Exception e){
            System.out.println("after throw");
        }finally {
            System.out.println("after");
        }
        return  retVal;
    }

    /**
     * 获取被增强对象的一些值
     * @param jp
     */
    public void getAdviceInfo(JoinPoint jp){
        System.out.println("代理对象："+jp.getThis().getClass());
        System.out.println("目标对象:"+jp.getTarget().getClass());
        System.out.println("被增强方法参数"+ Arrays.toString(jp.getArgs()));
        System.out.println("签名信息："+jp.getSignature());
        System.out.println("连接点类型："+jp.getKind());
    }

}
