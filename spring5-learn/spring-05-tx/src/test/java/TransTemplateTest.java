import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import service.AccountService;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 14:18
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "file:src/main/resources/applicationContext.xml")
public class TransTemplateTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void test(){
        accountService.tran(1L,2L,1000);
    }

}
