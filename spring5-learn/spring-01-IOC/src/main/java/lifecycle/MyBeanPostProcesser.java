package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 16:13
 * @Description:
 */
public class MyBeanPostProcesser implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行了MyBeanPostProcesser中的postProcessBeforeInitialization方法");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行了MyBeanPostProcesser中的postProcessAfterInitialization方法");
        return null;
    }

    public MyBeanPostProcesser(){
        System.out.println("实例化MyBeanPostProcesser");
    }
}
