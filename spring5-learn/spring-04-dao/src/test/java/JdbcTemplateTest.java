import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 14:18
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "file:src/main/resources/applicationContext.xml")
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        String sql = "INSERT INTO country(countryname,countrycode) values(?,?)";
        jdbcTemplate.update(sql,"安哥拉","AO");
    }

    @Test
    public void test2(){
        String sql = "UPDATE country set countrycode=?,countryname=? where id=?";
        jdbcTemplate.update(sql,"安哥拉1","AO1",11);
    }

    @Test
    public void test3(){
        String sql = "DELETE FROM country where id=?";
        jdbcTemplate.update(sql,10);
    }

    @Test
    public void test4(){
        String sql = "SELECT id,countryname,countrycode FROM country";
        List<Country> query = jdbcTemplate.query(sql, new RowMapper<Country>() {
            @Override
            public Country mapRow(ResultSet resultSet, int i) throws SQLException {
                Country country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setCountryName(resultSet.getString("countryname"));
                country.setCountryCode(resultSet.getString("countrycode"));
                return country;
            }
        });
        //遍历
        for (Country country : query) {
            System.out.println(country);
        }
    }
    // JDK8 API
    @Test
    public void test5(){
        String sql = "SELECT id,countryname,countrycode FROM country";
        List<Country> query = jdbcTemplate.query(sql,
                (resultSet,i)->{
                Country country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setCountryName(resultSet.getString("countryname"));
                country.setCountryCode(resultSet.getString("countrycode"));
                return country;

        });
        //遍历
        query.forEach(System.out::println);
    }

    // JDK8 API  查询id为1的country信息
    @Test
    public void test6(){
        String sql = "SELECT id,countryname,countrycode FROM country where id=?";
        List<Country> query = jdbcTemplate.query(sql,new Object[]{1},
                (resultSet,i)->{
                    Country country = new Country();
                    country.setId(resultSet.getLong("id"));
                    country.setCountryName(resultSet.getString("countryname"));
                    country.setCountryCode(resultSet.getString("countrycode"));
                    return country;

                });
        //遍历
        query.forEach(System.out::println);
    }


}
