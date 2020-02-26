package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 16:34
 * @Description:
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

//    @Override
//    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        System.out.println("执行了MyInstantiationAwareBeanPostProcessor中的postProcessBeforeInstantiation方法");
//        System.out.println("beanName:"+beanName);
//        return null;
//    }


    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("执行了MyInstantiationAwareBeanPostProcessor中的postProcessAfterInstantiation...");
        return true;
    }

    public MyInstantiationAwareBeanPostProcessor(){
        System.out.println("实例化MyInstantiationAwareBeanPostProcessor");
    }
}
