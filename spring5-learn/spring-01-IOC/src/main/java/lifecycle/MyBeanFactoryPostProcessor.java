package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 16:02
 * @Description:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("执行了MyBeanFactoryPostProcessor中的postProcessBeanFactory方法");
    }

    public MyBeanFactoryPostProcessor(){
        System.out.println("实例化MyBeanFactoryPostProcessor");
    }
}
