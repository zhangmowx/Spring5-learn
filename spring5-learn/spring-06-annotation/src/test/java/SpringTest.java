
import com.zm.config.AppConfig;
import com.zm.service.ArithmeticService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/27 21:03
 * @Description:
 */
public class SpringTest {
    //可以通过new AnnotationConfigApplicationContext(参数为@Configuration注解的类的class)
    @Test
    public void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(applicationContext);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ArithmeticService arithmeticService = applicationContext.getBean("arithmeticService", ArithmeticService.class);
        int addition = arithmeticService.addition(1, 2);
        System.out.println(addition);
    }

    @Test
    public void test3(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ArithmeticService arithmeticService = applicationContext.getBean("arithmeticService", ArithmeticService.class);
        //int division = arithmeticService.division(6, 2);
        int division = arithmeticService.division(6, 0);
        System.out.println(division);
    }



}
