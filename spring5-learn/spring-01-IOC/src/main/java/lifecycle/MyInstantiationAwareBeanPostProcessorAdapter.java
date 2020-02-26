package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 16:28
 * @Description:
 */
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("执行了MyInstantiationAwareBeanPostProcessorAdapter中的postProcessBeforeInstantiation。。");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("执行了MyInstantiationAwareBeanPostProcessorAdapter中的postProcessAfterInstantiation。。");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

//    @Override
//    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
//        System.out.println("执行了MyInstantiationAwareBeanPostProcessorAdapter中的postProcessProperties。。");
//        return super.postProcessProperties(pvs, bean, beanName);
//    }
    public MyInstantiationAwareBeanPostProcessorAdapter(){
        System.out.println("实例化MyInstantiationAwareBeanPostProcessorAdapter");
    }
}
