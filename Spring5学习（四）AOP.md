

## Spring AOP

> 面向方面的编程（AOP）通过提供另一种思考程序结构的方式来补充面向对象的编程（OOP）。OOP中模块化的关键单元是类，而在AOP中模块化是方面。方面使关注点（例如事务管理）的模块化跨越了多个类型和对象。（这种关注在AOP文献中通常被称为“跨领域”关注。）
>
> Spring的关键组件之一是AOP框架。虽然Spring IoC容器不依赖于AOP（这意味着您不需要使用AOP），但AOP是对Spring IoC的补充，以提供功能非常强大的中间件解决方案。

> AOP在Spring Framework中用于：
>
> - 提供声明式企业服务。这种服务最重要的是**声明式事务管理**。
> - 让用户实现自定义方面，并通过AOP补充其对OOP的使用。

### 1. AOP概念

-  **Aspect(切面)**： 通常是一个类，里面可以定义切入点和通知 。在Spring AOP中，通过使用常规类（[基于模式的方法](https://docs.spring.io/spring/docs/5.1.14.RELEASE/spring-framework-reference/core.html#aop-schema)）或使用注释进行`@Aspect`注释的常规类 （[@AspectJ样式](https://docs.spring.io/spring/docs/5.1.14.RELEASE/spring-framework-reference/core.html#aop-ataspectj)）来实现的。 
-  **Join point( 连接点 )** ：程序执行过程中的一个点，例如方法执行或异常处理。**在Spring AOP中，连接点始终代表方法的执行。** 
- **Advice (通知)**：  AOP在特定的切入点上执行的增强处理 。
-  **Pointcut(切点)** ：  就是带有通知的连接点，在程序中主要体现为书写切入点表达式 。切入点表达式匹配的连接点的概念是AOP的核心，并且Spring默认使用AspectJ切入点表达语言。 
-  **Introduction( 引入 )**： 在不修改代码的前提下，引入可以在**运行期**为类动态地添加一些方法或字段 。
-  **Target object( 目标对象 )**: 包含连接点的对象。也被称作被通知或被代理对象 
-  **AOP proxy ( AOP代理 )**: AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类 。
-  **weave(织入)** ： 将切面应用到目标对象并导致代理对象创建的过程 。

 Spring AOP包括以下类型的**Advice** ： 

- **Before**:在目标方法被调用之前做增强处理,@Before只需要指定切入点表达式即可

- **AfterReturning**: 在连接点正常完成之后要运行的advice（无返回异常）。@AfterReturning

- **AfterThrowing**: 如果方法因抛出异常而退出， 则执行 @AfterThrowing

- **After**:在目标方法完成之后做增强，无论目标方法正常或特殊返回 。@After

- **Around**:环绕通知,在目标方法完成前后做增强处理,环绕通知是最重要的通知类型,像事务,日志等都是环绕通知,注意编程中核心是一个ProceedingJoinPoint

### 2. AOP代理

Spring AOP默认将标准**JDK动态代理**用于AOP代理。也可以使用**CGLIB代理**

JDK动态代理：

- Java动态代理使用的是java.lang.reflect包中的Proxy类与InvocationHandler接口完成实现。
- 要使用JDK动态代理，委托类必须要定义接口
- JDK动态代理将拦截所有public方法（因为只能调用接口中定义的方法）
- 动态代理的最小单位是类（类中所有方法都会被处理），如果只想拦截一部分方法，需要在invoke方法中对要执行的方法名进行判断。

CGLIB代理：

- CGLIB可以生成委托类的之类，重写父类非final修饰的方法。
- 要求类不能是final修饰的，要拦截的方法非final，非static，非private的
- 动态代理的最小单位是类（类中所有方法都会被处理）

### 3. 基于XML配置的AOP

```java
public interface BusinessService {
    void beforeFunc();
    String afterReturnFunc();
    void afterThrowFunc()throws Exception;
    void afterFunc();
    void aroundFunc();
    String adviceFunc(String name,int age);
}

public class BusinessServiceImpl implements BusinessService {


    @Override
    public void beforeFunc() {
        System.out.println("BusinessServiceImpl beforeFunc");
    }

    @Override
    public String afterReturnFunc() {
        System.out.println("BusinessServiceImpl afterReturnFunc");
        return "Hello AOP";
    }

    @Override
    public void afterThrowFunc() throws  Exception{
        System.out.println("BusinessServiceImpl afterThrowFunc");
        int i=1/0;
    }

    @Override
    public void afterFunc() {
        System.out.println("BusinessServiceImpl afterFunc");
    }

    @Override
    public void aroundFunc() {
        System.out.println("BusinessServiceImpl AroundFunc");
    }
    @Override
    public String adviceFunc(String name, int age) {
        System.out.println("BusinessServiceImpl adviceFunc 参数name："+name +",age:"+age);
        return null;
    }
}
//切面类
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
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="logger" class="aop.AOPUtils"/>

    <bean id="businessService" class="aop.service.impl.BusinessServiceImpl"/>

    <aop:config >
        
        <aop:pointcut id="a" expression="execution(* aop.service..beforeFunc(..))"/>
        <aop:pointcut id="b" expression="execution(* aop.service..afterReturnFunc(..))"/>
        <aop:pointcut id="c" expression="execution(* aop.service..afterThrowFunc(..))"/>
        <aop:pointcut id="d" expression="execution(* aop.service..afterFunc(..))"/>
        <aop:pointcut id="e" expression="execution(* aop.service..aroundFunc(..))"/>
        <aop:pointcut id="f" expression="execution(* aop.service..adviceFunc(..))"/>

        <aop:aspect id="logger-aspect" ref="logger">
            <aop:before method="Beforeinfo" pointcut-ref="a"/>
            <aop:after-returning method="afterReturning" pointcut-ref="b" returning="ret"/>
            <aop:after-throwing method="afterThrowing" pointcut-ref="c" throwing="ex"/>
            <aop:after method="afterInfo" pointcut-ref="d"/>
            <aop:around method="aroundInfo" pointcut-ref="e"/>

            <aop:before method="getAdviceInfo" pointcut-ref="f"/>
        </aop:aspect>

    </aop:config>
</beans>
```

####  3.1 <aop:config >  

​	声明一个aop的配置文件

- 需要导入aop标签的schema。

- **aop:config**包含 **aop:pointcut**、**aop:advisor**、**aop:aspect**子标签（必须按照这个顺序）

#### 3.2 \<aop:aspect>

​	声明一个切面类。id属性给切面起个名称，通过**ref**属性关联切面类bean的id值

```xml
<aop:aspect id="" ref="切面类bean的id值">
</aop:aspect>
```

#### 3.3 \<aop:pointcut>

​	声明切入点。id属性给切入点起个名称，**expression**属性描述具体切入点表达式

```xml
<aop:pointcut id="a" expression="切入点表达式"/>
```

切入点表达式语法：

```erlang
execution(
	modifiers-pattern?  访问权限类型(？号表示0-n个可省略)
	ret-type-pattern    返回类型(必须)
	declaring-type-pattern? 全限定名(？号表示0-n个可省略)
	name-pattern(param-pattern) 方法名(参数名)
	throws-pattern?   抛出异常类型(？号表示0-n个可省略)
)
```

*****号：表示0个或多个任意字符

**..**号：可用于全限定名或方法参数中，全限定名中表示子包；方法参数中表示0-N个参数

示例：

```java
execution(public * *(..))  //任何公共方法的执行
execution(* set*(..))      //名称以set开头的任何方法的执行
execution(* com.xyz.service.AccountService.*(..)) //AccountService接口定义的任何方法的执行
execution(* com.xyz.service.*.*(..)) //service包中定义的任何方法的执行
execution(* com.xyz.service..*.*(..)) //service或其子包中中定义的任何方法的执行
```

解读execution表达式方法：先找到（），括号就表示方法。看括号里的参数和括号前面的内容在解读

#### 3.5 advice通知

advice通知包括 before，after，after-returning，after-throwing，around。

- before： method="切面的方法名" pointcut-ref="切入点的id值"

```xml
<aop:before method="切面的方法名" pointcut-ref="切入点的id值"/>
```

- after-returning：method="切面的方法名" pointcut-ref="切入点的id值" 

  returning="返回值应参数的名称obj"。 切面的方法名中必须有参数（Object obj）

```xml
<aop:after-returning method="切面的方法名" pointcut-ref="切入点的id值" returning="返回值应参数的名称"/>
```

- after-throwing：method="切面的方法名" pointcut-ref="切入点的id值" 

  throwing="异常参数的名称ex"。切面的方法名中必须有参数（Exception ex）

```xml
<aop:after-throwing method="切面的方法名" pointcut-ref="切入点的id值" throwing="异常参数的名称"/>
```

- around：method="切面的方法名" pointcut-ref="切入点的id值"

```xml
<aop:around method="切面的方法名" pointcut-ref="切入点的id值"/>
```

```java
//环绕通知的切面方法：返回值类型为Object，方法参数第一个为ProceedingJoinPoint类型参数
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
```

**获取被增强方法信息，并传递给增强方法**

Spring AOP提供了`org.aspectj.lang.JoinPoint`接口，作为增强方法的第一个参数

**JoinPoint**：提供访问当前被增强方法的真实对象，代理对象，方法参数等信息。

**ProceedingJoinPoint**：**JoinPoint**之类，只用于环绕增强中。

**JoinPoint**接口主要方法方法

![](D:\TyporaDate\Spring\images\JoinPoint.png)



