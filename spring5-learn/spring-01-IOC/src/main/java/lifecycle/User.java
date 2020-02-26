package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 15:59
 * @Description:
 */
public class User implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {

    private Integer id;
    private String name;

    public  User(){
        System.out.println("创建User Bean对象。。。");
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        System.out.println("执行User的setId方法执行");
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        System.out.println("执行User的setName方法执行");
        this.name = name;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行User的setBeanFactory方法");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("执行User实现的接口BeanNameAware中重写的setBeanName方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行User的destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行User的afterPropertiesSet方法");
    }

    public void UserinitMethod(){
        System.out.println("执行了User的UserinitMethod方法");
    }

    public void UserDestroyMethod(){
        System.out.println("执行了User的UserDestroyMethod方法");
    }


}
