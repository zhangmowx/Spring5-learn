package tx;



import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tx.service.CountryService;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/3/3 10:51
 * @Description:
 */
public class TxTest {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TxAPPConfig.class);

        CountryService countryService = (CountryService) applicationContext.getBean("countryService");

        countryService.saveCountry();

    }

}
