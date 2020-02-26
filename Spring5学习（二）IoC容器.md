## Spring5 IoC容器

### 1. Spring IoC容器和Bean简介

 `org.springframework.beans`和`org.springframework.context`包是Spring框架的IoC容器的基础， 该 `BeanFactory` 接口提供了一种高级配置机制，能够管理任何类型的对象。 `ApplicationContext`是的子接口`BeanFactory`。  

![](D:\TyporaDate\Spring\images\beanFactory&ApplicationContext.png)

**面试题：BeanFactory和ApplicationContext区别**

**BeanFactory：**是Spring里面最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能；

**ApplicationContext：**应用上下文，继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能。

- 国际化（MessageSource）
- 访问资源，如URL和文件（ResourceLoader）
- 载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层  
- 消息发送、响应机制（ApplicationEventPublisher）
- AOP（拦截器）
- 两者初始化bean的时机不同

 **两者初始化bean的时机不同** 

 **BeanFactory**在启动的时候不会去实例化Bean，只有从容器中拿Bean的时候才会去实例化 。

 **ApplicationContext**在启动的时候就把所有的Bean全部实例化了。它还可以为Bean配置lazy-init=true来让Bean延迟实例化。

如果选择使用**BeanFactory**还是**ApplicationContext**？（建议使用**ApplicationContext**）

​	延迟实例化的优点：（**BeanFactory**）

- 应用启动的时候占用资源很少；对资源要求较高的应用，比较有优势； 

​	不延迟实例化的优点： （**ApplicationContext**）

-  所有的Bean在启动的时候都加载，系统运行的速度快； 
- 在启动的时候所有的Bean都加载了，我们就能在系统启动的时候，尽早的发现系统中的配置问题 
- 建议web应用，在启动的时候就把所有的Bean都加载了。（把费时的操作放到系统启动中完成）

```java
//测试结果：
/**
     ===========
     初始化Person...
     bean.Person@29774679
     */
    @Test
    public void testBeanFactory() {
        Resource resource = new ClassPathResource("SpringInitBean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        System.out.println("===========");
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    /**
     初始化Person...
     =============
     bean.Person@1a8a8f7c
     */
    @Test
    public void testApplicationContext(){
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("SpringInitBean.xml");
        System.out.println("=============");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

```

### 2. Bean实例化方式

- 构造器实例化（无参构造器）
- 静态工厂方法实例化
- 实例工厂方法实例化
- 实现FactoryBean接口实例化

#### 2.1 构造器实例化（*）

```java
public class Person {
    public Person(){
        System.out.println("初始化Person...");
    }
}
```

```xml
<bean id="person" class="bean.Person" />
```

#### 2.2 静态工厂方法实例化

```java
// 不通过new对象进行创建Dog，统一通过静态工厂方式创建Dog。
public class Dog {
}
public class DogFactory {
    public static Dog  createInstance(){
        return new Dog();
    }
}
```

```xml
<!--静态工厂方法实例化bean-->
<!--class="静态工厂类" factory-method="创建对象的方法名" -->
<bean id="dog" class="bean.DogFactory" factory-method="createInstance"/>
```

#### 2.3 实例工厂方法实例化

```java
public class Cat {
}
public class CatFactory {
    public Cat createInstance(){
        return new Cat();
    }
}
```

```xml
<!--实例工厂方法实例化bean-->
<!--
    先创建工厂bean对象，在创建真实bean对象
    factory-bean="实例工厂对象bean的ID"
    factory-method="实例工厂对象创建真实bean对象的方法名"
-->
<bean id="catFactory" class="bean.CatFactory"/>
<bean id="cat" factory-bean="catFactory" factory-method="createInstance"/>
```

#### 2.4 实现FactoryBean接口实例化（*）

```java
public class Country {
}
public class CountryFactory implements FactoryBean<Country> {
    @Override
    public Country getObject() throws Exception {
        return new Country();
    }

    @Override
    public Class<?> getObjectType() {
        return Country.class;
    }

    /**
     * 设置bean对象Scope。默认是单例模式
     */
//    @Override
//    public boolean isSingleton() {
//        return false;
//    }
}
```

```xml
<!--实现FactoryBean接口实例化-->
<bean id="country" class="bean.CountryFactory"/>
```

#### 2.5 面试题：BeanFactory和FactoryBean区别？

**BeanFactory**：以Factory结尾，表示它是一个工厂类(接口)，用于管理Bean的一个工厂。在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。

**FactoryBean**：以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果要获取FactoryBean对象，在id前面加一个&符号来获取。

### 3. Bean作用域

在Spring容器中指其创建的Bean对象相对于其他Bean对象的请求可见范围。

```xml
<bean id="" class="" scope="可见范围作用域"/>
```

| Scope值       | 解释                                                       |
| ------------- | ---------------------------------------------------------- |
| **Singleton** | 单例模式：在spring容器中仅存在一个Bean实例                 |
| **propotype** | 多例模式：每次容器调用该Bean时，都会创建一个新的Bean实例。 |
| request       | 用于web开发，将bean对象放入request作用域范围。             |
| session       | 用于web开发，将bean对象放入session作用域范围。             |
| application   | 用于web开发，将bean对象放入application作用域范围。         |
| websocket     | 用于web开发，将bean对象放入websocket作用域范围。           |

常用值有**Singleton**和**propotype**。spring5中没有globalSession。

### 4. Bean生命周期![](D:\TyporaDate\Spring\images\Bean生命周期.png)

测试结果：

```txt
实例化MyBeanFactoryPostProcessor
执行了MyBeanFactoryPostProcessor中的postProcessBeanFactory方法
实例化MyBeanPostProcesser
实例化MyInstantiationAwareBeanPostProcessorAdapter
实例化MyInstantiationAwareBeanPostProcessor
执行了MyInstantiationAwareBeanPostProcessorAdapter中的postProcessBeforeInstantiation。。
创建User Bean对象。。。
执行了MyInstantiationAwareBeanPostProcessorAdapter中的postProcessAfterInstantiation。。
执行了MyInstantiationAwareBeanPostProcessor中的postProcessAfterInstantiation...
执行User的setId方法执行
执行User的setName方法执行
执行User的setBeanName方法
执行User的setBeanFactory方法
执行了MyBeanPostProcesser中的postProcessBeforeInitialization方法
执行User的afterPropertiesSet方法
执行了User的UserinitMethod方法
执行了MyBeanPostProcesser中的postProcessAfterInitialization方法
lifecycle.User@396e2f39
执行User的destroy方法
执行了User的UserDestroyMethod方法
```

**总结**：bean对象的主要生命周期

- 实例化bean对象
- 注入bean对象属性
- 调用<bean>中init-method指定的方法
- Bean创建完成，可以在容器中正常使用
- 调用<bean>中的destroy-method指定的方法，销毁bean对象























