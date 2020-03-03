package tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/3/3 10:46
 * @Description:
 */
@Repository
public class CountryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCountry(){
        String sql = "INSERT INTO country(countrycode,countryname) values(?,?)";
        jdbcTemplate.update(sql,"code1","name1");
    }
}
