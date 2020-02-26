import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 15:14
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "file:src/main/resources/applicationContext.xml")
public class HibernateTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void test1(){
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Country");
        List list = query.list();
        list.forEach(System.out::println);
    }
}
