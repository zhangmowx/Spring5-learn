
 ## Spring 事务

### 1. 事务

> 事务（Transaction），一般是指要做的或所做的事情。在计算机术语中是指访问并可能更新数据库中各种数据项的一个程序执行单元(unit)。事务通常由高级数据库操纵语言或编程语言（如SQL，C++或Java）书写的用户程序的执行所引起，并用形如begin transaction和end transaction语句（或函数调用）来界定。事务由事务开始(begin transaction)和事务结束(end transaction)之间执行的全体操作组成。
>
> 事务是恢复和[并发控制](https://baike.baidu.com/item/并发控制)的基本单位。

### 2. 为什么要事务

 事务是为解决数据安全操作提出的，事务控制实际上就是控制数据的安全访问。

### 3. 事务的4个特性（ACID）

事务应该具有4个属性：原子性、一致性、隔离性、持久性。这四个属性通常称为**ACID特性**。

- 原子性（atomicity）。一个事务是一个不可分割的工作单位，事务中包括的操作要么都做，要么都不做。
- 一致性（consistency）。事务必须是使数据库从一个一致性状态变到另一个一致性状态。一致性与原子性是密切相关的。
- 隔离性（isolation）。一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
- 持久性（durability）。指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。

### 4. Spring支持的事务类型

#### 4.1 Global Transactions

 全局事务使您可以使用多个事务资源，通常是关系数据库和消息队列。应用服务器通过JTA管理全局事务，而JTA是一个繁琐的API（部分是由于其异常模型）。此外，`UserTransaction`通常需要从JNDI 派生JTA ，这意味着您还需要使用JNDI才能使用JTA。全局事务的使用限制了应用程序代码的任何潜在重用，因为JTA通常仅在应用程序服务器环境中可用。 

#### 4.2 Local Transactions

 本地事务是特定于资源的，例如与JDBC连接关联的事务。本地事务可能更易于使用，但有一个明显的缺点：它们不能跨多个事务资源工作。例如，使用JDBC连接管理事务的代码不能在全局JTA事务中运行。因为应用程序服务器不参与事务管理，所以它无法帮助确保多个资源之间的正确性。（值得注意的是，大多数应用程序使用单个事务资源。）另一个缺点是本地事务侵入了编程模型。 

### 5. Spring框架事务抽象

 Spring事务抽象的关键是事务策略的概念。事务管理主要包括如下3个接口：

- PlatformTransactionManager
- TransactionDefinition
- TransactionStatus

#### **5.1 PlatformTransactionManager**

`org.springframework.transaction.PlatformTransactionManager`接口定义了一种事务策略。

```java
public interface PlatformTransactionManager {

    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
```

**getTransaction**：根据事务定义信息从事务环境中返回一个已存在的事务，或返回一个新建的事务。

**commit**：根据事务的状态提交事务。

**rollback**：事务回滚，当发生异常时，会被隐式调用rollback方法。

Spring不直接管理事务，但为各个框架如JDBC、Hibernate，mybatis，JPA等都提供了对应的事务管理器。

```xml
<!--Spring JDBC事务和Mybatis-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     <property name="dataSource" ref="dataSource" />
</bean>

<!--Hibernate事务-->
<bean id="transactionManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
     <property name="sessionFactory" ref="sessionFactory" />
</bean>

<!--JPA事务-->
<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
     <property name="sessionFactory" ref="sessionFactory" />
</bean>

<!--JTA 事务 需要JNDI方式连接数据库-->
<jee:jndi-lookup id="dataSource" jndi-name="jdbc/jpetstore"/>
<bean id="txManager" class="org.springframework.transaction.jta.JtaTransactionManager" />

```



#### 5.2 TransactionDefinition

`TransactionDefinition`接口指定：

- 传播规则：通常，在事务范围内执行的所有代码都在该事务中运行。但是，如果在已存在事务上下文的情况下执行事务方法，则可以指定行为。
- 隔离级别：此事务与其他事务的工作隔离的程度。例如，该交易能否看到其他交易的未提交写入？
- 超时：超时之前该事务运行了多长时间，并被基础事务基础结构自动回滚。
- 只读状态：当代码读取但不修改数据时，可以使用只读事务。在某些情况下，例如使用Hibernate时，只读事务可能是有用的优化。

```java
public interface TransactionDefinition {
    int PROPAGATION_REQUIRED = 0;
    int PROPAGATION_SUPPORTS = 1;
    int PROPAGATION_MANDATORY = 2;
    int PROPAGATION_REQUIRES_NEW = 3;
    int PROPAGATION_NOT_SUPPORTED = 4;
    int PROPAGATION_NEVER = 5;
    int PROPAGATION_NESTED = 6;
    int ISOLATION_DEFAULT = -1;
    int ISOLATION_READ_UNCOMMITTED = 1;
    int ISOLATION_READ_COMMITTED = 2;
    int ISOLATION_REPEATABLE_READ = 4;
    int ISOLATION_SERIALIZABLE = 8;
    int TIMEOUT_DEFAULT = -1;

    int getPropagationBehavior();

    int getIsolationLevel();

    int getTimeout();

    boolean isReadOnly();

    @Nullable
    String getName();
}
```



#### 5.3 TransactionStatus

 接口为事务代码提供了一种控制事务执行和查询事务状态的简单方法 。

```java
public interface TransactionStatus extends SavepointManager, Flushable {
    boolean isNewTransaction();

    boolean hasSavepoint();

    void setRollbackOnly();

    boolean isRollbackOnly();

    void flush();

    boolean isCompleted();
}
```

### 6. 事务的传播规则和隔离级别

#### 6.1 事务的传播规则

在TransactionDefinition接口中定义了事务的传播规则：

**PROPAGATION_REQUIRED**：如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。

**PROPAGATION_SUPPORTS**：支持当前事务，如果当前没有事务，就以非事务方式执行。

**PROPAGATION_MANDATORY**：支持当前事务，如果当前没有事务，就抛出异常。

**PROPAGATION_REQUIRES_NEW**：新建事务，如果当前存在事务，把当前事务挂起。

**PROPAGATION_NOT_SUPPORTED**：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。

**PROPAGATION_NEVER**：以非事务方式执行，如果当前存在事务，则抛出异常。 
**PROPAGATION_NESTED**：寄生事务。

最常用规则： **REQUIRED**和**REQUIRES_NEW**

#### 6.2 事务的隔离级别

在TransactionDefinition接口中定义了事务的隔离级别：

**ISOLATION_DEFAULT**：这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.

**ISOLATION_READ_UNCOMMITTED**：这是事务最低的隔离级别，它充许别外一个事务可以看到这个事务未提交的数据。这种隔离级别会产生脏读，不可重复读和幻像读。

**ISOLATION_READ_COMMITTED**：保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数据。这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻像读。

**ISOLATION_REPEATABLE_READ**：这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读。它除了保证一个事务不能读取另一个事务未提交的数据外，还保证了避免下面的情况产生(不可重复读)。

**ISOLATION_SERIALIZABLE**：这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。除了防止脏读，不可重复读外，还避免了幻像读。



**Spring中除ISOLATION_DEFAULT外的4中隔离级别都是虚拟出来的级别，实际隔离设置以数据库中的设置的隔离级别为准。**

### 7. 基于XML的事务配置

配置事务步骤：

1. 根据ORM选择对象的，选择对应的事务类
2. 设置\<tx:advice>标签属性值
3. 将\<tx:advice>设置到aop中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">

    <context:property-placeholder location="classpath:db.properties"/>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <bean id="accountDao" class="Dao.impl.AccountDaoImpl"/>
    <bean id="accountService" class="service.impl.AccountServiceImpl"/>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--使用jdbc方式 创建DataSourceTransactionManager的bean,注入datasource属性值-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 配置事务属性 -->
    <!-- tx:advice标签 transaction-manager="事务Bean的id值"-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!--tx:method 设置哪些方法名的相关属性
            name:必填，支持通配符*。值为方法名
            propagation：设置事务的传播规则，默认值REQUIRED
            isolation：事务隔离级别
            timeout：超时，默认值-1
            read-only：当代码读取但不修改数据时，可以使用只读事务。默认false。
                       在某些情况下，例如使用Hibernate时，只读事务可能是有用的优化
            rollback-for：因什么异常而回滚，多个异常时用","隔开
            no-rollback-for：哪些异常不进行回滚，多个异常时用","隔开
            -->
            <tx:method name="get*" read-only="true"/>
            <tx:method name="list*" read-only="true"/>

            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    <!--aop -->
    <aop:config>
        <aop:pointcut id="txPoint" expression="execution(* service.AccountService..tran(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"/>
    </aop:config>
    
</beans>
```

\<tx:advice>中的设置

![](D:\TyporaDate\Spring\images\td-advice.png)

