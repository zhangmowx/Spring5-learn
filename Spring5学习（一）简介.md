## 							         Spring5 学习&简介

### 1. Spring简介

> Spring是一个开源框架，它由[Rod Johnson](https://baike.baidu.com/item/Rod Johnson)创建。它是为了解决企业应用开发的复杂性而创建的。Spring使用基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，任何Java应用都可以从Spring中受益。
>
> Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器框架。
>
> **轻量**——从大小与开销两方面而言Spring都是轻量的。完整的Spring框架可以在一个大小只有1MB多的JAR文件里发布。并且Spring所需的处理开销也是微不足道的。此外，Spring是非侵入式的：典型地，Spring应用中的对象不依赖于Spring的特定类。
>
> **控制反转**——Spring通过一种称作控制反转（IoC）的技术促进了松耦合。当应用了IoC，一个对象依赖的其它对象会通过被动的方式传递进来，而不是这个对象自己创建或者查找依赖对象。你可以认为IoC与JNDI相反——不是对象从容器中查找依赖，而是容器在对象初始化时不等对象请求就主动将依赖传递给它。
>
> **面向切面**——Spring提供了[面向切面编程](https://baike.baidu.com/item/面向切面编程)的丰富支持，允许通过分离应用的业务逻辑与系统级服务（例如审计（auditing）和事务（transaction）管理）进行内聚性的开发。应用对象只实现它们应该做的——完成业务逻辑——仅此而已。它们并不负责（甚至是意识）其它的系统级关注点，例如日志或事务支持。
>
> **容器**——Spring包含并管理应用对象的配置和生命周期，在这个意义上它是一种容器，你可以配置你的每个bean如何被创建——基于一个可配置原型（prototype），你的bean可以创建一个单独的实例或者每次需要时都生成一个新的实例——以及它们是如何相互关联的。然而，Spring不应该被混同于传统的重量级的EJB容器，它们经常是庞大与笨重的，难以使用。
>
> **框架**——Spring可以将简单的组件配置、组合成为复杂的应用。在Spring中，应用对象被声明式地组合，典型地是在一个XML文件里。Spring也提供了很多基础功能（事务管理、持久化框架集成等等），将应用逻辑的开发留给了你。
>
> 所有Spring的这些特征使你能够编写更干净、更可管理、并且更易于测试的代码。它们也为Spring中的各种模块提供了基础支持。

### 2. Spring模块

![](D:\TyporaDate\Spring\images\spring框架.jpg)

**Core Container**：核心容器是Spring框架的重要组成部分，是Spring框架的基础。在整个框架中负责对象的创建，管理，配置等的操作。其主要包含spring-core，spring-beans，spring-context，spring-expression，spring-context-support组件。

**AOP**：Spring框架还提供了面向切面编程的能力，利用面向切面编程，可以实现一些面向对象编程无法很好实现的操作。例如，将日志，事务与具体的业务逻辑解耦。其主要包含spring-aop，spring-aspects组件。

**Instrumentation**：该模块提供了为JVM添加代理的功能，该模块包含spring-instrument，spring-instrument-tomcat组件。

**Data Access/Integration**：Spring框架为了简化数据访问的操作，包装了很多关于数据访问的操作，提供了相应的模板。同时还提供了使用ORM框架的能力，可以与很多流行的ORM框架进行整合，如hibernate，mybatis等等的著名框架。还实现了数据事务的能力，能够支持事务。包含spring-jdbc，spring-tx，spring-orm，spring-oxm，spring-jms，spring-messaging组件。

**Web**：Spring框架支持Web开发，以及与其他应用远程交互调用的方案。包含spring-web，spring-webmvc，spring-websocket，spring-webmvc-portlet组件。

**Test**：Spring框架提供了测试的模块，可以实现单元测试，集成测试等等的测试流程，整合了JUnit或者TestNG测试框架。包含spring-test组件。

### 3. 开发环境准备

- 开发工具：IntelliJ IDEA 2018.3.1 (Ultimate Edition)
- JDK版本：java version "1.8.0_40"
- Maven：Apache Maven 3.6.1
- Spring：5.1.13.RELEASE

### 4. 学习目录









