import bean.*;
import lifecycle.User;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/22 13:59
 * @Description:  测试类用于测试BeanFactory和ApplicationContext 加载Bean的方式。
 *
 *
 */
public class SpringTest {

    /**
     ===========
     初始化Person...
     bean.Person@29774679
     */
    @Test
    public void testBeanFactory() {
        Resource resource = new ClassPathResource("SpringInitBean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        System.out.println("===========");
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    /**
     初始化Person...
     =============
     bean.Person@1a8a8f7c
     */
    @Test
    public void testApplicationContext(){
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("SpringInitBean.xml");
        System.out.println("=============");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

    /**
     * 测试静态工厂方法实例化bean
     */
    @Test
    public  void test3(){
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("SpringInitBean.xml");
        System.out.println("=============");
        Dog dog = (Dog) ctx.getBean("dog");
        System.out.println(dog);
    }
    /**
     * 测试实例工厂方法实例化bean
     */
    @Test
    public  void test4(){
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("SpringInitBean.xml");
        System.out.println("=============");
        Cat cat = (Cat) ctx.getBean("cat");
        System.out.println(cat);
    }

    /**
     * 测试 实现FactoryBean接口实例化bean
     * 及获取FactoryBean对象
     */
    @Test
    public  void test5(){
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("SpringInitBean.xml");
        System.out.println("=============");
        Country country = (Country) ctx.getBean("country");
        // 获取FactoryBean对象，在bean对象的id前加上&符号
        CountryFactory countryFactory = (CountryFactory) ctx.getBean("&country");
        System.out.println(country);
        System.out.println(countryFactory);
    }

    /**
     * 测试bean对象的生命周期
     */
    @Test
    public void test6(){
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("SpringInitBean.xml");

        User user = (User) ctx.getBean("user");
        System.out.println(user);
        ctx.close();

    }

}
