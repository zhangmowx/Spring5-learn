import bean.Person;
import bean.User;
import bean_Inheritance.Lion;
import bean_Inheritance.Tiger;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import javafx.util.converter.DateStringConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/23 10:25
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "file:src/main/resources/ApplicationContext.xml")
public class SpringTest {

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private Person person;
    @Autowired
    private User user;
    @Autowired
    private Tiger tiger;
    @Autowired
    private Lion lion;

    @Autowired
    private DruidDataSource druidDataSource;

    @Autowired
    private DataSource dataSource;

    /**
     * 测试 setter方式注入
     */
    @Test
    public void test1(){
        System.out.println(person.toString());
    }

    /**
     * 测试构造器注入
     */
    @Test
    public void test2(){
        System.out.println(user);
    }

    /**
     * 测试 Bean的继承
     */
    @Test
    public void test3(){
        System.out.println(tiger);
        System.out.println(lion);
    }

    /**
     * 测试 属性占位符
     */
    @Test
    public void test4() throws SQLException {
        //普通方式
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
        //使用占位符方式
        Connection connection1 = dataSource.getConnection();
        System.out.println(connection1);
    }
}
