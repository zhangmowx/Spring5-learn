package Dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import Dao.AccountDao;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/26 21:06
 * @Description:
 */
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void transOut(Long outid, float money) {
        jdbcTemplate.update("UPDATE account set totalmoney = totalmoney - ? where id = ?",money,outid);
    }

    @Override
    public void tranIn(Long inid, float money) {
        jdbcTemplate.update("UPDATE account set totalmoney = totalmoney + ? where id = ?",money,inid);
    }
}
