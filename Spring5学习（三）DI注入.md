## Spring5 DI 依赖注入

### 1. 依赖注入

 依赖注入（DI）是一个过程，通过该过程，对象只能通过构造函数参数，工厂方法的参数或在构造或创建对象实例后在对象实例上设置的属性来定义其依赖关系（即，与它们一起工作的其他对象）。从工厂方法返回。然后，容器在创建bean时注入那些依赖项。从根本上讲，此过程是通过使用类的直接构造或服务定位器模式来控制bean自身依赖关系的实例化或位置的bean本身的逆过程（因此称为Control Inversion） 

依赖注入方式：

- setter注入
- 构造器注入
- 其他方式（略）

#### 1.1 setter注入

使用<bean>标签的子元素<property>子元素设置

- ​	常量类型，直接使用value属性
- ​	引用类型，使用ref属性
- ​	集合类型，使用对应集合元素标签

```java
public class Person {

    private Integer id;
    private String name;
    private Dog dog;

    private Set<String> set;
    private List<String> list;
    private String[] array;
    private Map<String,Object> map;
    private Properties properties;
	//set,get,toString方法省略
}
public class Dog {

    private String name;
    private Integer age;
    //set,get,toString方法省略
}
```

```xml
<bean id="dog" class="bean.Dog">
        <property name="name" value="旺财"/>
        <property name="age" value="2"/>
    </bean>

    <bean id="person" class="bean.Person">
        <!--常量属性设置 name value-->
        <property name="id" value="1"/>
        <property name="name" value="zhang"/>
        <!--引用属性 name ref-->
        <property name="dog" ref="dog"/>
        <!--集合属性 使用对应的集合标签-->
        <property name="set" >
            <set>
                <value>set1</value>
                <value>set2</value>
            </set>
        </property>
        <property name="list">
            <list>
                <value>list1</value>
                <value>list2</value>
            </list>
        </property>
        <property name="array">
            <array>
                <value>array1</value>
                <value>array1</value>
            </array>
        </property>
        <property name="map">
            <map>
                <entry key="key1" value="value1"/>
                <entry key="key2" value="value2"/>
            </map>
        </property>
        <property name="properties">
            <props>
                <prop key="propkey1">propvalue1</prop>
                <prop key="propkey2">propvalue2</prop>
            </props>
        </property>
    </bean>
```

#### 1.2 构造器注入

使用<bean>标签的子元素<constructor-arg>子元素设置

- ​	常量类型，直接使用value属性
- ​	引用类型，使用ref属性
- ​	集合类型，使用对应集合元素标签

```java
public class User {

    private Integer id;
    private String name;
    private Dept dept;

    private Set<String> set;
    private List<String> list;
    private String[] array;
    private Map<String,Object> map;
    private Properties properties;

    public User() {}
    public User(Integer id, String name, Dept dept, Set<String> set, List<String> list, String[] array, Map<String, Object> map, Properties properties) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.set = set;
        this.list = list;
        this.array = array;
        this.map = map;
        this.properties = properties;
    }
    
 public class Dept {

    private Integer Deptid;
    private String Deptname;

    public Dept() {}
    public Dept(Integer deptid, String deptname) {
        Deptid = deptid;
        Deptname = deptname;
    }
}   
```

```xml
	<bean id="dept" class="bean.Dept">
        <constructor-arg name="deptid" value="2"/>
        <constructor-arg name="deptname" value="信息科技部"/>
    </bean>
    <bean id="user" class="bean.User">
        <constructor-arg name="id" value="2"/>
        <constructor-arg name="name" value="wang"/>
        <constructor-arg name="dept" ref="dept"/>
        <constructor-arg name="set">
            <set>
                <value>set1</value>
                <value>set2</value>
            </set>
        </constructor-arg>
        <constructor-arg name="list">
            <list>
                <value>list1</value>
                <value>list2</value>
            </list>
        </constructor-arg>
        <constructor-arg name="array">
            <array>
                <value>array1</value>
                <value>array1</value>
            </array>
        </constructor-arg>
        <constructor-arg name="map">
            <map>
                <entry key="key1" value="value1"/>
                <entry key="key2" value="value2"/>
            </map>
        </constructor-arg>
        <constructor-arg name="properties">
            <props>
                <prop key="propkey1">propvalue1</prop>
                <prop key="propkey2">propvalue2</prop>
            </props>
        </constructor-arg>
    </bean>
```

### 2. Bean继承

将多个Bean中的共同属性抽取到一个通用的抽象bean中。

1. Tiger和Lion都拥有animalType属性，将animalType属性抽取创建一个新的bean，设置bean的属性**abstract="true"**  指定该bean为一个抽象bean，用于继承。
2. 在Tiger和Lion的bean中，添加parent属性，**parent=“抽象bean的id值‘**，

```java
public class Tiger {
    private String name;
    private String animalType;
    private Integer age;
    //set,get,toString方法省略
}
public class Lion {
    private String name;
    private String animalType;
    private Double weight;
    //set,get,toString方法省略
}
```



```xml
<!--bean 继承 ============-->
    <bean id="animal" abstract="true">
        <property name="animalType" value="哺乳动物"/>
    </bean>

    <bean id="tiger" class="bean_Inheritance.Tiger" parent="animal">
        <property name="name" value="老虎"/>
        <property name="age" value="5"/>
    </bean>
    <bean id="lion" class="bean_Inheritance.Lion" parent="animal">
        <property name="name" value="狮子"/>
        <property name="weight" value="200"/>
        <!--对 animalType属性进行重新赋值，覆盖animal中的属性值-->
        <!--<property name="animalType" value="哺乳动物,肉食动物"/>-->
    </bean>
    <!--bean 继承 ============-->
```

### 3. 属性占位符

 可以使用`PropertySourcesPlaceholderConfigurer`标准Java `Properties`格式，从外部文件properties中获取属性值，将值注入bean中。这样做使部署应用程序的人员可以自定义特定于环境的属性，例如数据库URL和密码，而无需为修改容器的主要XML定义文件

```xml
	<!--
     加载 properties资源文件 当多个时properties时,用分号隔开
     location="classpath:a.properties,classpath:b.properties"
     -->
    <context:property-placeholder location="classpath:db.properties"/>

	<!--属性占位符 -->
    <!--普通方式-->
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mysql"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>
    <!--占位符方式-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
```

```properties
#key=value
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mysql
jdbc.username=root
jdbc.password=123456
```

