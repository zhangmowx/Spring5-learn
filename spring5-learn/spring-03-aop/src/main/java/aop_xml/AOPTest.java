package aop_xml;

import aop_xml.service.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 09:53
 * @Description:D:\IdeaProjects\spring5\spring-learn\spring-03-aop\src\main\resources\applicationContext.xml
 */
@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "file:src/main/resources/applicationContext.xml")
public class AOPTest {

    @Autowired
    private BusinessService businessService;
    // before
    @Test
    public void test1(){
        businessService.beforeFunc();
    }
    // after return
    @Test
    public void test2(){
        businessService.afterReturnFunc();
    }
    // after-throw
    @Test
    public void test3() throws Exception {
        businessService.afterThrowFunc();
    }
    // after
    @Test
    public void test4(){
        businessService.afterFunc();
    }

    // around
    @Test
    public void test5(){
        businessService.aroundFunc();
    }

    // 获取被增强方法的相关值
    @Test
    public void test6(){
        businessService.adviceFunc("zhangsan",18);
    }
}
