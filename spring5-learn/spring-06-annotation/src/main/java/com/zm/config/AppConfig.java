package com.zm.config;

import com.zm.condition.LinuxCondition;
import com.zm.condition.WindowsCondition;
import com.zm.filter.MyTypeFilter;
import com.zm.pojo.*;
import com.zm.selector.MyImportSelector;
import com.zm.selector.MyRegistrar;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/27 21:01
 * @Description:
 * @Configuration 相当于applicationContext.xml配置文件
 */
@Configuration
@ComponentScan(value="com.zm")
@ComponentScan(value="aop")
//value(basePackages) 属性指定要扫描的包路径，多个用逗号分割
//@ComponentScan(value = "com.zm",excludeFilters = {
//        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class})
//})
//@ComponentScan(value = "com.zm",includeFilters = {
//        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class})
//},useDefaultFilters = false)
//@ComponentScan(value = "com.zm",includeFilters = {
//        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {MyTypeFilter.class})
//},useDefaultFilters = false)
//@Import({Cat.class, Dog.class, MyImportSelector.class,MyRegistrar.class})
@EnableAspectJAutoProxy
public class AppConfig {

    @Scope(value="singleton",proxyMode = ScopedProxyMode.DEFAULT)
    @Bean(value="p")
    public Person person() {
        return new Person("zhangsan", 18);
    }

    @Lazy
    @Bean(value="person01")
    public Person person01() {
        return new Person("zhangsan", 18);
    }

    @Conditional(value={WindowsCondition.class})
    @Bean(value="linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    @Conditional(value={LinuxCondition.class})
    @Bean(value="bill")
    public Person person03() {
        return new Person("bill", 68);
    }


    @Bean()
    public CatFactoryBean catBean(){
        return new CatFactoryBean();
    }

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Teacher teacher(){
        return new Teacher();
    }

}
