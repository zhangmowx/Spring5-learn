package com.zm.selector;

import com.zm.pojo.Country;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/28 14:08
 * @Description:
 */
public class MyRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        BeanDefinition definition = new RootBeanDefinition(Country.class);
        //definition.setScope("singleton");
        //void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
        //beanName:给Bean对象设置名称
        //beanDefinition:设置bean的相关信息。可以设置scope，init-method等等信息
        registry.registerBeanDefinition("country",definition);
    }
}
